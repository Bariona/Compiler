package middleend.irinst;

import middleend.IRVisitor;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;

public class Phi extends IRBaseInst {
  public Phi(IRBaseType type, IRBasicBlock parenBlock) {
    super("phi", type, parenBlock);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(getName() + " = phi ");
    for (int i = 0; i < operands.size(); ++i) {
      if (getOperand(i) instanceof IRBaseInst instr) {
        ret.append("[ ").append(instr.getName()).append(", ").append(instr.parenBlock.getName()).append(" ]");
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
