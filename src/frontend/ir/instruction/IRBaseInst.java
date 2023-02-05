package frontend.ir.instruction;

import frontend.ir.User;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;

import java.util.ArrayList;

abstract public class IRBaseInst extends User {
  public IRBlock parenBlock;

  public IRBaseInst(String name, IRBaseType type, IRBlock parenBlock) {
    super(name, type);
    this.parenBlock = parenBlock;

    if (parenBlock != null)
      parenBlock.addInst(this);
  }

  public ArrayList<Value> getUses() {
    return new ArrayList<>();
  }

}
