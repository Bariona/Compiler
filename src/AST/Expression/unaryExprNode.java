package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Utility.Position;

public class unaryExprNode extends ExprNode {
  public String opCode;
  public ExprNode expression;

  public unaryExprNode(String opCode, ExprNode expression, Position pos) {
    super(pos);
    this.opCode = opCode;
    this.ExprType = expression.ExprType; // Is "!" the expression.restype?
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
