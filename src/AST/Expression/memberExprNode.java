package AST.Expression;

import AST.ASTvisitor;
import AST.ExprNode;
import Utility.Position;

public class memberExprNode extends ExprNode {
  public ExprNode callExpr;
  public String member;

  public memberExprNode(ExprNode callExpr, String member, Position pos) {
    super(pos);
    this.member = member;
    this.callExpr = callExpr;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
