package middleend.operands;

import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;
import middleend.irtype.VoidType;

public class NullConst extends IRBaseConst {

  public NullConst() {
    super("Null", new PtrType(new VoidType()));
  }

  public NullConst(IRBaseType type) {
    super("Null", type);
  }

  @Override
  public String getName() {
    return "null";
  }
}
