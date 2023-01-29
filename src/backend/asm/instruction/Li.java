package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

public class Li extends ASMBaseInst {
  public Register rd;
  public Immediate imm;

  // Load immediate
  public Li(Register rd, Immediate imm, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.imm = imm;
  }

  @Override
  public HashSet<Register> getDefs() {
    return new HashSet<>(Collections.singletonList(rd));
  }

  @Override
  public void replaceDefs(Register u, Register v) {
    if (u == rd) rd = v;
  }

  @Override
  public String toString() {
    return "li " + rd.toString() + ", " + imm.toString();
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
