package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Store extends ASMBaseInst {
  // s{b|h|w|d} <rd>, <symbol>: store byte, half word, word or double word to global

  public Register reg, address;
  public Immediate offset;
  final int size;

  public Store(Register reg, Register address, Immediate offset, int size, ASMBlock parenBlock) {
    super(parenBlock);
    this.reg = reg;
    this.address = address;
    this.offset = offset;
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
    return translate(size) + " " + reg.toString() + ", "
            + offset.toString() + "(" + address.toString() + ")";
  }
}
