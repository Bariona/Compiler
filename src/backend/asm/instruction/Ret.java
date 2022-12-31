package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;

public class Ret extends ASMBaseInst {

  public Ret(ASMBlock parenBlock) {
    super(parenBlock);
  }

  @Override
  public String toString() {
    return "ret";
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
