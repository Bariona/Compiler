package middleend.operands;

import middleend.irtype.IRBaseType;

public class Register extends IRBaseConst {

  public Register(String name, IRBaseType type) {
    super(name, type);
  }
}
