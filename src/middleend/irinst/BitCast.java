package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;

public class BitCast extends IRBaseInst {

  public BitCast(Value curValue, IRBaseType targetType, IRBasicBlock parenBlock) {
    super("bitcast", targetType, parenBlock);
    addOperands(curValue);
  }

  @Override
  public String toString() {
    return getName() + " = bitcast " + getOperand(0).getTypeAndName() +
            " to " + getType().toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
