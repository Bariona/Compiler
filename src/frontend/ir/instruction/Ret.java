package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBasicBlock;
import frontend.ir.irtype.VoidType;

public class Ret extends IRBaseInst {

  public Ret(IRBasicBlock parenBlock) { // ret void
    super("ret", new VoidType(), parenBlock);
  }

  public Ret(Value value, IRBasicBlock parenBlock) { // ret <type> <value>
    super("ret", value.getType(), parenBlock);
    addOperands(value);
  }

  @Override
  public String toString() {
    if (this.operands.size() > 0)
      return "ret " + getOperand(0).getTypeAndName();
    return "ret void";
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
