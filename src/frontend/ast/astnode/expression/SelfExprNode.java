package frontend.ast.astnode.expression;

import frontend.ast.ASTVisitor;
import utility.Position;

public class SelfExprNode extends ExprNode {
  public String opCode;
  public ExprNode expression;

  public SelfExprNode(String opCode, ExprNode expression, Position pos) {
    super(pos);
    this.expression = expression;
    this.opCode = opCode;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
