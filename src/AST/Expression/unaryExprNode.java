package AST.Expression;

import AST.ASTvisitor;
import AST.ExprNode;
import Utility.Position;

public class unaryExprNode extends ExprNode {
  public String opCode;
  public ExprNode expression;

  public unaryExprNode(String opCode, ExprNode expression, Position pos) {
    super(pos);
    this.opCode = opCode;
    this.restype = expression.restype; // Is "!" the expression.restype?
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}