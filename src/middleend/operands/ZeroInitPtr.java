package middleend.operands;

import middleend.irtype.IRBaseType;

public class ZeroInitPtr extends IRBaseConst {

  public ZeroInitPtr(IRBaseType type) {
    super("zeroinitializer", type);
  }

  @Override
  public String getName() {
    return "zeroinitializer";
  }
}
