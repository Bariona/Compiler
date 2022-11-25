package middleend.hierarchy;

import middleend.IRVisitor;
import middleend.User;
import middleend.Value;
import middleend.irtype.FuncType;

public class IRFunction extends User {
  public IRBasicBlock entryBlock;
  public FuncType funcType; // retType and paraType e.g. %struct = type {i32, i1}

  public IRFunction(String name, FuncType funcType) {
    super(name, funcType.retType);
    this.funcType = funcType;
  }

  public void addParameters(Value para) {
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
