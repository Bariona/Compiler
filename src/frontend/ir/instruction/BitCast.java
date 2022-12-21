package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;

public class BitCast extends IRBaseInst {

  public BitCast(Value curValue, IRBaseType targetType, IRBlock parenBlock) {
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
