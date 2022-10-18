package frontend;

import ast.ASTVisitor;
import ast.RootNode;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import utility.Position;
import utility.StringDealer;
import utility.error.SemanticError;
import utility.info.*;
import utility.scope.*;
import utility.type.*;


public class SemanticChecker implements ASTVisitor {
  private final ScopeManager scopeManager = new ScopeManager();

  @Override
  public void visit(RootNode node) {
//    node.scope.print();
    scopeManager.pushScope(node.scope);
    node.defs.forEach(it -> it.accept(this));
    scopeManager.popScope();
  }

  @Override
  public void visit(VarDefNode node) {
    node.varList.forEach(it -> it.accept(this));
  }

  @Override
  public void visit(VarSingleDefNode node) {
    if (node.expr != null) {
      node.expr.accept(this);
      assert node.expr.exprType instanceof VarType;
      VarType exprType = (VarType) node.expr.exprType;
      TypeChecker.assignCheck(node.info.type, exprType, node.pos, scopeManager);
    }
    VarType t = node.info.type;
    assert t != null;
    if (t.builtinType == BaseType.BuiltinType.FUNC || t.builtinType == BaseType.BuiltinType.NULL || t.builtinType == BaseType.BuiltinType.VOID)
      throw new SemanticError("Variable type error", node.pos);
    scopeManager.addItem(node.info);
  }

  @Override
  public void visit(ClassDefNode node) {
    if (!(scopeManager.curScope() instanceof RootScope))
      throw new SemanticError("Class should not be defined here", node.pos);
    scopeManager.pushScope(node.scope);
    node.varDefs.forEach(var -> var.accept(this));
    node.funcDefs.forEach(func -> func.accept(this));
    scopeManager.popScope();
  }

  @Override
  public void visit(FuncDefNode node) {
    scopeManager.pushScope(new FuncScope(node.info));
    node.info.paraListInfo.forEach(scopeManager::addItem);
    node.stmts.accept(this);
    scopeManager.popScope();
  }

  @Override
  public void visit(AtomExprNode node) {
    if (node.atom.StringConst() != null) {
      node.exprType = new VarType(BaseType.BuiltinType.STRING);

    } else if (node.atom.Decimal() != null) {
      node.exprType = new VarType(BaseType.BuiltinType.INT);

    } else if (node.atom.This() != null) {
      node.exprType = new VarType(BaseType.BuiltinType.CLASS);
      node.exprType.ClassName = scopeManager.getClassName();

    } else if (node.atom.True() != null || node.atom.False() != null) {
      node.exprType = new VarType(BaseType.BuiltinType.BOOL);

    } else if (node.atom.Identifier() != null) {
      String name = node.atom.Identifier().toString();
      BaseInfo info = scopeManager.queryName(name);

      if (info instanceof VarInfo varInfo) {
        node.exprType = varInfo.type.clone();
      } else if (info instanceof FuncInfo funcInfo) {
        node.exprType = funcInfo.funcType.clone();
      } else throw new SemanticError("Atom not defined", node.pos);

    } else {
      // null 类型
      node.exprType = new VarType(BaseType.BuiltinType.NULL);
    }
  }

  @Override
  public void visit(NewExprNode node) {
    assert node.exprType != null;
    for (ExprNode expr : node.dimensionExpr) {
      if (expr == null) continue;
      expr.accept(this);
      if (!BaseType.isIntType(expr.exprType))
        throw new SemanticError("bracket inside should be Integral", expr.pos);
    }
  }

  @Override
  public void visit(BracketExprNode node) { // unsolved: 从底层优化BracketNode? 改成index vector?
    node.index.accept(this);
    if (!BaseType.isIntType(node.index.exprType))
      throw new SemanticError("bracket index must be integral", node.index.pos);
    node.callExpr.accept(this);
    node.exprType = node.callExpr.exprType.clone();
    VarType ref = (VarType) node.exprType;
    ref.dimension -= 1;
    if (ref.dimension < 0) {
      throw new SemanticError("dimension not enough", node.index.pos);
    }
  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    if (BaseType.isStringType(node.callExpr.exprType)) { // String type
      node.exprType = StringDealer.matchFunction(node.member);
      if (node.exprType == null)
        throw new SemanticError("no such String function", node.pos);
      return ;
    }

    if (BaseType.isArray(node.callExpr.exprType)) { // array
      if (node.member.equals("size")) {
        node.exprType = new FuncType(BaseType.BuiltinType.INT);
      } else throw new SemanticError("no such Array function", node.pos);
      return ;
    }

    if (node.callExpr.exprType.builtinType != BaseType.BuiltinType.CLASS)
      throw new SemanticError("MemberExpr left handside should be class", node.callExpr.pos);

    ClassInfo classInfo = scopeManager.getClassInfo(node.callExpr.exprType.ClassName);
    VarInfo varInfo = classInfo.findVarInfo(node.member);
    FuncInfo funcInfo = classInfo.findFuncInfo(node.member);

    if (varInfo != null) {
      node.exprType = varInfo.type.clone();
    } else if (funcInfo != null) {
      node.exprType = funcInfo.funcType.clone();
//      System.out.println(funcInfo);
//      System.out.println(funcInfo.paraListInfo.size());
//      System.out.println((funcInfo.funcType).paraListType.size());
    } else {
      throw new SemanticError("no such member has defined", node.pos);
    }
  }

