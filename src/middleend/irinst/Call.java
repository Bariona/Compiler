package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;

public class Call extends IRBaseInst {
  public Call(IRFunction func, IRBasicBlock parenBlock, Value... Args) {
    super("call_" + func.name, func.getType(), parenBlock);
    addOperands(func);
    for (Value arg : Args) addOperands(arg);
  }

  // e.g. call void @_Z1f1Ai(%class.A* noundef %4, i32 noundef 1)  | void f(x, 1) x is a class
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = call "
            + this.getType().toString() + " " + getOperand(0).getName());
    ret.append("(");
    for (int i = 1; i < operands.size(); ++i) {
      if (i > 1) ret.append(", ");
      ret.append(getOperand(i).getTypeAndName());
    }
    ret.append(")");
    return ret.toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
