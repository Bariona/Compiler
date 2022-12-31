package frontend.ir.instruction;

import frontend.ir.User;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;

abstract public class IRBaseInst extends User {
  public IRBlock parenBlock;

  public IRBaseInst(String name, IRBaseType type, IRBlock parenBlock) {
    super(name, type);
    this.parenBlock = parenBlock;

    if (parenBlock != null)
      parenBlock.addInst(this);
  }
}
