package middleend;

import frontend.ast.ASTVisitor;
import frontend.ast.RootNode;
import frontend.ast.definition.ClassDefNode;
import frontend.ast.definition.FuncDefNode;
import frontend.ast.definition.VarDefNode;
import frontend.ast.definition.VarSingleDefNode;
import frontend.ast.expression.*;
import frontend.ast.statement.*;
import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;
import middleend.hierarchy.IRModule;
import middleend.irinst.*;
import middleend.irtype.*;
import middleend.operands.BoolConst;
import middleend.operands.IntConst;
import middleend.operands.NullConst;
import org.antlr.v4.runtime.misc.Pair;
import utility.error.IRBuildError;
import utility.info.BaseInfo;
import utility.info.ClassInfo;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.scope.*;
import utility.type.BaseType;
import utility.type.VarType;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {
  private IRModule module;
  private final ScopeManager scopeManager = new ScopeManager();
  private IRBasicBlock forExit, forStep;
  private IRBasicBlock curBlock, initBlock;
  private IRFunction curFunction;
  private final IRBasicBlock globalInitBlock;

  private final HashMap<String, StructType> structCollection = new HashMap<>();
  // private final HashMap<String, FuncType> funcCollection = new HashMap<>();

  public IRBuilder(IRModule module, RootNode root) {
    this.module = module;
    this.initBlock = new IRBasicBlock("global dec", null);
    IRFunction globInitFunc = new IRFunction("global_init", new FuncType(new VoidType()));
    this.globalInitBlock = new IRBasicBlock("Init", globInitFunc);

    root.accept(this);
  }

  private IRBaseType transType(VarType type) {
    IRBaseType ret = switch (type.builtinType) {
      case INT -> new IntType();
      case BOOL -> new BoolType();
      case VOID -> new VoidType();
      case CLASS -> structCollection.get(type.ClassName);
      default -> null;
    };

    for (int i = 1; i <= type.dimension; ++i)
      ret = new PtrType(ret);

    return ret;
  }

  @Override
  public void visit(RootNode node) {
    scopeManager.pushScope(node.scope);

    // Struct Type register information
    {
      ArrayList<ClassDefNode> clsList = new ArrayList<>();
      for (var nexNode : node.defs) {
        if (!(nexNode instanceof ClassDefNode clsNode))
          continue;
        clsList.add(clsNode);
        String name = clsNode.info.name;

        StructType newStruct = new StructType(name);
        structCollection.put(newStruct.className, newStruct);
        // clsNode.value = newStruct;
      }

      for (var clsNode : clsList) {
        String name = clsNode.info.name;
        StructType struct = structCollection.get(name);

        for (var variable : clsNode.info.varInfos)
          struct.addMember(transType(variable.type));
        module.addStruct(struct);

        // add class.method
        for (var funcInfo : clsNode.info.funcInfos) {
          FuncType funcType = new FuncType(transType(funcInfo.funcType.retType));
          for (var para : funcInfo.paraListInfo)
            funcType.addParaType(transType(para.type));
          // funcCollection.put(name + "." + funcInfo.name, funcType);
          funcInfo.value = new IRFunction(name + "." + funcInfo.name, funcType);

          module.addFunction((IRFunction) funcInfo.value);
        }

      }
    }

    // Function register information
    {
      for (var nexNode : node.defs) {
        if (!(nexNode instanceof FuncDefNode funcNode))
          continue;
        FuncType funcType = new FuncType(transType(funcNode.info.funcType.retType));
        for (var para : funcNode.info.paraListInfo)
          funcType.addParaType(transType(para.type));
        // funcCollection.put(funcNode.info.name, funcType);
        funcNode.value = funcNode.info.value = new IRFunction(funcNode.info.name, funcType);

        module.addFunction((IRFunction) funcNode.value);
      }
    }

    // Global variable information
    {
      for (var nexNode : node.defs) {
        if (nexNode instanceof VarDefNode)
          nexNode.accept(this);
      }
    }

    for (var nexNode : node.defs)
      if (!(nexNode instanceof VarDefNode))
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

    Value tmp;
    if (scopeManager.curScope() instanceof RootScope) {
      // ==== global variable ====
      tmp = new GlobalDef(name, transType(type), initBlock);
      curBlock = globalInitBlock;
    } else {
      // ==== local variable ====
      tmp = allocate(transType(type));
    }

    node.info.value = node.value = tmp;

    if (node.expr != null) {
      node.expr.accept(this);
      store(node.expr.value, node.value);
    }

    scopeManager.addItem(node.info);
  }

  @Override
  public void visit(ClassDefNode node) {
    scopeManager.pushScope(node.scope);

    node.varDefs.forEach(var -> var.accept(this));

    node.funcDefs.forEach(func -> func.accept(this));

    scopeManager.popScope();
  }

  private void updateParaList(boolean isMethod, IRFunction function, FuncDefNode node) {
    if (isMethod)
      function.addParameters(new Value("this", new PtrType(function.funcType)));
    for (var info : node.info.paraListInfo) {
      Value para = new Value(info.name, transType(info.type));
      function.addParameters(para);
    }
  }

  @Override
  public void visit(FuncDefNode node) {
    scopeManager.pushScope(new FuncScope(node.info));

    // declare several variables
    boolean isMethod = !(scopeManager.curScope() instanceof RootScope);
    IRFunction function = (IRFunction) node.info.value;
    curBlock = new IRBasicBlock("entry_of_" + node.info.name, function);
    function.entryBlock = curBlock;

    curFunction = function;

    node.info.paraListInfo.forEach(scopeManager::addItem); // ??? delete?

    updateParaList(isMethod, function, node); // insert parameters

    node.stmts.accept(this);

    // ??? whether here add new cur Basic Block

    curFunction = null;
    scopeManager.popScope();
  }

  @Override
  public void visit(AtomExprNode node) {
    if (node.atom.StringConst() != null) {
      // String const
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
      BaseInfo baseInfo = scopeManager.queryName(name);

      if (baseInfo instanceof VarInfo) {
        // must load this variable.
        Pair<Value, Boolean> pair = scopeManager.queryValue(name);
        boolean isMemberVariable = pair.b;
        assert pair.a.getType() instanceof PtrType;

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
        node.value = load(address);

      } else { // function type
        // don't need to create basicBlock here.
        node.value = scopeManager.getFuncScope().info.value;
      }

    } else {
      // null
      node.value = new NullConst();
    }

  }

  private void MallocArray(NewExprNode node) {

  }

  @Override
  public void visit(NewExprNode node) {
    VarType type = (VarType) node.exprType;
    if (type.dimension == 0) {
      assert BaseType.isClassType(type);
      // node
      StructType classType = (StructType) transType(type);
      Call call = new Call(module.MallocFunction(), curBlock, new IntConst(classType.size()));
      return ;
    }
    MallocArray(node);
  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    // getelementptr
    if (BaseType.isStringType(node.callExpr.exprType)) {
      return;
    }

    if (BaseType.isArray(node.callExpr.exprType)) {
      return;
    }

    ClassInfo classInfo = scopeManager.queryClassInfo(node.callExpr.exprType.ClassName);

    if (node.exprType instanceof VarType) {
      Value strPtr = node.callExpr.value;
      assert strPtr.getType() instanceof PtrType;
      Value address = visitStructMember(classInfo, strPtr, node.member);
      if (address == null)
        throw new IRBuildError("can't find member: " + node.member + " in " + classInfo.name, node.pos);
      node.value = load(address);
    } else {
      // function call
      node.value = scopeManager.curClassScope.queryFuncInfo(node.member).value;
    }
  }

  @Override
  public void visit(BracketExprNode node) {
    node.callExpr.accept(this);
    node.index.accept(this);
    GetElePtr address = new GetElePtr(transType((VarType) node.exprType), node.callExpr.value, curBlock);
    address.addOperands(node.index.value);
    node.value = load(address);
    // type : node.callExpr.value.target, value: ?
  }

  @Override
  public void visit(FuncExprNode node) {
    node.callExpr.accept(this);
    if (!(node.callExpr.value instanceof IRFunction callFunc))
      throw new IRBuildError("not a function call", node.pos);
    Call call = new Call(callFunc, curBlock);

    if (node.callExpr instanceof MemberExprNode memberExp) {
      call.addOperands(memberExp.callExpr.value);
    }

    for (int i = 0; i < node.argumentList.size(); ++i) {
      ExprNode cur = node.argumentList.get(i);
      cur.accept(this);
      call.addOperands(cur.value);
    }
    node.value = call;
  }

  @Override
  public void visit(SelfExprNode node) {
    node.expression.accept(this);
    String op = node.opCode.equals("++") ? "+" : "-";
    Value calcRes = new Binary(op, node.expression.value, new IntConst(1), curBlock);
    store(calcRes, node.expression.value); // ??? this is not correct!

    node.value = node.expression.value;
    // x = 2, int y = x++; => x = 3, y = 2;
  }

  @Override
  public void visit(UnaryExprNode node) {
    node.expression.accept(this);
    switch (node.opCode) {
      case "+" -> node.value = node.expression.value;
      case "-" -> node.value = new Binary("-", new IntConst(0), node.expression.value, curBlock);
      case "!" -> node.value = new Binary("^", node.expression.value, new BoolConst("true"), curBlock);
      case "~" -> node.value = new Binary("^", node.expression.value, new IntConst(-1), curBlock);
      default -> {
        // ++x and --x
        String op = node.opCode.equals("++") ? "+" : "-";
        Value calcRes = new Binary(op, node.expression.value, new IntConst(1), curBlock);
        store(calcRes, node.expression.value); // ??? this is not correct
        node.value = calcRes;
      }
    }
  }

  @Override
  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    if (node.opType == BinaryExprNode.binaryOpType.Logic) {
      IRBasicBlock tmpBlock = curBlock;
      if (node.opCode.equals("&&")) {
        IRBasicBlock exitAnd = new IRBasicBlock("exitAnd", curFunction);
        IRBasicBlock ifTrue = new IRBasicBlock("calc.True", curFunction);
        new Branch(node.lhs.value, ifTrue, exitAnd, tmpBlock);

        curBlock = ifTrue;
        node.rhs.accept(this);

        curBlock = exitAnd;
        Phi phi = new Phi(new BoolType(), curBlock);
        phi.addOperands(new BoolConst("false"), tmpBlock);
        phi.addOperands(node.rhs.value, ifTrue);

        node.value = phi;
      } else {
        IRBasicBlock exitOr = new IRBasicBlock("exitOr", curFunction);
        IRBasicBlock ifFalse = new IRBasicBlock("calc.False", curFunction);
        new Branch(node.lhs.value, exitOr, ifFalse, tmpBlock);

        curBlock = ifFalse;
        node.rhs.accept(this);

        curBlock = exitOr;
        Phi phi = new Phi(new BoolType(), curBlock);
        phi.addOperands(new BoolConst("true"), tmpBlock);
        phi.addOperands(node.rhs.value, ifFalse);

        node.value = phi;
      }
      return;
    }

    node.rhs.accept(this);
    if (node.opType == BinaryExprNode.binaryOpType.Equal
            || node.opType == BinaryExprNode.binaryOpType.Compare) {
      node.value = new Icmp(node.opCode, node.lhs.value, node.rhs.value, curBlock);
    } else {
      node.value = new Binary(node.opCode, node.lhs.value, node.rhs.value, curBlock);
    }
  }

  @Override
  public void visit(AssignExprNode node) {
    node.lhs.accept(this); // lhs的load其实是不必要的? e.g. x = y;
    node.rhs.accept(this);
    store(node.lhs.value, node.rhs.value); // ??? resolver
    node.value = node.rhs.value;
  }

  @Override
  public void visit(SuiteStmtNode node) {
    scopeManager.pushScope(new SuiteScope());
    node.stmts.forEach(stmt -> stmt.accept(this));
    scopeManager.popScope();
  }

  @Override
  public void visit(IfStmtNode node) {
    IRBasicBlock ifTrue = new IRBasicBlock("if.True", curFunction);
    IRBasicBlock ifFalse = new IRBasicBlock("if.False", curFunction);
    IRBasicBlock fellow = new IRBasicBlock("if.end", curFunction);

    node.condition.accept(this);
    new Branch(node.condition.value, ifTrue, ifFalse, curBlock);

    if (node.thenStmt != null) {
      curBlock = ifTrue;
      scopeManager.pushScope(new SuiteScope());
      node.thenStmt.accept(this);
      new Branch(fellow, curBlock);
      scopeManager.popScope();
    }

    if (node.elseStmt != null) {
      curBlock = ifFalse;
      scopeManager.pushScope(new SuiteScope());
      node.elseStmt.accept(this);
      new Branch(fellow, curBlock);
      scopeManager.popScope();
    }

    curBlock = fellow;
  }

  @Override
  public void visit(ReturnStmtNode node) {
    if (node.ret != null) {
      node.ret.accept(this);
      node.value = new Ret(node.ret.value, curBlock);
    } else {
      node.value = new Ret(curBlock);
    }
  }

  @Override
  public void visit(WhileStmtNode node) {
    IRBasicBlock whileCondition = new IRBasicBlock("while.condition", curFunction);
    IRBasicBlock whileBody = new IRBasicBlock("while.body", curFunction);
    IRBasicBlock exitBlock = new IRBasicBlock("while.exit", curFunction);
    new Branch(whileCondition, curBlock);

    curBlock = whileCondition;
    node.condition.accept(this);
    new Branch(node.condition.value, whileBody, exitBlock, curBlock);

    if (node.stmt != null) {
      curBlock = whileBody;
      scopeManager.pushScope(new LoopScope());
      node.stmt.accept(this);
      new Branch(whileCondition, curBlock);
      scopeManager.popScope();
    }

    curBlock = exitBlock;
  }

  @Override
  public void visit(ForStmtNode node) {
    IRBasicBlock forCondition = new IRBasicBlock("for.condition", curFunction);
    IRBasicBlock forBody = new IRBasicBlock("for.body", curFunction);
    forStep = new IRBasicBlock("for.step", curFunction);
    forExit = new IRBasicBlock("for.exit", curFunction);

    if (node.initial != null)
      node.initial.accept(this);
    new Branch(forCondition, curBlock);

    if (node.condition != null) {
      curBlock = forCondition;
      node.condition.accept(this);
      new Branch(node.condition.value, forBody, forExit, curBlock);
    }

    if (node.stmt != null) {
      curBlock = forBody;
      scopeManager.pushScope(new LoopScope());
      node.stmt.accept(this);
      new Branch(forStep, curBlock);
      scopeManager.popScope();
    }

    if (node.step != null) {
      curBlock = forStep;
      node.step.accept(this);
      new Branch(forCondition, curBlock);
    }

    curBlock = forExit;
  }

  @Override
  public void visit(BreakContinueNode node) {
    new Branch(node.isBreak ? forExit : forStep, curBlock);
  }

  @Override
  public void visit(LambdaExprNode node) {
    throw new IRBuildError("Lambda expression not supported", node.pos);
  }

  private Value allocate(IRBaseType type) {
    return new Alloca(type, curBlock);
  }

  private Value load(Value value) {
    return new Load(value, curBlock);
  }

  private void store(Value value, Value ptr) {
    new Store(value, ptr, curBlock);
  }

  private Value visitStructMember(ClassInfo info, Value thisOperand, String member) {
    int idx = 0;
    for (var var : info.varInfos) {
      if (var.name.equals(member)) {
        GetElePtr ptr = new GetElePtr(transType(var.type), thisOperand, curBlock);
        ptr.addOperands(new IntConst(idx));
        return ptr;
      }
      ++idx;
    }
    return null;
  }

}
