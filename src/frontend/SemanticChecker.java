package frontend;

import ast.ASTVisitor;
import ast.RootNode;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import utility.error.SemanticError;
import utility.info.*;
import utility.scope.*;
import utility.type.*;


public class SemanticChecker implements ASTVisitor {
  private ScopeManager scopeManager = new ScopeManager();

  boolean isBoolType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BaseType.BuiltinType.BOOL));
  }

  boolean isIntType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BaseType.BuiltinType.INT));
  }

  @Override
  public void visit(RootNode node) {
    scopeManager.pushScope(node.scope);
    node.scope.print();
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
      // unsolved: 空的expr怎么办?
      node.expr.accept(this);
      BaseType exprType = node.expr.exprType;
      assert exprType != null;

      if (!(exprType instanceof VarType))
        throw new SemanticError("type is not a variable type!", node.pos);
      if (!node.info.type.isSame(exprType))
        throw new SemanticError("type not match!", node.pos);
    }
    assert node.info.type != null;
    // unsolved: 会不会和Root scope 重命名了?
    scopeManager.addItem(node.info);
  }

  @Override
  public void visit(ClassDefNode node) {
    if (!(scopeManager.curScope() instanceof RootScope))
      throw new SemanticError("Class should not be defined here", node.pos);
    scopeManager.pushScope(node.scope);
    // unsolved : 要实现forward reference, 在scope里提前additem
    node.varDefs.forEach(var -> var.accept(this));
    node.funcDefs.forEach(func -> func.accept(this));
    assert scopeManager.curScope() instanceof ClassScope;
    scopeManager.popScope();
  }

  @Override
  public void visit(FuncDefNode node) {
    if (!(scopeManager.curScope() instanceof RootScope) && !(scopeManager.curScope() instanceof ClassScope))
      throw new SemanticError("Function should not be defined here", node.pos);
    scopeManager.pushScope(new FuncScope(node.info));
    node.info.paraListInfo.forEach(para -> scopeManager.addItem(para));
    if (node.stmts != null)
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
      // unsolved: 加一个Class Name, scope manager需要classScope的定义
      node.exprType = new VarType(BaseType.BuiltinType.CLASS);
      node.exprType.ClassName = scopeManager.getClassName();

    } else if (node.atom.True() != null || node.atom.False() != null) {
      node.exprType = new VarType(BaseType.BuiltinType.BOOL);

    } else if (node.atom.Identifier() != null) {
      String name = node.atom.Identifier().toString();
      VarInfo varInfo = scopeManager.queryVarName(name);
      FuncInfo funcInfo = scopeManager.queryFuncName(name);

      if (varInfo != null) {
        node.exprType = varInfo.type.clone();
      } else if (funcInfo != null) {
        node.exprType = funcInfo.type.clone();
      } else throw new SemanticError("Atom not defined", node.pos);

    } else {
      // null 类型
      node.exprType = null;
    }
  }

  @Override
  public void visit(NewExprNode node) {
    assert node.exprType != null;
    for (ExprNode expr : node.dimensionExpr) {
      if (expr == null) continue;
      expr.accept(this);
      if (expr.exprType.builtinType != BaseType.BuiltinType.INT)
        throw new SemanticError("bracket inside should be Integral", expr.pos);
    }
  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    if (node.callExpr.exprType.builtinType == BaseType.BuiltinType.STRING) {
      // unsolved: String type
      return;
    }
    if (node.callExpr.exprType.builtinType != BaseType.BuiltinType.CLASS)
      throw new SemanticError("MemberExpr left handside should be class", node.callExpr.pos);
    assert node.callExpr.exprType.ClassName != null;

    ClassInfo classInfo = scopeManager.getClassInfo(node.callExpr.exprType.ClassName);

    assert classInfo != null;
    VarInfo varInfo = classInfo.findVarInfo(node.member);
    FuncInfo funcInfo = classInfo.findFuncInfo(node.member);

    if (varInfo != null) {
      node.exprType = varInfo.type.clone();
    } else if (funcInfo != null) {
      node.exprType = funcInfo.type.clone();
    } else {
      throw new SemanticError("no such member has defined", node.pos);
    }
  }

  @Override
  public void visit(BracketExprNode node) { // unsolved: 从底层优化BracketNode? 改成index vector?
    node.index.accept(this);
    if (node.index.exprType.builtinType != BaseType.BuiltinType.INT)
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
  public void visit(FuncExprNode node) {
    node.callExpr.accept(this);
    if (node.callExpr.exprType instanceof VarType)
      throw new SemanticError("not a function", node.callExpr.pos);
    FuncType ret = (FuncType) node.callExpr.exprType;
    if (node.argumentList.size() != ret.paraListType.size())
      throw new SemanticError("Function parameter's number not correct", node.pos);
    for (int i = 0; i < node.argumentList.size(); ++i) {
      ExprNode cur = node.argumentList.get(i);
      cur.accept(this);
      if (!cur.exprType.isSame(ret.paraListType.get(i)))
        throw new SemanticError("Function parameter's type not correct", node.pos);
    }
//    node.argumentList.forEach(arg -> arg.accept(this));
    node.exprType = node.callExpr.exprType.clone();
  }

  @Override
  public void visit(SelfExprNode node) {
    assert node.expression != null;
    node.expression.accept(this);
    if (!isIntType(node.expression.exprType))
      throw new SemanticError("expect INT", node.pos);
    // unsolved : iaAssignable
    if (!node.expression.isAssignable())
      throw new SemanticError("expect variable", node.pos);
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(UnaryExprNode node) {
    assert node.expression != null;
    node.expression.accept(this);
    if (node.opCode.equals("!")) {
      if (!isBoolType(node.expression.exprType))
        throw new SemanticError("expect BOOL", node.pos);
    } else if (node.opCode.equals("++") || node.opCode.equals("--")) {
      if (!isIntType(node.expression.exprType))
        throw new SemanticError("expect INT", node.pos);
      if (!node.expression.isAssignable())
        throw new SemanticError("expect variable", node.pos);
    } else if (node.opCode.equals("+") || node.opCode.equals("-") || node.opCode.equals("~")) {
      if (!isIntType(node.expression.exprType))
        throw new SemanticError("expect INT", node.pos);
    }
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(BinaryExprNode node) {
    assert node.lhs != null && node.rhs != null;
    node.lhs.accept(this);
    node.rhs.accept(this);
    boolean flag = false;
    if (node.opType == BinaryExprNode.binaryOpType.Compare) {
      if (!isBoolType(node.lhs.exprType) || !isBoolType(node.rhs.exprType)) {
        flag = true;
      } else node.exprType = new VarType(BaseType.BuiltinType.BOOL);
    } else {
      if (!isIntType(node.lhs.exprType) || !isIntType(node.rhs.exprType)) {
        flag = true;
      } else node.exprType = new VarType(BaseType.BuiltinType.INT);
      // unsolved: String 的拼接
    }
    if (flag) throw new SemanticError("type not correct", node.pos);
  }

  @Override
  public void visit(AssignExprNode node) {
    assert node.lhs != null && node.rhs != null;
    node.lhs.accept(this);
    node.rhs.accept(this);
    if (!node.lhs.isAssignable())
      throw new SemanticError("expect Left value", node.lhs.pos);
    if (!node.lhs.exprType.isSame(node.rhs.exprType))
      throw new SemanticError("expect Same type", node.pos);
    node.exprType = node.lhs.exprType.clone();
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
    if (node.condition.exprType instanceof FuncType)
      throw new SemanticError("condition res type error", node.condition.pos);
    VarType condi = (VarType) node.condition.exprType;
    if (!isBoolType(condi))
      throw new SemanticError("condition res type error", node.condition.pos);

    // unsolved: 好像不需要push scope
//    scopeManager.pushScope(new SuiteScope());
    node.thenStmt.accept(this);
//    scopeManager.popScope();
    if (node.elseStmt != null) {
//      scopeManager.pushScope(new SuiteScope());
      node.elseStmt.accept(this);
//      scopeManager.popScope();
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
    node.ret.accept(this);
    if (!node.ret.exprType.isSame(funcScope.info.type.retType))
      throw new SemanticError("Return type not match", node.ret.pos);
    // unsolved: int main()'s return
  }

  @Override
  public void visit(WhileStmtNode node) {
    node.condition.accept(this);
    if (!isBoolType(node.condition.exprType))
      throw new SemanticError("condition expr should be bool type", node.condition.pos);
    node.stmt.accept(this);
  }

  @Override
  public void visit(ForStmtNode node) {
    ++scopeManager.forLoopCnt;
    if (node.initial != null) node.initial.accept(this);
    if (node.condition != null) {
      node.condition.accept(this);
      if (!isBoolType(node.condition.exprType))
        throw new SemanticError("condition expr should be bool type", node.condition.pos);
    }
    if (node.step != null) node.step.accept(this);

    scopeManager.pushScope(new SuiteScope());
    node.stmt.accept(this);
    scopeManager.popScope();
    --scopeManager.forLoopCnt;
  }
}
