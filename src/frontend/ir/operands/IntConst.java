package frontend.ir.operands;

import frontend.ir.irtype.IntType;

public class IntConst extends IRBaseConst {
  public int value;

  public IntConst(int value) {
    super("ConstInt", new IntType());
    this.value = value;
  }

  @Override
  public String getName() {
    return String.valueOf(value);
  }
}
