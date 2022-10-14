package frontend;

import ast.ASTVisitor;
import ast.RootNode;
import ast.definition.ClassDefNode;
import ast.definition.FuncDefNode;
import ast.definition.VarDefNode;
import ast.definition.VarSingleDefNode;
import ast.expression.*;
import ast.statement.*;
import utility.error.SemanticError;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.scope.ClassScope;
import utility.scope.FuncScope;
import utility.scope.SuiteScope;
import utility.type.BaseType;
import utility.type.FuncType;
import utility.type.VarType;


public class SemanticChecker implements ASTVisitor {
  private ScopeManager scopeManager = new ScopeManager();

  boolean isBoolType(BaseType it) {
    if (it instanceof FuncType) return false;
    return ((VarType) it).isSame(new VarType(BaseType.BultinType.BOOL));
  }

  @Override
  public void visit(RootNode node) {
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
    // unsolved: 空的expr怎么办?
      node.expr.accept(this);
      BaseType exprType = node.expr.exprType;
      if (!(exprType instanceof VarType))
        throw new SemanticError("type is not a variable type!", node.pos);
      if (!node.info.type.isSame((VarType) exprType))
        throw new SemanticError("type not match!", node.pos);
    }
    scopeManager.curScope().addItem(node.info);
  }

  @Override
  public void visit(ClassDefNode node) {
    scopeManager.pushScope(node.scope);
    // unsolved : 要实现forward reference, 在scope里提前additem
    node.varDefs.forEach(var -> var.accept(this));
    node.funcDefs.forEach(func -> func.accept(this));
    assert scopeManager.curScope() instanceof ClassScope;
    scopeManager.popScope();
  }

  @Override
  public void visit(FuncDefNode node) {
    scopeManager.pushScope(new FuncScope(node.info));
    node.info.parameterList.forEach(para -> scopeManager.curScope().addItem(para));
    node.stmts.accept(this);
    scopeManager.popScope();
  }

  @Override
  public void visit(AtomExprNode node) {
    if (node.atom.StringConst() != null) {
      node.exprType = new VarType(BaseType.BultinType.STRING);

    } else if (node.atom.Decimal() != null) {
      node.exprType = new VarType(BaseType.BultinType.INT);

    } else if (node.atom.This() != null) {
      // unsolved: 加一个Class Name, scope manager需要classScope的定义
      node.exprType = new VarType(BaseType.BultinType.CLASS);

    } else if (node.atom.True() != null || node.atom.False() != null) {
      node.exprType = new VarType(BaseType.BultinType.BOOL);

    } else if (node.atom.Identifier() != null) {
      String name = node.atom.Identifier().toString();
      VarInfo varInfo = scopeManager.queryVarName(name);
      FuncInfo funcInfo = scopeManager.queryFuncName(name);

      if (varInfo != null) {
        node.exprType = varInfo.type.clone();
      } else if (funcInfo != null) {
        node.exprType = funcInfo.type.clone();
      } else throw new SemanticError("AtomExpr error", node.pos);

    } else {
      // null 类型
      node.exprType = null;
    }
  }

  @Override
  public void visit(NewExprNode node) {
    for (ExprNode expr : node.dimensionExpr) {
      if (expr == null) continue;
      expr.accept(this);
      if (expr.exprType.bultinType != BaseType.BultinType.INT)
        throw new SemanticError("bracket inside should be Integral", expr.pos);
    }
  }

  @Override
  public void visit(MemberExprNode node) {
    node.callExpr.accept(this);
    if (node.callExpr.exprType.bultinType != BaseType.BultinType.CLASS)
      throw new SemanticError("MemberExpr left handside should be class", node.callExpr.pos);
    VarInfo varInfo = scopeManager.queryVarName(node.member);
    FuncInfo funcInfo = scopeManager.queryFuncName(node.member);

    if(varInfo != null) {
      node.exprType = varInfo.type.clone();
    } else if(funcInfo != null) {
      node.exprType = funcInfo.type.clone();
    } else {
      throw new SemanticError("no such member has defined", node.pos);
    }
  }

  @Override
  public void visit(BracketExprNode node) { // unsolved: 从底层优化BracketNode? 改成index vector?
    node.index.accept(this);
    if (node.index.exprType.bultinType != BaseType.BultinType.INT)
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
    node.argumentList.forEach(arg -> arg.accept(this));

  }

  @Override
  public void visit(SelfExprNode node) {

  }

  @Override
  public void visit(UnaryExprNode node) {

  }

  @Override
  public void visit(BinaryExprNode node) {

  }

  @Override
  public void visit(AssignExprNode node) {

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
     FuncScope funcScope = (FuncScope) scopeManager.getFuncScope();
     if (funcScope == null)
      throw new SemanticError("Return should be in a function", node.pos);
     node.ret.accept(this);
     if (!node.ret.exprType.isSame(funcScope.info.type))
       throw new SemanticError("Return type not match", node.ret.pos);
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
    node.initial.accept(this);
    node.condition.accept(this);
    if (!isBoolType(node.condition.exprType))
      throw new SemanticError("condition expr should be bool type", node.condition.pos);
    node.step.accept(this);

    scopeManager.pushScope(new SuiteScope());
    node.stmt.accept(this);
    scopeManager.popScope();
    --scopeManager.forLoopCnt;
  }
}
