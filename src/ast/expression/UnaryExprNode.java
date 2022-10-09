package ast.expression;

import ast.ASTVisitor;
import ast.ExprNode;
import utility.Position;

public class UnaryExprNode extends ExprNode {
  public String opCode;
  public ExprNode expression;

  public UnaryExprNode(String opCode, ExprNode expression, Position pos) {
    super(pos);
    this.opCode = opCode;
    this.exprType = expression.exprType; // Is "!" the expression.restype?
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
