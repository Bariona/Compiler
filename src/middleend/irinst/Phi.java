package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.IRBaseType;
import utility.Debugger;

public class Phi extends IRBaseInst {
  public Phi(IRBaseType type, IRBasicBlock parenBlock) {
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
      // if (getOperand(i) instanceof IRBaseInst instr) {
      ret.append("[ ").append(getOperand(i).getName()).append(", ").append(getOperand(i + 1).getName()).append(" ]");
//      } else {
//        Debugger.error(getOperand(i).toString());
//        assert false;
//      }

      if (i + 2 < operands.size()) {
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
