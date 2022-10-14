package ast.expression;

import ast.ASTVisitor;
import utility.Position;

public class UnaryExprNode extends ExprNode {
  public String opCode;
  public ExprNode expression;

  public UnaryExprNode(String opCode, ExprNode expression, Position pos) {
    super(pos);
    this.opCode = opCode;
  }

  @Override
  public boolean isAssignable() {
    return opCode == "++" || opCode == "--";
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
