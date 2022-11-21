package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;

public class GetElePtr extends IRBaseInst {

  public GetElePtr(IRBaseType targetEleType, Value ptr, IRBasicBlock parenBlock) {
    super("getelementptr", targetEleType, parenBlock);
    assert ptr.getType() instanceof PtrType;
    addOperands(ptr);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = getelementptr ");
    ret.append(((PtrType) getOperand(0).getType()).target.toString());
    for (var operand : operands)
      ret.append(", ").append(operand.getTypeAndName());
    return ret.toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
