package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Utility.Position;

public class bracketExprNode extends ExprNode {
  public ExprNode callExpr, index;

  public bracketExprNode(ExprNode callExpr, ExprNode index, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.index = index;
    this.ExprType = null; // ??????
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
