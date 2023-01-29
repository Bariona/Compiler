package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Register;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class Branch extends ASMBaseInst {
  public String op;
  public Register rs1, rs2;
  public ASMBlock dest;

  public Branch(String op, Register rs1, Register rs2, ASMBlock dest, ASMBlock parenBlock) {
    super(parenBlock);
    this.op = op;
    this.rs1 = rs1;
    this.rs2 = rs2;
    this.dest = dest;
  }


  @Override
  public HashSet<Register> getUses() {
    return new HashSet<>(Arrays.asList(rs1, rs2));
  }

  @Override
  public String toString() {
    return op + " " + rs1.toString() + ", " + rs2.toString() + ", " + dest.toString();
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
