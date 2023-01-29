package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMModule;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

public class Ret extends ASMBaseInst {
  ASMModule asm;

  public Ret(ASMModule asm, ASMBlock parenBlock) {
    super(parenBlock);
    this.asm = asm;
  }

  @Override
  public HashSet<Register> getUses() {
    return new HashSet<>(Collections.singletonList(asm.getReg("ra")));
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
