package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

public class La extends ASMBaseInst {
  public String name;
  public Register rd;

  public La(Register rd, String name, ASMBlock parenBlock) {
    super(parenBlock);
    this.name = name;
    this.rd = rd;
  }

  @Override
  public String toString() {
    return "la " + rd.toString() + ", " + name;
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }
}
