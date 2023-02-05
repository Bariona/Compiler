package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;

import java.util.ArrayList;
import java.util.Arrays;

public class Binary extends IRBaseInst {
  String opcode;

  // <result> = opcode <ty> <op1>, <op2>          ; yields ty:result
  public Binary(String opcode, Value op1, Value op2, IRBlock parenBlock) {
    super(opcode, op1.getType(), parenBlock);
    this.opcode = opcode;

    assert op1.getType().match(op2.getType());
    addOperands(op1);
    addOperands(op2);
  }

  static public String Trans(String opcode) {
    return switch (opcode) {
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
  }

  public String getOpcode() {
    return switch (opcode) {
      case "shl"-> "sll";
      case "ashr" -> "sra";
      case "srem" -> "rem"; // remainder
      case "sdiv" -> "div";
      default -> opcode;
    };
  }

  @Override
  public ArrayList<Value> getUses() {
    return new ArrayList<>(Arrays.asList(getOperand(0), getOperand(1)));
  }

  @Override
  public String toString() {
    return getName() + " = " + opcode + " " +
            getOperand(0).getTypeAndName() + ", " + getOperand(1).getName();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
