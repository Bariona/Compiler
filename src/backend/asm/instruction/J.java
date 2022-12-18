package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;

public class J extends ASMBaseInst {
  public ASMBlock dest;

  public J(ASMBlock dest, ASMBlock parenBlock) {
    super(parenBlock);
    this.dest = dest;
  }

  @Override
  public String toString() {
    return "j " + dest.toString();
  }
}
