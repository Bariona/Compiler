package frontend.ast.astnode.expression;

import frontend.ast.ASTVisitor;
import utility.Position;

public class AssignExprNode extends ExprNode {
  public ExprNode lhs, rhs;

  public AssignExprNode(ExprNode lhs, ExprNode rhs, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public boolean isAssignable() {
    return true;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
