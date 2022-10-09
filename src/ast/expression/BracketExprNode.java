package ast.expression;

import ast.ASTVisitor;
import ast.ExprNode;
import utility.Position;

public class BracketExprNode extends ExprNode {
  public ExprNode callExpr, index;

  public BracketExprNode(ExprNode callExpr, ExprNode index, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.index = index;
    this.exprType = null; // ??????
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
