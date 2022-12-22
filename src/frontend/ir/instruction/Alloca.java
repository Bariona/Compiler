package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;
import frontend.ir.irtype.PtrType;

public class Alloca extends IRBaseInst {
  IRBaseType allocaType;

  public Alloca(String name, IRBaseType allocaType, IRBlock parenBlock) {
    super(name + ".addr", new PtrType(allocaType), parenBlock);
    this.allocaType = allocaType;
  }

  @Override
  public String toString() {
    return getName() + " = alloca " + allocaType.toString();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
