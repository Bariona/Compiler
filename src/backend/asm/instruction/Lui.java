package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Lui extends ASMBaseInst {
  private Register rd;
  private Immediate imm;

  // x[rd] = sext(immediate[31:12] << 12)

  public Lui(Register rd, Immediate imm, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.imm = imm;
  }

  @Override
  public String toString() {
    return "lui " + rd.toString() + ", " + imm.toString();
  }
}
