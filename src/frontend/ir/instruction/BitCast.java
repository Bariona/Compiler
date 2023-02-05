package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;

import java.util.ArrayList;
import java.util.Collections;

public class BitCast extends IRBaseInst {

  public BitCast(Value curValue, IRBaseType targetType, IRBlock parenBlock) {
    super("bitcast", targetType, parenBlock);
    addOperands(curValue);
  }

  @Override
  public ArrayList<Value> getUses() {
    return new ArrayList<>(Collections.singletonList(getOperand(0)));
  }

  @Override
  public String toString() {
    return getName() + " = bitcast " + getOperand(0).getTypeAndName() +
            " to " + getType().toString();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
