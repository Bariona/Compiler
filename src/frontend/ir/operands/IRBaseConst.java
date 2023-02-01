package frontend.ir.operands;

import frontend.ir.Value;
import frontend.ir.irtype.IRBaseType;

abstract public class IRBaseConst extends Value {

  public IRBaseConst(String name, IRBaseType type) {
    super(name, type);
  }

  @Override
  public String toString() {
    assert false;
    return null;
  }

}
