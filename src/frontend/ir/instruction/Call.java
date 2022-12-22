package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.irtype.VoidType;

public class Call extends IRBaseInst {
  public Call(IRFunction func, IRBlock parenBlock, Value... Args) {
    super(func.name + ".call", func.getType(), parenBlock);
    addOperands(func);
    for (Value arg : Args) addOperands(arg);
  }

  // e.g. call void @_Z1f1Ai(%class.A* noundef %4, i32 noundef 1)  | void f(x, 1) x is a class
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    if (!(getType() instanceof VoidType))
      ret.append(getName() + " = ");
    ret.append("call " + this.getType().toString() + " " + getOperand(0).getName());
    ret.append("(");
    for (int i = 1; i < operands.size(); ++i) {
      if (i > 1) ret.append(", ");
      ret.append(getOperand(i).getTypeAndName());
    }
    ret.append(")");
    return ret.toString();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
