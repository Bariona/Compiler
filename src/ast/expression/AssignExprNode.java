package ast.expression;

import ast.*;
import utility.Position;

public class AssignExprNode extends ExprNode {
  public ExprNode lhs, rhs;

  public AssignExprNode(ExprNode lhs, ExprNode rhs, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    exprType = lhs.exprType; // expression'res type
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}