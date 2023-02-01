package frontend.ir.operands;

import frontend.ir.irtype.IRBaseType;

public class IReg extends IRBaseConst {

  public IReg(String name, IRBaseType type) {
    super(name.substring(0, name.lastIndexOf(".")), type);
  }

  @Override
  public String getName() {
    return name;
  }
}
