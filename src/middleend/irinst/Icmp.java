package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.BoolType;

public class Icmp extends IRBaseInst {
  String opcode;

  public Icmp(String op, Value op1, Value op2, IRBasicBlock parenBlock) {
    super(op, new BoolType(), parenBlock);
    this.opcode = op;

    assert op1.getType().match(op2.getType());
    addOperands(op1);
    addOperands(op2);
  }

  private String Trans(String opcode) {
    String ret = switch (opcode) {
      case "==" -> "eq";
      case "!=" -> "ne";
      case ">=" -> "sge";
      case "<=" -> "sle";
      case ">"  -> "sgt";
      case "<"  -> "slt";
      default -> null;
    };
    return ret;
  }

  @Override
  public String toString() {
    return getName() + " = icmp " + Trans(opcode) + " " + getOperand(0).getTypeAndName() + " " + getOperand(1).getName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
