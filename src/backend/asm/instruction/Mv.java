package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

public class Mv extends ASMBaseInst {
  public Register rd, rs;

  // Copy register

  public Mv(Register rd, Register rs, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.rs = rs;
  }

  @Override
  public String toString() {
    return "mv " + rd.toString() + ", " + rs.toString();
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
