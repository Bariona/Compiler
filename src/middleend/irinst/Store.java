package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.PtrType;
import middleend.irtype.VoidType;
import utility.Debugger;

public class Store extends IRBaseInst {

  public Store(Value value, Value ptr, IRBasicBlock parenBlock) {
    super("store", new VoidType(), parenBlock);

    if (ptr.getType() instanceof PtrType ptrType) {
      assert value.getType().match(ptrType.target);
    } else {
      Debugger.error(ptr.getTypeAndName());
    }

    addOperands(value);
    addOperands(ptr);
  }

  @Override
  public String toString() {
    return "store " + getOperand(0).getTypeAndName() + ", " + getOperand(1).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
