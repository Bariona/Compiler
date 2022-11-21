package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;

public class Binary extends IRBaseInst {
  String opcode;

  // <result> = opcode <ty> <op1>, <op2>          ; yields ty:result
  public Binary(String opcode, Value op1, Value op2, IRBasicBlock parenBlock) {
    super(opcode, op1.getType(), parenBlock);

    assert op1.getType().match(op2.getType());
    addOperands(op1);
    addOperands(op2);
  }

  @Override
  public String toString() {
    return opcode + " " + getOperand(0).getTypeAndName() + " " + getOperand(1).getName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
