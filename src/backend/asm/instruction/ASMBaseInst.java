package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;

public abstract class ASMBaseInst {
  public ASMBlock parenBlock;

  public ASMBaseInst(ASMBlock parenBlock) {
    this.parenBlock = parenBlock;
  }

  abstract public String toString();
}
