package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.PtrType;
import middleend.irtype.VoidType;
import middleend.operands.NullConst;
import utility.Debugger;

public class Store extends IRBaseInst {

  public Store(Value value, Value ptr, IRBasicBlock parenBlock) {
    super("store", new VoidType(), parenBlock);

    if (ptr.getType() instanceof PtrType ptrType) {
      if (value instanceof NullConst) {
        value = new NullConst(ptrType.target);
      } else if (!value.getType().match(ptrType.target)) {
        Debugger.error("target type: " + ptrType.target.toString() + " ← store value type: " + value.getType().toString());
      }
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
