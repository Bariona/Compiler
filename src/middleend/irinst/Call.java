package middleend.irinst;

import middleend.IRVisitor;
import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;

public class Call extends IRBaseInst {
  public Call(IRFunction func, IRBasicBlock parenBlock) {
    super("call", func.getType(), parenBlock);
    addOperands(func);
  }

  @Override
  public String toString() {
    return getName() + " = call " + this.getType().toString() + " " + getOperand(0).toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
