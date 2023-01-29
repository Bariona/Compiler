package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

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
  public HashSet<Register> getDefs() {
    return new HashSet<>(Collections.singletonList(rd));
  }

  @Override
  public void replaceDefs(Register u, Register v) {
    if (rd == u) rd = v;
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }
}
