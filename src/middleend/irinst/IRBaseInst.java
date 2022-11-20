package middleend.irinst;

import middleend.User;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;

abstract public class IRBaseInst extends User {
  public IRBasicBlock parenBlock;

  public IRBaseInst(String name, IRBaseType type, IRBasicBlock parenBlock) {
    super(name, type);
    this.parenBlock = parenBlock;

    assert parenBlock != null;
    parenBlock.addInst(this);
  }
}
