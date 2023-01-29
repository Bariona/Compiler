package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

import java.util.HashSet;

public abstract class ASMBaseInst {
  public ASMBlock parenBlock;

  public ASMBaseInst(ASMBlock parenBlock) {
    this.parenBlock = parenBlock;
    if (parenBlock != null)
      parenBlock.addInst(this);
  }

  public HashSet<Register> getUses() {
    return new HashSet<>();
  }

  public HashSet<Register> getDefs() {
    return new HashSet<>();
  }

  public void replaceUses(Register u, Register v) {}

  public void replaceDefs(Register u, Register v) {}

  abstract public String toString();

  abstract public void accept(InstVisitor visitor);
}
