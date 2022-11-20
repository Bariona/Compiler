package middleend.operands;

import middleend.irtype.BoolType;
import middleend.irtype.IRBaseType;

public class BoolConst extends IRBaseConst {
  boolean isTrue;

  public BoolConst(String value) throws Exception {
    super("ConstBool", new BoolType());
    if (value.equals("true")) {
      this.isTrue = true;
    } else if (value.equals("false")) {
      this.isTrue = false;
    } else throw new Exception("value out of range!");
  }

}
