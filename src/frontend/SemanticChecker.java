package frontend;

import ast.ASTVisitor;
import ast.RootNode;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import utility.Position;
import utility.StringDealer;
import utility.error.SemanticError;
import utility.error.SyntaxError;
import utility.info.*;
import utility.scope.*;
import utility.type.*;


public class SemanticChecker implements ASTVisitor {
  private final ScopeManager scopeManager = new ScopeManager();

  private void checkAssign(VarType lhs, VarType rhs, Position pos) {
    if (BaseType.isNullType(rhs)) {
      if (BaseType.isPrimitiveType(lhs)) {
        throw new SemanticError("null cannot be assigned to primitive type variable", pos);
      }
    } else {
      if (!lhs.isSame(rhs))
        throw new SemanticError("Assign expression expects same type", pos);
      if (BaseType.isClassType(lhs) && scopeManager.getClassInfo(rhs.ClassName) == null)
        throw new SemanticError(lhs.ClassName + " type is not defined! ", pos);
    }
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
      node.expr.accept(this);
      assert node.expr.exprType instanceof VarType;
      VarType exprType = (VarType) node.expr.exprType;
      checkAssign(node.info.type, exprType, node.pos);
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
    node.info.paraListInfo.forEach(scopeManager::addItem);
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
    assert node.callExpr.exprType.ClassName != null;

    ClassInfo classInfo = scopeManager.getClassInfo(node.callExpr.exprType.ClassName);

    assert classInfo != null;
//    System.out.println(classInfo.name + " " + node.member);
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
    assert node.expression != null;
    node.expression.accept(this);
    if (!BaseType.isIntType(node.expression.exprType))
      throw new SemanticError("expect INT", node.pos);
    // unsolved : iaAssignable
    if (!node.expression.isAssignable())
      throw new SemanticError("expect left value", node.pos);
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(UnaryExprNode node) {
//    System.out.println(node.pos.toString() + " " + node.opCode);
    assert node.expression != null;
    node.expression.accept(this);
    switch (node.opCode) {
      case "!":
        if (!BaseType.isBoolType(node.expression.exprType))
          throw new SemanticError("expect BOOL", node.pos);
        break;
      case "++", "--":
        if (!BaseType.isIntType(node.expression.exprType))
          throw new SemanticError("expect INT", node.pos);
        if (!node.expression.isAssignable())
          throw new SemanticError("expect variable", node.pos);
        break;
      case "+", "-", "~":
        if (!BaseType.isIntType(node.expression.exprType))
          throw new SemanticError("expect INT", node.pos);
        break;
      default:
        throw new SyntaxError("Unary undifined character", node.pos);
    }
    node.exprType = node.expression.exprType.clone();
  }

  @Override
  public void visit(AssignExprNode node) {
    assert node.lhs != null && node.rhs != null;
    node.lhs.accept(this);
    node.rhs.accept(this);
    if (!node.lhs.isAssignable())
      throw new SemanticError("Assign expression expects Left value", node.lhs.pos);
    checkAssign((VarType) node.lhs.exprType, (VarType) node.rhs.exprType, node.pos);
    node.exprType = node.lhs.exprType.clone();
  }

  @Override
  public void visit(BinaryExprNode node) {
    assert node.lhs != null && node.rhs != null;
    node.lhs.accept(this);
    node.rhs.accept(this);

    if (!node.lhs.exprType.isSame(node.rhs.exprType))
      throw new SemanticError("left/right ExprType not match", node.pos);
    boolean flag = false;
    if (BaseType.isStringType(node.lhs.exprType)) {
      // String Type
      if (node.opCode.equals("+")) { // string connect
        node.exprType = new VarType(BaseType.BuiltinType.STRING);
      } else if (node.opType != BinaryExprNode.binaryOpType.Arithmetic) {
        node.exprType = new VarType(BaseType.BuiltinType.BOOL);
      } else flag = true;
    } else {
      // Other Type
      if (node.opType == BinaryExprNode.binaryOpType.Arithmetic) {
        if (!BaseType.isIntType(node.lhs.exprType)) { // arithmatic
          flag = true;
        } else node.exprType = new VarType(BaseType.BuiltinType.INT);
      } else if (node.opType == BinaryExprNode.binaryOpType.Logic) {
        if (!BaseType.isBoolType(node.lhs.exprType)) {
          flag = true;
        } else node.exprType = new VarType(BaseType.BuiltinType.BOOL);
      } else { // compare & equal
        node.exprType = new VarType(BaseType.BuiltinType.BOOL);
      }
    }
    if (flag) throw new SemanticError("type not correct", node.pos);
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
    if (!BaseType.isBoolType(condi))
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
    if (node.ret == null) {
      if (!funcScope.info.isConstructor && !BaseType.isVoidType(funcScope.info.funcType.retType))
        throw new SemanticError("Return should not be empty", node.pos);
      return ;
    }

    node.ret.accept(this);
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
