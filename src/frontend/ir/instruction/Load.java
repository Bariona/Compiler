package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.PtrType;

public class Load extends IRBaseInst {

  // %val = load i32, i32* %ptr
  public Load(Value ptr, IRBlock parenBlock) {
    super(ptr.name + ".load", ((PtrType) ptr.getType()).target, parenBlock);
    addOperands(ptr);
  }

  public Value loadPtr() {
    return getOperand(0);
  }

  public void resetPtr(Value value) {
    resetOperands(0, value);
  }

  @Override
  public String toString() {
    return getName() + " = load " + this.getType().toString() + ", " + getOperand(0).getTypeAndName();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
