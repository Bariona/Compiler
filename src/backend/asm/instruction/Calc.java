package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.BaseOperand;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

public class Calc extends ASMBaseInst {
  public String op;
  public Register rd;
  public BaseOperand rs1, rs2;

  // binary & icmp
  public Calc(String op, Register rd, BaseOperand rs1, BaseOperand rs2, ASMBlock parenBlock) {
    super(parenBlock);
    this.op = op;
    this.rd = rd;
    this.rs1 = rs1;
    this.rs2 = rs2;
  }

  @Override
  public HashSet<Register> getUses() {
    HashSet<Register> ret = new HashSet<>();
    if (rs1 instanceof Register r1) ret.add(r1);
    if (rs2 instanceof Register r2) ret.add(r2);
    return ret;
  }

  @Override
  public void replaceUses(Register u, Register v) {
    if (rs1 == u) rs1 = v;
    if (rs2 == u) rs2 = v;
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
    return op + " " + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
