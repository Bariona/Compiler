package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.operand.Immediate;
import backend.asm.operand.Register;

public class Load extends ASMBaseInst {
  // load an address from the GOT(Global Offset Table)
  public Register reg, address;
  public Immediate offset;
  final int size;

  public Load(Register reg, Register address, Immediate offset, int size, ASMBlock parenBlock) {
    super(parenBlock);
    this.reg = reg;
    this.address = address;
    this.offset = offset;
    this.size = size;
    if (parenBlock != null)
      parenBlock.addInst(this);
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
    return translate(size) + " " + reg.toString() + ", "
            + offset.toString() + "(" + address.toString() + ")";
  }
}
