package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Load extends ASMBaseInst {
  // load an address from the GOT(Global Offset Table)
  public Register rd, rs, address = null;
  // public Immediate offset;
  final int size;

  public Load(Register rd, Register rs, Immediate offset, int size, ASMBlock parenBlock) {
    super(parenBlock);
    this.rd = rd;
    this.rs = rs;
    this.rs.setOffset(offset);
    // this.offset = offset;
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
  public String toString() {
//    String ret = translate(size) + " " + rd.toString() + ", "
//            + offset.toString() + "(" + rs.toString() + ")";
//    if (address != null) ret += address.toString();
//    if (true) return ret;
    return translate(size) + " " + rd.toString() + ", "
            + rs.offset.toString() + "(" + rs.toString() + ")";
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
