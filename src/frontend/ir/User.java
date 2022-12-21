package frontend.ir;

import frontend.ir.irtype.IRBaseType;

import java.util.ArrayList;

public abstract class User extends Value {
  public ArrayList<Value> operands;

  public User(String name, IRBaseType type) {
    super(name, type);
    this.operands = new ArrayList<>();
  }

  public Value getOperand(int i) {
    return (i >= operands.size()) ? null : operands.get(i);
  }

  public void addOperands(Value val) {
    operands.add(val);
    val.addUser(this);
  }

  public void resetOperands(int i, Value val) {
    operands.get(i).userList.remove(this);
    val.addUser(this);
    operands.set(i, val);
  }

  protected abstract void accept(IRVisitor visitor);
}
