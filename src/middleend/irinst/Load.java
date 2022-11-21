package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;

public class Load extends IRBaseInst {

  public Load(IRBaseType loadType, Value ptr, IRBasicBlock parenBlock) {
    super("load", loadType, parenBlock);
    if (ptr.getType() instanceof PtrType ptrType) {
      assert loadType.match(ptrType.target);
    } else assert false;
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
