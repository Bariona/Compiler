package frontend.ir.operands;

import frontend.ir.irtype.BoolType;

public class BoolConst extends IRBaseConst {
  public boolean isTrue;

  public BoolConst(String value) {
    super("ConstBool", new BoolType());
    if (value.equals("true")) {
      this.isTrue = true;
    } else if (value.equals("false")) {
      this.isTrue = false;
    } else assert false;
  }

  @Override
  public String getName() {
    return String.valueOf(isTrue ? 1 : 0);
  }
}
