package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;

public class Phi extends IRBaseInst {
  public Phi(IRBaseType type, IRBasicBlock parenBlock) {
    super("phi", type, parenBlock);
  }

  public void addOperands(Value... valList) {
    for (Value val : valList) super.addOperands(val);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = phi ");
    ret.append(getType().toString() + " ");
    for (int i = 0; i < operands.size(); i += 2) {
      if (getOperand(i) instanceof IRBaseInst instr) {
        ret.append("[ ").append(instr.getName()).append(", ").append(getOperand(i + 1).getName()).append(" ]");
      } else assert false;

      if (i < operands.size() - 1) {
        ret.append(", ");
      }
    }
    return ret.toString();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
