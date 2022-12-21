package frontend.ir;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.RootNode;
import frontend.ast.astnode.definition.*;
import frontend.ast.astnode.expression.*;
import frontend.ast.astnode.statement.*;
import frontend.ir.hierarchy.*;
import frontend.ir.instruction.*;
import frontend.ir.irtype.*;
import frontend.ir.operands.*;
import org.antlr.v4.runtime.misc.Pair;
import utility.Debugger;
import utility.error.IRBuildError;
import utility.error.SemanticError;
import utility.info.*;
import utility.scope.RootScope;
import utility.scope.ScopeManager;
import utility.type.BaseType;
import utility.type.VarType;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {
  private final IRModule module;
  private final ScopeManager scopeManager = new ScopeManager();

  private IRBlock curBlock, initCurBlock;
  private IRFunction curFunction;

  private final IRFunction globInitFunc;

  private final Value zero = new IntConst(0),
          one = new IntConst(1),
          negOne = new IntConst(-1);

  public IRBuilder(IRModule module, RootNode root) {
    this.module = module;
    globInitFunc = new IRFunction("globInit", new FuncType(new VoidType()));
    initCurBlock = globInitFunc.getEntryBlock();
    module.addFunction(globInitFunc);

    root.accept(this);

    new Branch(globInitFunc.getExitBlock(), initCurBlock);
    buildCFG();
  }

  private void buildCFG() {
    for (var func : module.irFuncList) {
      for (var block : func.blockList)
        block.insert2CFG();
    }
  }

  @Override
  public void visit(RootNode node) {
    // add builtin function
    for (var builtin : module.builtinFuncList) {
      String name = builtin.name; // e.g. printlnInt
      FuncInfo info = node.scope.queryFuncInfo(name);
      if (info == null)
        throw new IRBuildError("no builtin function named " + name, node.pos);
      info.value = builtin;
    }

    scopeManager.pushScope(node.scope);

    Declaration(node);

    // Global variable declare
    for (var nexNode : node.defs) {
      if (nexNode instanceof VarDefNode)
        nexNode.accept(this);
    }

    for (var nexNode : node.defs) // class
      if (nexNode instanceof ClassDefNode)
        nexNode.accept(this);

    for (var nexNode : node.defs) // function
      if (nexNode instanceof FuncDefNode)
        nexNode.accept(this);

    scopeManager.popScope();
  }

  @Override
  public void visit(VarDefNode node) {
    node.varList.forEach(it -> it.accept(this));
  }

  @Override
  public void visit(VarSingleDefNode node) {
    VarType type = node.info.type;
    String name = node.info.name;
    boolean isGlobal = scopeManager.curScope() instanceof RootScope;

    if (isGlobal) {
      // ==== global variable ====
      GlobalDef def = new GlobalDef(name, transType(type));
      module.addGlobVariable(def);

      curFunction = globInitFunc;
      curBlock = initCurBlock;
      node.info.value = node.value = def;
      node.value.recordPtr = node.value;
    } else {
      // ==== local variable ====
      Value tmp = allocate(name, transType(type));

      node.info.value = node.value = tmp;
      node.value.recordPtr = node.value;
    }

    if (node.expr != null) {
      node.expr.accept(this);
      store(node.expr.value, node.value.recordPtr);
    } else if (BaseType.isClassType(node.info.type) || BaseType.isArray(node.info.type)) {
      store(new NullConst(), node.value.recordPtr);
    }

    if (isGlobal)
      initCurBlock = curBlock;
  }

  @Override
  public void visit(ClassDefNode node) {
    scopeManager.pushScope(node.scope);

    // in Mx*, we don't have to traverse class's variables
//    node.varDefs.forEach(var -> var.accept(this));
    for (var var : node.varDefs) {
      var.value = new GlobalDef(node.info.name + "." + var.info.name, transType(var.info.type));
      var.info.value = var.value;
//      store(new NullConst(), var.value);
    }

    node.funcDefs.forEach(func -> func.accept(this));

    scopeManager.popScope();
  }

  @Override
  public void visit(FuncDefNode node) {
    scopeManager.pushScope(node.scope);

    // declare several variables
    boolean isMethod = scopeManager.curClassScope != null;

    IRFunction function = (IRFunction) node.info.value;
    curFunction = function;
    curBlock = function.getEntryBlock();

    if (node.info.name.equals("main")) {
      new Call(globInitFunc, curBlock);
      store(zero, function.retValPtr);
    }

    updateParaList(isMethod, function, node); // insert parameters

    node.stmts.accept(this);

    if (hasNoReturn(node))
      new Branch(curFunction.getExitBlock(), curBlock);

    curFunction = null;
    scopeManager.popScope();
  }

  @Override
  public void visit(AtomExprNode node) {
    if (node.atom.StringConst() != null) {
      // String const
      String strConst = node.atom.StringConst().toString();
      strConst = strConst.substring(1, strConst.length() - 1)
              .replace("\\n", "\n")
              .replace("\\\"", "\"")
              .replace("\\\\", "\\");
      StringConst str = new StringConst(strConst);
      module.globVarList.addFirst(str);
      node.value = new GetElePtr(str.name + ".load", new IntType(8), str, curBlock, zero);

    } else if (node.atom.Decimal() != null) {
      node.value = new IntConst(Integer.parseInt(node.atom.Decimal().toString()));

    } else if (node.atom.This() != null) {
      FuncInfo info = scopeManager.getFuncScope().info;
      if (info.value instanceof IRFunction func) {
        node.value = func.getOperand(0); // get "this pointer"
      } else throw new IRBuildError("\"this\" is not a IRFunction ?? ", node.pos);

    } else if (node.atom.True() != null || node.atom.False() != null) {
      node.value = new BoolConst(node.atom.True() != null ? "true" : "false"); // ??? optimize bool Const

    } else if (node.atom.Identifier() != null) {
      String name = node.atom.Identifier().toString();
      BaseInfo info = scopeManager.queryName(name);

      if (info instanceof VarInfo) {
        // must load this variable.
        Pair<Value, Boolean> pair = scopeManager.queryValue(name);

        boolean isMemberVariable = pair.b;

        Value address;
        if (isMemberVariable) {
          // use getelementptr to acquire member
          assert scopeManager.getFuncScope() != null;

          ClassInfo curClass = scopeManager.getCurClassInfo();
          Value curFunc = scopeManager.getFuncScope().info.value;
          Value thisOperand = ((IRFunction) curFunc).getOperand(0);
          address = visitStructMember(curClass, thisOperand, name);
          if (address == null)
            throw new IRBuildError("can't find member: " + name + " in " + curClass.name, node.pos);
        } else {
          address = pair.a;
        }

//        System.out.println(node.pos.toString());
        node.value = load(address);
      } else {
        // function type
        // don't need to create basicBlock here.
        node.value = info.value;
        // System.out.println(node.pos.toString());
      }

    } else {
      // null
      node.value = new NullConst();
    }

  }

  @Override
  public void visit(NewExprNode node) {
    VarType type = (VarType) node.exprType;
    if (type.dimension == 0) {
      assert BaseType.isClassType(type);
      // Debugger.printPause(type.ClassName);
      StructType classType = module.getStruct(type.ClassName);
      Call mallocPtr = new Call(module.MallocFunction(), curBlock, new IntConst(classType.size()));
      node.value = bitcast(mallocPtr, new PtrType(module.getStruct(type.ClassName)));

      FuncInfo constructor = scopeManager.queryClassInfo(type.ClassName).findFuncInfo(type.ClassName);
      if (constructor != null)
        new Call((IRFunction) constructor.value , curBlock, node.value);
      return;
    }
    node.value = MallocArray(node, node.dimensionExpr, 0);
    node.value.recordPtr = node.value;
  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    // getelementptr
    if (BaseType.isStringType(node.callExpr.exprType)) {
      node.value = module.getStrFunc(node.member);
      return;
    }

    if (BaseType.isArray(node.callExpr.exprType)) {
      PtrType i32 = new PtrType(new IntType());
      Value cast = bitcast(node.callExpr.value, i32);
      node.value = load(new GetElePtr("cast", cast, negOne, curBlock));
      return;
    }

    ClassInfo classInfo = scopeManager.queryClassInfo(node.callExpr.exprType.ClassName);
    if (node.exprType instanceof VarType) {
      Value strPtr = node.callExpr.value;
      if (strPtr == null || !(strPtr.getType() instanceof PtrType)) {
        Debugger.print(node.pos.toString() + " " + node.member);
        Debugger.print(strPtr.getTypeAndName());
        throw new IRBuildError("not a pointer type", node.pos);
      }
      Value address = visitStructMember(classInfo, strPtr, node.member);

      if (address == null)
        throw new IRBuildError("can't find member: " + node.member + " in " + classInfo.name, node.pos);

      node.value = load(address);
    } else {
      // function call
      node.value = classInfo.findFuncInfo(node.member).value;
    }
  }

  @Override
  public void visit(BracketExprNode node) {
    node.callExpr.accept(this);
    node.index.accept(this);
    GetElePtr address = new GetElePtr(node.callExpr.value.name + ".elm", node.callExpr.value, node.index.value, curBlock);
    node.value = load(address);
  }

  @Override
  public void visit(FuncExprNode node) {
    node.callExpr.accept(this);
    if (!(node.callExpr.value instanceof IRFunction)) {
      node.value = node.callExpr.value;
      return;
    }

    ArrayList<Value> argList = new ArrayList<>();
    if (node.callExpr instanceof MemberExprNode memberExp) {
      argList.add(memberExp.callExpr.value);
    } else if (((IRFunction) node.callExpr.value).isMethod) {
      argList.add(curFunction.getOperand(0));
    }


    for (int i = 0; i < node.argumentList.size(); ++i) {
      ExprNode arg = node.argumentList.get(i);
      arg.accept(this);
      argList.add(arg.value);
    }

    Call call = new Call((IRFunction) node.callExpr.value, curBlock);
    argList.forEach(call::addOperands);

    node.value = call;
  }

  @Override
  public void visit(SelfExprNode node) {
    node.expression.accept(this);
    String op = node.opCode.equals("++") ? "+" : "-";
    Value calcRes = new Binary(Binary.Trans(op), node.expression.value, one, curBlock);
    Value storePtr = node.expression.value.recordPtr;
    store(calcRes, storePtr);

    node.value = node.expression.value;
    // x = 2, int y = x++; => x = 3, y = 2;
  }

  @Override
  public void visit(UnaryExprNode node) {
    node.expression.accept(this);
    switch (node.opCode) {
      case "+" -> node.value = node.expression.value;
      case "-" -> node.value = new Binary("sub", zero, node.expression.value, curBlock);
      case "!" -> node.value = new Binary("xor", node.expression.value, new BoolConst("true"), curBlock);
      case "~" -> node.value = new Binary("xor", node.expression.value, negOne, curBlock);
      default -> {
        // ++x and --x
        String op = node.opCode.equals("++") ? "add" : "sub";
        Value calcRes = new Binary(op, node.expression.value, one, curBlock);
        Value storePtr = node.expression.value.recordPtr;
        store(calcRes, storePtr);
        calcRes.recordPtr = storePtr;

        node.value = calcRes;
      }
    }
  }

  @Override
  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    if (node.opType == BinaryExprNode.binaryOpType.Logic) {
      IRBlock tmpBlock = curBlock;
      if (node.opCode.equals("&&")) {
        IRBlock exitAnd = newBlock("exitAnd");
        IRBlock ifTrue = newBlock("calc.True");
        new Branch(node.lhs.value, ifTrue, exitAnd, tmpBlock);

        curBlock = ifTrue;
        node.rhs.accept(this);
        new Branch(exitAnd, curBlock);

        Phi phi = new Phi(new BoolType(), exitAnd);
        phi.addOperands(new BoolConst("false"), tmpBlock);
        phi.addOperands(node.rhs.value, curBlock);

        curBlock = exitAnd;
        node.value = phi;
      } else {
        IRBlock exitOr = newBlock("exitOr");
        IRBlock ifFalse = newBlock("calc.False");
        new Branch(node.lhs.value, exitOr, ifFalse, tmpBlock);

        curBlock = ifFalse;
        node.rhs.accept(this);
        new Branch(exitOr, curBlock);

        Phi phi = new Phi(new BoolType(), exitOr);
        phi.addOperands(new BoolConst("true"), tmpBlock);
        phi.addOperands(node.rhs.value, curBlock);

        curBlock = exitOr;
        node.value = phi;
      }
      return;
    }

    node.rhs.accept(this);
    if (BaseType.isStringType(node.rhs.exprType)) {
      String opcode = Icmp.Trans(node.opCode);
      if (opcode == null)
        opcode = Binary.Trans(node.opCode);
      node.value = new Call(module.getStrFunc(opcode), curBlock, node.lhs.value, node.rhs.value);
    } else {
      if (node.opType == BinaryExprNode.binaryOpType.Equal
              || node.opType == BinaryExprNode.binaryOpType.Compare) {
        node.value = new Icmp(Icmp.Trans(node.opCode), node.lhs.value, node.rhs.value, curBlock);
      } else {
        node.value = new Binary(Binary.Trans(node.opCode), node.lhs.value, node.rhs.value, curBlock);
      }
    }
  }

  @Override
  public void visit(AssignExprNode node) {
    node.lhs.accept(this); // lhs的load其实是不必要的? e.g. x = y;
    node.rhs.accept(this);

    Value storePtr = node.lhs.value.recordPtr;
    store(node.rhs.value, storePtr);

    node.value = node.rhs.value;
    node.value.recordPtr = storePtr;
  }

  @Override
  public void visit(SuiteStmtNode node) {
    scopeManager.pushScope(node.scope);
    node.stmts.forEach(stmt -> stmt.accept(this));
    scopeManager.popScope();
  }

  @Override
  public void visit(IfStmtNode node) {
    IRBlock ifTrue = newBlock("if.then");
    IRBlock ifFalse = newBlock("if.else");
    IRBlock ifExit = newBlock("if.end");

    node.condition.accept(this);
    new Branch(node.condition.value, ifTrue, ifFalse, curBlock);

    curBlock = ifTrue;
    if (node.thenStmt != null) {
      scopeManager.pushScope(node.thenScope);
      node.thenStmt.accept(this);
      scopeManager.popScope();
    }
    new Branch(ifExit, curBlock);

    curBlock = ifFalse;
    if (node.elseStmt != null) {
      scopeManager.pushScope(node.elseScope);
      node.elseStmt.accept(this);
      scopeManager.popScope();
    }
    new Branch(ifExit, curBlock);

    curBlock = ifExit;
  }

  @Override
  public void visit(ReturnStmtNode node) {
    if (node.ret != null) {
      node.ret.accept(this);
      store(node.ret.value, curFunction.retValPtr);
    }
    new Branch(curFunction.getExitBlock(), curBlock);
  }

  @Override
  public void visit(WhileStmtNode node) {
    IRBlock whileCond = newBlock("while.cond");
    IRBlock whileBody = newBlock("while.body");
    IRBlock whileExit = newBlock("while.end");

    scopeManager.pushLoopBlock(whileCond, whileExit);
    new Branch(whileCond, curBlock);

    curBlock = whileCond;
    node.condition.accept(this);
    new Branch(node.condition.value, whileBody, whileExit, curBlock);

    curBlock = whileBody;
    if (node.stmt != null) {
      scopeManager.pushScope(node.scope);
      node.stmt.accept(this);
      scopeManager.popScope();
    }
    new Branch(whileCond, curBlock);

    scopeManager.popLoopBlock();
    curBlock = whileExit;
  }

  @Override
  public void visit(ForStmtNode node) {
    IRBlock forCond = newBlock("for.cond");
    IRBlock forBody = newBlock("for.body");
    IRBlock forStep = newBlock("for.inc");
    IRBlock forExit = newBlock("for.end");

    scopeManager.pushLoopBlock(forStep, forExit);

    if (node.initial != null)
      node.initial.accept(this);

    node.initVarDef.forEach(var -> var.accept(this));

    new Branch(forCond, curBlock);
    // scopeManager.curScope().print();

    curBlock = forCond;
    if (node.condition != null) {
      node.condition.accept(this);
      new Branch(node.condition.value, forBody, forExit, curBlock);
    } else new Branch(forBody, curBlock);


    curBlock = forBody;
    if (node.stmt != null) {
      scopeManager.pushScope(node.scope);
      node.stmt.accept(this);
      scopeManager.popScope();
    }
    new Branch(forStep, curBlock);

    curBlock = forStep;
    if (node.step != null)
      node.step.accept(this);
    new Branch(forCond, curBlock);


    scopeManager.popLoopBlock();
    curBlock = forExit;
  }

  @Override
  public void visit(BreakContinueNode node) {
    new Branch(node.isBreak ? scopeManager.loopExit() : scopeManager.loopStep(), curBlock);
  }

  @Override
  public void visit(LambdaExprNode node) {
    throw new IRBuildError("Lambda expression not supported", node.pos);
  }

  private IRBaseType transType(VarType type) {
    IRBaseType ret = switch (type.builtinType) {
      case INT -> new IntType();
      case BOOL -> new BoolType();
      case VOID -> new VoidType();
      case CLASS -> new PtrType(module.getStruct(type.ClassName));
      case STRING -> new PtrType(new IntType(8));
      default -> null;
    };

    for (int i = 1; i <= type.dimension; ++i)
      ret = new PtrType(ret);

    return ret;
  }

  private IRBlock newBlock(String name) {
    return new IRBlock(name, curFunction);
  }
  private BitCast bitcast(Value value, IRBaseType targetType) {
    return new BitCast(value, targetType, curBlock);
  }

  private Value allocate(String name, IRBaseType type) {
    return new Alloca(name, type, curFunction.getEntryBlock());
  }

  private Value load(Value value) {
    Load ld = new Load(value, curBlock);
    ld.recordPtr = value;
    return ld;
  }

  private void store(Value value, Value storePtr) {
    new Store(value, storePtr, curBlock);
  }

  private Value visitStructMember(ClassInfo info, Value thisOperand, String member) {
    int idx = 0;
    for (var var : info.varInfos) {
      if (var.name.equals(member))
        return new GetElePtr(info.name + "_" + member,
                transType(var.type), thisOperand, curBlock, new IntConst(idx));
      ++idx;
    }
    return null;
  }

  private boolean hasNoReturn(FuncDefNode node) {
    if (!(node.stmts instanceof SuiteStmtNode))
      throw new SemanticError("function definition must have \"{} \" ", node.pos);
    for (var son : ((SuiteStmtNode) node.stmts).stmts)
      if (son instanceof ReturnStmtNode)
        return false;
    return true;
  }


  private void updateParaList(boolean isMethod, IRFunction function, FuncDefNode node) {
    if (isMethod) {
      String className = scopeManager.getCurClassName();
      Value This = new Value("this", new PtrType(module.getStruct(className)));
      // function.isMethod = true;
      function.addParameters(This);
    }

    for (var info : node.info.paraListInfo) {
      Value para = new Value(info.name, transType(info.type));

      Value allocaPtr = allocate(info.name, transType(info.type));
      info.value = allocaPtr;
      info.value.recordPtr = allocaPtr;
      store(para, allocaPtr);
      function.addParameters(para);
    }
  }

  private Value MallocArray(NewExprNode node, ArrayList<ExprNode> dimExpr, int curDim) {
    // new int [2][3];

    dimExpr.get(curDim).accept(this);
    Value arraySize = dimExpr.get(curDim).value;    // arraySize = 2;
    VarType type = (VarType) node.exprType.clone(); // this is ugly...
    type.dimension = dimExpr.size() - curDim - 1;
    IRBaseType newType = transType(type);

    Binary byteCnt = new Binary("mul", new IntConst(newType.size()), arraySize, curBlock);
    Binary mallocSize = new Binary("add", new IntConst(4), byteCnt, curBlock); // extra byte to store size

    Call mallocPtr = new Call(module.MallocFunction(), curBlock, mallocSize);

    // store array's size:
    BitCast sizePtr = bitcast(mallocPtr, new PtrType(new IntType()));
    store(arraySize, sizePtr);

    BitCast ptr = bitcast(new GetElePtr("ahead", sizePtr, one, curBlock), new PtrType(newType));

    if (curDim + 1 == dimExpr.size() || dimExpr.get(curDim + 1) == null)
      return ptr;

    GetElePtr tail = new GetElePtr("ptr.tail", ptr, arraySize, curBlock);

    IRBlock whileCond = newBlock("while.cond");
    IRBlock whileBody = newBlock("while.body");
    IRBlock whileExit = newBlock("while.end");

    new Branch(whileCond, curBlock);

    // condition
    Phi cur = new Phi(new PtrType(newType), whileCond); // SSA: cur = phi [%ptr, %entry], [%inc, %while.body]
    cur.addOperands(ptr, curBlock);
    Icmp condition = new Icmp("ne", cur, tail, whileCond);
    new Branch(condition, whileBody, whileExit, whileCond);

    // body
    curBlock = whileBody;
    Value element = MallocArray(node, dimExpr, curDim + 1); // malloc new int [3]
    store(element, cur);
    GetElePtr inc = new GetElePtr("ptr.inc", cur, one, curBlock);
    cur.addOperands(inc, curBlock);
    new Branch(whileCond, curBlock);

    curBlock = whileExit;

    return ptr;
  }

  private void Declaration(RootNode node) {
    // Struct Type register information
    {
      ArrayList<ClassDefNode> clsList = new ArrayList<>();
      for (var nexNode : node.defs) {
        if (!(nexNode instanceof ClassDefNode clsNode))
          continue;
        clsList.add(clsNode);
        String name = clsNode.info.name;

        StructType newStruct = new StructType(name);
        module.addStruct(newStruct);

      }

      // doing so can avoid: class A { B ... } clas B { A ...}
      for (var clsNode : clsList) {
        String name = clsNode.info.name;
        StructType struct = module.getStruct(name);

        for (var variable : clsNode.info.varInfos) {
          struct.addMember(transType(variable.type));
        }

        // add class.method
        for (var funcInfo : clsNode.info.funcInfos) {
          FuncType funcType = new FuncType(transType(funcInfo.funcType.retType));
          IRFunction function = new IRFunction(name + "." + funcInfo.name, funcType);
          function.isMethod = true;
          funcInfo.value = function;
          module.addFunction(function);
        }
      }
    }

    // Function register information
    {
      for (var nexNode : node.defs) {
        if (!(nexNode instanceof FuncDefNode funcNode))
          continue;
        FuncType funcType = new FuncType(transType(funcNode.info.funcType.retType));
        funcNode.value = funcNode.info.value = new IRFunction(funcNode.info.name, funcType);
        module.addFunction((IRFunction) funcNode.value);
      }
    }
  }

}
