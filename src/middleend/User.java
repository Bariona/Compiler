package middleend;

import middleend.irtype.IRBaseType;

import java.util.ArrayList;

public abstract class User extends Value {
  public ArrayList<Value> operands;

  public User(String name, IRBaseType type) {
    super(name, type);
    this.operands = new ArrayList<>();
  }

  public Value getOperand(int i) {
    return operands.get(i);
  }

  public void addOperands(Value val) {
    operands.add(val);
    val.addUser(this);
  }

  public void setOperands(int i, Value val) {
    operands.set(i, val);
  }

  protected abstract void accept(IRVisitor visitor);
}
