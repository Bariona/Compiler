package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;

public class NOP extends ASMBaseInst {
  public NOP() {
    super(null);
  }

  public String toString() {
    return "";
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }
}
