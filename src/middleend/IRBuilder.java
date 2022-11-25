package middleend;

import frontend.ast.ASTVisitor;
import frontend.ast.RootNode;
import frontend.ast.definition.*;
import frontend.ast.expression.*;
import frontend.ast.statement.*;
import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;
import middleend.irinst.*;
import middleend.irtype.*;
import middleend.irtype.FuncType;
import middleend.operands.BoolConst;
import middleend.operands.IntConst;
import middleend.operands.NullConst;
import org.antlr.v4.runtime.misc.Pair;
import utility.info.BaseInfo;
import utility.info.ClassInfo;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.scope.FuncScope;
import utility.scope.RootScope;
import utility.scope.ScopeManager;
import utility.type.*;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {
  private final ScopeManager scopeManager = new ScopeManager();
  private IRBasicBlock curBlock, initBlock;
  private final IRBasicBlock globalInitBlock;

  private final HashMap<String, StructType> structCollection = new HashMap<>();
  private final HashMap<String, FuncType> funcCollection = new HashMap<>();

  public IRBuilder(RootNode root) {
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

    // ??? not finished: include array / class
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
        // add class.method
        for (var funcInfo : clsNode.info.funcInfos) {
          FuncType function = new FuncType(transType(funcInfo.funcType.retType));
          for (var para : funcInfo.paraListInfo)
            function.addParaType(transType(para.type));
          funcCollection.put(name + "." + funcInfo.name, function);
        }

      }
    }

    // Function register information
    {
      for (var nexNode : node.defs) {
        if (!(nexNode instanceof FuncDefNode funcNode))
          continue;
        FuncType function = new FuncType(transType(funcNode.info.funcType.retType));
        for (var para : funcNode.info.paraListInfo)
          function.addParaType(transType(para.type));
        funcCollection.put(funcNode.info.name, function);
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
    String funcName = node.info.name;
    FuncType funcType = funcCollection.get(isMethod ? (scopeManager.getCurClassName() + "." + funcName) : funcName);
    IRFunction function = new IRFunction(node.info.name, funcType);
    curBlock = new IRBasicBlock("entry_of_" + node.info.name, function);
    function.entryBlock = curBlock;

    node.info.paraListInfo.forEach(scopeManager::addItem); // ??? delete?

    updateParaList(isMethod, function, node); // insert parameters

    node.stmts.accept(this);

    node.info.value =  node.value = function;
    // ??? whether here add new cur Basic Block

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
      } else {
        System.out.println("\"this\" is not a IRFunction ?? ");
        assert false;
      }

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
          ClassInfo curClass = scopeManager.getCurClassInfo();
          Value curFunc = scopeManager.getFuncScope().info.value;
          Value thisOperand = ((IRFunction) curFunc).getOperand(0);
          address = visitStructMember(curClass, thisOperand, name);

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

  @Override
  public void visit(NewExprNode it) {

  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    // getelementptr
  }

  @Override
  public void visit(BracketExprNode node) {
    node.index.accept(this);
    node.callExpr.accept(this);
    // type : node.callExpr.value.target, value: ?
  }

  @Override
  public void visit(FuncExprNode node) {
    node.callExpr.accept(this);
  }

  @Override
  public void visit(SelfExprNode it) {

  }

  @Override
  public void visit(UnaryExprNode it) {

  }

  @Override
  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    node.rhs.accept(this);

    if (node.opType == BinaryExprNode.binaryOpType.Equal || node.opType == BinaryExprNode.binaryOpType.Compare) {
      node.value = new Icmp(node.opCode, node.lhs.value, node.rhs.value, curBlock);
    } else {
      node.value = new Binary(node.opCode, node.lhs.value, node.rhs.value, curBlock);
    }
  }

  @Override
  public void visit(AssignExprNode node) {

  }

  @Override
  public void visit(SuiteStmtNode node) {

  }

  @Override
  public void visit(IfStmtNode it) {

  }

  @Override
  public void visit(BreakContinueNode it) {

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
  public void visit(WhileStmtNode it) {

  }

  @Override
  public void visit(ForStmtNode it) {

  }

  @Override
  public void visit(LambdaExprNode it) {}

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
    System.out.println("can't find member: " + member + " in " + info.name);
    assert false;
    return null;
    // GetElePtr ptr = new GetElePtr()
  }

}
