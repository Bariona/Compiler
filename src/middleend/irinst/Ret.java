package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.VoidType;

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
