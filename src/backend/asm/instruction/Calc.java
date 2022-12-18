package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.BaseOperand;
import backend.asm.operand.Register;

public class Calc extends ASMBaseInst {
  String op;
  Register rd;
  BaseOperand rs1, rs2;

  // binary & icmp
  public Calc(String op, Register rd, BaseOperand rs1, BaseOperand rs2, ASMBlock parenBlock) {
    super(parenBlock);
    this.op = op;
    this.rd = rd;
    this.rs1 = rs1;
    this.rs2 = rs2;
  }

  @Override
  public String toString() {
    return op + " " + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
  }
}
