package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;

public class Alloca extends IRBaseInst {
  IRBaseType allocaType;

  public Alloca(String name, IRBaseType allocaType, IRBasicBlock parenBlock) {
    super(name, new PtrType(allocaType), parenBlock);
    this.allocaType = allocaType;
  }

  @Override
  public String toString() {
    return getName() + " = alloca " + allocaType.toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