  @Override
  public void visit(LambdaExprNode node) {
    scopeManager.pushScope(new FuncScope(node.info));
    node.info.paraListInfo.forEach(scopeManager::addItem);

    scopeManager.lambdaReturn = null;
    node.suite.accept(this);
    node.exprType = scopeManager.lambdaReturn.clone();

    scopeManager.popScope();
    for (int i = 0; i < node.argumentList.size(); ++i) {
      ExprNode cur = node.argumentList.get(i);
      cur.accept(this);
//      System.out.println(node.exprType.typename() + " " + node.info.paraListInfo.get(i).type.typename());
      if (!cur.exprType.isSame(node.info.paraListInfo.get(i).type))
        throw new SemanticError("Lambda parameter's type not correct", node.pos);
    }
  }

  @Override
  public void visit(FuncExprNode node) {
    node.callExpr.accept(this);
    if (!(node.callExpr.exprType instanceof FuncType callExp))
      throw new SemanticError("not a function", node.callExpr.pos);
    if (node.argumentList.size() != callExp.paraListType.size()) {
      System.out.printf("given para num: %d, required para num: %d\n", node.argumentList.size(), callExp.paraListType.size());
      throw new SemanticError("Function parameter's number not correct", node.pos);
    }
    for (int i = 0; i < node.argumentList.size(); ++i) {
      ExprNode cur = node.argumentList.get(i);
      cur.accept(this);
      if (!cur.exprType.isSame(callExp.paraListType.get(i)))
        throw new SemanticError("Function parameter's type not correct", node.pos);
    }
    node.exprType = callExp.retType.clone();
  }

  @Override
  public void visit(SelfExprNode node) {
    node.expression.accept(this);
    TypeChecker.selfCheck(node);
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(UnaryExprNode node) {
    node.expression.accept(this);
    TypeChecker.unaryCheck(node);
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(AssignExprNode node) {
    node.lhs.accept(this);
    node.rhs.accept(this);
    if (!node.lhs.isAssignable())
      throw new SemanticError("Assign expression expects Left value", node.lhs.pos);
    TypeChecker.assignCheck((VarType) node.lhs.exprType, (VarType) node.rhs.exprType, node.pos, scopeManager);
    node.exprType = node.lhs.exprType.clone();
  }

  @Override
  public void visit(BinaryExprNode node) {
    node.lhs.accept(this);
    node.rhs.accept(this);

    TypeChecker.binaryCheck(node);

    if (node.opType == BinaryExprNode.binaryOpType.Equal || node.opType == BinaryExprNode.binaryOpType.Compare) {
      node.exprType = new VarType(BaseType.BuiltinType.BOOL);
    } else node.exprType = node.lhs.exprType.clone();
  }

  @Override
  public void visit(SuiteStmtNode node) {
    scopeManager.pushScope(new SuiteScope());
    node.stmts.forEach(stmt -> stmt.accept(this));
    scopeManager.popScope();
  }

  @Override
  public void visit(IfStmtNode node) {
    node.condition.accept(this);
    if (!BaseType.isBoolType(node.condition.exprType))
      throw new SemanticError("condition res type error", node.condition.pos);

    if (node.thenStmt != null) {
      scopeManager.pushScope(new SuiteScope());
      node.thenStmt.accept(this);
      scopeManager.popScope();
    }

    if (node.elseStmt != null) {
      scopeManager.pushScope(new SuiteScope());
      node.elseStmt.accept(this);
      scopeManager.popScope();
    }
  }

  @Override
  public void visit(BreakContinueNode node) {
    if (!scopeManager.isInForLoop())
      throw new SemanticError("Break/Continue should be in a loop", node.pos);
  }

  @Override
  public void visit(ReturnStmtNode node) {
    FuncScope funcScope = scopeManager.getFuncScope();
    if (funcScope == null)
      throw new SemanticError("Return should be in a function", node.pos);

    if (node.ret != null) node.ret.accept(this);

    if (funcScope.info.name.equals("Lambda")) { // lambda
      VarType ret;
      if (node.ret == null) {
        ret = new VarType(BaseType.BuiltinType.VOID);
      } else ret = (VarType) node.ret.exprType.clone();

      if (scopeManager.lambdaReturn == null)  {
        scopeManager.lambdaReturn = ret;
      } else if (!scopeManager.lambdaReturn.isSame(ret))
        throw new SemanticError("lambda return type not same", node.pos);
      return ;
    }


    if (node.ret == null) { // case "return;":
      if (!funcScope.info.isConstructor && !BaseType.isVoidType(funcScope.info.funcType.retType))
        throw new SemanticError("Return should not be empty", node.pos);
      return ;
    }

    if (!node.ret.exprType.isSame(funcScope.info.funcType.retType))
      throw new SemanticError("Return type not match", node.ret.pos);
  }

  @Override
  public void visit(WhileStmtNode node) {
    ++scopeManager.forLoopCnt;
    node.condition.accept(this);
    if (!BaseType.isBoolType(node.condition.exprType))
      throw new SemanticError("condition expr should be bool type", node.condition.pos);
    if (node.stmt != null) {
      scopeManager.pushScope(new LoopScope());
      node.stmt.accept(this);
      scopeManager.popScope();
    }
    --scopeManager.forLoopCnt;
  }

  @Override
  public void visit(ForStmtNode node) {
    ++scopeManager.forLoopCnt;
    if (node.initial != null) node.initial.accept(this);
    if (node.condition != null) {
      node.condition.accept(this);
      if (!BaseType.isBoolType(node.condition.exprType))
        throw new SemanticError("condition expr should be bool type", node.condition.pos);
    }
    if (node.step != null) node.step.accept(this);

    if (node.stmt != null) {
      scopeManager.pushScope(new LoopScope());
      node.stmt.accept(this);
      scopeManager.popScope();
    }
    --scopeManager.forLoopCnt;
  }

}
