package ast.expression;

import ast.ASTVisitor;
import utility.Position;

public class BracketExprNode extends ExprNode {
  public ExprNode callExpr, index;

  public BracketExprNode(ExprNode callExpr, ExprNode index, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.index = index;
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
