package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;
import frontend.ir.irtype.PtrType;
import frontend.ir.operands.IntConst;

public class GetElePtr extends IRBaseInst {


  // e.g. %glob_arr.load.elm = getelementptr inbounds i32, i32* %glob_arr.load, i32 4
  // a[4]
  public GetElePtr(String name, Value pointer, Value idx, IRBlock parenBlock) {
    super(name, pointer.getType(), parenBlock);
    addOperands(pointer);
    addOperands(idx);
  }

  // e.g. %8 = getelementptr inbounds %class.A, %class.A* %1, i32 0, i32 3
  public GetElePtr(String name, IRBaseType targetEleType, Value structPtr, IRBlock parenBlock, Value idx) {
    super(name, new PtrType(targetEleType), parenBlock);
    addOperands(structPtr);
    addOperands(new IntConst(0));
    addOperands(idx);
  }

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
