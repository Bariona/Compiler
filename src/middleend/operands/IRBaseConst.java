package middleend.operands;

import middleend.Value;
import middleend.irtype.IRBaseType;

abstract public class IRBaseConst extends Value {

  public IRBaseConst(String name, IRBaseType type) {
    super(name, type);
  }

}
