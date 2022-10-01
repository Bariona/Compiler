package AST;

import Utility.Position;
import Utility.Type.BaseType;

public class cmpExprNode extends ExprNode {
  public ExprNode lhs, rhs;

  public enum cmpOpType {
    Less, Leq, Greater, Geq, Equal, NotEqual
  }

  public cmpOpType opCode;

  public cmpExprNode(ExprNode lhs, ExprNode rhs, cmpOpType opCode, BaseType boolType, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    this.opCode = opCode;
    type = boolType;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
