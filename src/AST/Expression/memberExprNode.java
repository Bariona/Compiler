package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Utility.Position;

public class memberExprNode extends ExprNode {
  public ExprNode callExpr;
  public String member;

  public memberExprNode(ExprNode callExpr, String member, Position pos) {
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
