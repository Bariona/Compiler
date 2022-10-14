package ast.expression;

import ast.ASTVisitor;
import utility.Position;
import utility.type.VarType;

public class MemberExprNode extends ExprNode {
  public ExprNode callExpr;
  public String member;

  public MemberExprNode(ExprNode callExpr, String member, Position pos) {
    super(pos);
    this.member = member;
    this.callExpr = callExpr;
  }

  @Override
  public boolean isAssignable() {
    return exprType instanceof VarType;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
