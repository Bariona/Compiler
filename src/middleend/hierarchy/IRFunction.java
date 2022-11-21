package middleend.hierarchy;

import middleend.IRVisitor;
import middleend.User;
import middleend.Value;
import middleend.irtype.IRBaseType;

public class IRFunction extends User {
  public IRFunction(String name, IRBaseType retType) {
    super(name, retType);
  }

  void addParameters(Value para) {
    addOperands(para);
  }

  public String toStr() { // without "define"
    StringBuilder ret = new StringBuilder(this.getType().toString() + " " + this.getName() + "(");
    for (int i = 0; i < operands.size(); ++i) {
      ret.append(getOperand(i).getTypeAndName());
      if (i < operands.size() - 1)
        ret.append(", ");
    }
    ret.append(")");
    return ret.toString();
  }

  @Override
  public String getName() {
    return "@" + this.name;
  }

  @Override
  public String toString() {
    return "define " + this.toStr();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

}
