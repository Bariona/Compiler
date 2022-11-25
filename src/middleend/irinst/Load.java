package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.PtrType;

public class Load extends IRBaseInst {

  // %val = load i32, i32* %ptr
  public Load(Value ptr, IRBasicBlock parenBlock) {
    super("load", ((PtrType) ptr.getType()).target, parenBlock);
    addOperands(ptr);
  }

  @Override
  public String toString() {
    return getName() + " = load " + this.getType().toString() + ", " + getOperand(0).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
