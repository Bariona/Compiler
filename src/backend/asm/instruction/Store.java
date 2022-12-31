package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Store extends ASMBaseInst {
  // s{b|h|w|d} <rd>, <symbol>: store byte, half word, word or double word to global

  public Register rs, rd, address = null;
  // public Immediate offset;
  final int size;

  public Store(Register rs, Register rd, Immediate offset, int size, ASMBlock parenBlock) {
    super(parenBlock);
    this.rs = rs;
    this.rd = rd;
    this.rd.setOffset(offset);
    // this.offset = offset;
    this.size = size;
  }

  static public String translate(int size) {
    return switch (size) {
      case 1 -> "sb";
      case 2 -> "sh";
      case 4 -> "sw";
      default -> throw new IllegalStateException("Unexpected value: " + size);
    };
  }

  @Override
  public String toString() {
//    String ret = translate(size) + " " + rs.toString() + ", "
//            + offset.toString() + "(" + rd.toString() + ")";
//    if (address != null) ret += address.toString();
//    if (true) return ret;
    return translate(size) + " " + rs.toString() + ", "
            + rd.offset.toString() + "(" + rd.toString() + ")";
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
