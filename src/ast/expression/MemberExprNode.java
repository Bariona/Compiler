package ast.expression;

import ast.ASTVisitor;
import ast.ExprNode;
import utility.Position;

public class MemberExprNode extends ExprNode {
  public ExprNode callExpr;
  public String member;

  public MemberExprNode(ExprNode callExpr, String member, Position pos) {
    super(pos);
    this.member = member;
    this.callExpr = callExpr;
    // this.restype???
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
