package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.BoolType;

public class Icmp extends IRBaseInst {
  String opcode;

  public Icmp(String op, Value op1, Value op2, IRBlock parenBlock) {
    super(op, new BoolType(), parenBlock);
    this.opcode = op;

//    assert op1.getType().match(op2.getType());
    addOperands(op1);
    addOperands(op2);
  }

  static public String Trans(String opcode) {
    String ret = switch (opcode) {
      case "==" -> "eq";
      case "!=" -> "ne";
      case ">=" -> "sge";
      case "<=" -> "sle";
      case ">" -> "sgt";
      case "<" -> "slt";
      default -> null;
    };
    return ret;
  }

  public String getOpcode() {
    return switch (opcode) {
      case "slt" -> "blt";
      case "sle" -> "ble";
      case "sgt" -> "bgt";
      case "sge" -> "bge";
      case "eq" -> "beq";
      case "ne" -> "bne";
      default -> "error";
    };
  }

  @Override
  public String toString() {
    return getName() + " = icmp " + opcode + " " +
            getOperand(0).getTypeAndName() + ", " + getOperand(1).getName();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
