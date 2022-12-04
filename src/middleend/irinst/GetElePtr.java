package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;
import middleend.irtype.PtrType;
import middleend.operands.IntConst;

public class GetElePtr extends IRBaseInst {

  public GetElePtr(String name, Value pointer, Value idx, IRBasicBlock parenBlock) {
    super(name, pointer.getType(), parenBlock);
    addOperands(pointer);
    addOperands(idx);
  }

  public GetElePtr(String name, IRBaseType targetEleType, Value structPtr, IRBasicBlock parenBlock, Value idx) {
    super(name, new PtrType(targetEleType), parenBlock);
    addOperands(structPtr);
    addOperands(new IntConst(0));
    addOperands(idx);
  }

  // e.g. %8 = getelementptr inbounds %class.A, %class.A* %1, i32 0, i32 3
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = getelementptr inbounds ");
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
