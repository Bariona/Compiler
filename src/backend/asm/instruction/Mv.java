package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

public class Mv extends ASMBaseInst {
  public Register rd, rs;

  // Copy register

  public Mv(Register rd, Register rs, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.rs = rs;
  }

  @Override
  public HashSet<Register> getUses() {
    return new HashSet<>(Collections.singletonList(rs));
  }

  @Override
  public void replaceUses(Register u, Register v) {
    if (rs == u) rs = v;
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
  public String toString() {
    return "mv " + rd.toString() + ", " + rs.toString();
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
