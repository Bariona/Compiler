package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.IRBaseType;

public class Phi extends IRBaseInst {
  public Phi(IRBaseType type, IRBlock parenBlock) {
    super("phi", type, parenBlock);
  }

  public void addOperands(Value... valList) {
    // 1st: value 2nd: belonging block
    for (Value val : valList) super.addOperands(val);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = phi ");
    ret.append(getType().toString() + " ");
    for (int i = 0; i < operands.size(); i += 2) {
      ret.append("[ ").append(getOperand(i).getName()).append(", ")
              .append(getOperand(i + 1).getName()).append(" ]");
      if (i + 2 < operands.size()) {
        ret.append(", ");
      }
    }
    return ret.toString();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
