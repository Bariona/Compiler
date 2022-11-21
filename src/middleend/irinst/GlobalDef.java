package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;

public class GlobalDef extends IRBaseInst {
  // global variable

  public GlobalDef(String name, Value value, IRBasicBlock parenBlock) {
    super("glob_" + name, value.getType(), parenBlock);
    addOperands(value);
  }

  @Override
  public String getName() {
    return "@" + this.name;
  }

  @Override
  public String toString() {
    return getName() + " = global " + getOperand(0).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

}
