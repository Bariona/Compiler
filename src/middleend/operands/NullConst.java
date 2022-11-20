package middleend.operands;

import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;

public class NullConst extends IRBaseConst {
  public NullConst(String name, IRBaseType type) {
    super("Null", new PtrType());
  }

}
