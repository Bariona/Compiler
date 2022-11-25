package middleend.operands;

import middleend.irtype.PtrType;

public class NullConst extends IRBaseConst {

  public NullConst() {
    super("Null", new PtrType());
  }

}
