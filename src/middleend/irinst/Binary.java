package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;

public class Binary extends IRBaseInst {
  String opcode;

  // <result> = opcode <ty> <op1>, <op2>          ; yields ty:result
  public Binary(String opcode, Value op1, Value op2, IRBasicBlock parenBlock) {
    super(opcode, op1.getType(), parenBlock);
    this.opcode = opcode;

    assert op1.getType().match(op2.getType());
    addOperands(op1);
    addOperands(op2);
  }

  static public String Trans(String opcode) {
    String ret = switch (opcode) {
      case "+" -> "add";
      case "-" -> "sub";
      case "*" -> "mul";
      case "/" -> "sdiv";
      case "%" -> "srem";
      case "^" -> "xor";
      case "|" -> "or";
      case "&" -> "and";
      case "<<" -> "shl";
      case ">>" -> "ashr";
      default -> null;
    };
    return ret;
  }

  @Override
  public String toString() {
    return getName() + " = " + opcode + " " +
            getOperand(0).getTypeAndName() + ", " + getOperand(1).getName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
