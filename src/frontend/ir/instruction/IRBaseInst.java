package frontend.ir.instruction;

import frontend.ir.User;
import frontend.ir.hierarchy.IRBasicBlock;
import frontend.ir.irtype.IRBaseType;

abstract public class IRBaseInst extends User {
  public IRBasicBlock parenBlock;

  public IRBaseInst(String name, IRBaseType type, IRBasicBlock parenBlock) {
    super(name, type);
    this.parenBlock = parenBlock;

    assert parenBlock != null;
    parenBlock.addInst(this);
  }
}
