package AST.Expression;

import AST.ASTvisitor;
import AST.ExprNode;
import Utility.Position;

public class bracketExprNode extends ExprNode {
  public ExprNode callExpr, index;

  public bracketExprNode(ExprNode callExpr, ExprNode index, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.index = index;
    this.restype = null; // ??????
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
