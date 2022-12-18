package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Li extends ASMBaseInst {
  public Register reg;
  public Immediate imm;

  // Load immediate
  public Li(Register reg, Immediate imm, ASMBlock parenBlock) {
    super(parenBlock);
    this.reg = reg;
    this.imm = imm;
    if (parenBlock != null)
      parenBlock.addInst(this);
  }

  @Override
  public String toString() {
    return "li " + reg.toString() + ", " + imm.toString();
  }
}
