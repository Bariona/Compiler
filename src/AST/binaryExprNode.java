package AST;

import Utility.Position;
import Utility.Type.BaseType;

public class binaryExprNode extends ExprNode {
  public ExprNode lhs, rhs;

  public enum binaryOpType {
    Mul, Div, Mod,
    Add, Sub,
    LeftShift, RightShift,
    And, Xor, Or
  }

  public binaryOpType opCode;

  public binaryExprNode(ExprNode lhs, ExprNode rhs, binaryOpType opCode, BaseType intType, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    this.opCode = opCode;
    type = intType; // have to be Integral ?
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
