package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;

public abstract class ASMBaseInst {
  public ASMBlock parenBlock;

  public ASMBaseInst(ASMBlock parenBlock) {
    this.parenBlock = parenBlock;
    if (parenBlock != null)
      parenBlock.addInst(this);
  }

  abstract public String toString();

  abstract public void accept(InstVisitor visitor);
}
