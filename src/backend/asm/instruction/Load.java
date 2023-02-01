package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

import java.util.Collections;
import java.util.HashSet;

public class Load extends ASMBaseInst {
  // load an address from the GOT(Global Offset Table)
  public Register rd, rs;
  public Immediate offset;
  final int size;

  public Load(Register rd, Register rs, Immediate offset, int size, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.rs = rs;
    this.offset = offset;
    this.size = size;
  }

  static public String translate(int size) {
    return switch (size) {
      case 1 -> "lb";
      case 2 -> "lh";
      case 4 -> "lw";
      default -> throw new IllegalStateException("Unexpected value: " + size);
    };
  }

  @Override
  public HashSet<Register> getUses() {
    return new HashSet<>(Collections.singletonList(rs));
  }

  @Override
  public void replaceUses(Register u, Register v) {
    if (u == rs) rs = v;
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
//    String ret = translate(size) + " " + rd.toString() + ", "
//            + offset.toString() + "(" + rs.toString() + ")";
//    if (address != null) ret += address.toString();
//    if (true) return ret;
    return translate(size) + " " + rd.toString() + ", "
            + offset.toString() + "(" + rs.toString() + ")";
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
