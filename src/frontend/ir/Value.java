package frontend.ir;

import backend.asm.operand.BaseOperand;
import backend.asm.operand.Register;
import frontend.ir.irtype.IRBaseType;

import java.util.ArrayList;
import java.util.HashMap;

public class Value {
  public String name;
  public Value recordPtr;
  public final IRBaseType type;
  private boolean isGlobal = false;
  public BaseOperand asmOperand = null;
  public final ArrayList<User> userList;

  public static HashMap<String, Integer> renameTable = new HashMap<>();

  public Value(String name, IRBaseType type) {
    this.name = rename(name);
    this.type = type;
    this.userList = new ArrayList<>();
  }

  public static String rename(String s) {
    Integer cnt = renameTable.get(s);
    if (cnt == null) {
      renameTable.put(s, 1);
      return s;
    } else renameTable.put(s, cnt + 1);

    return s + cnt;
  }


  public void setGlobal() {
    isGlobal = true;
  }

  public boolean isGlobal() {
    return isGlobal;
  }

  public void addUser(User user) {
    userList.add(user);
  }

  public void replaceUserValue(Value value) {
    assert this != value;
    for (var user : userList) {
      for (int i = 0; i < user.operands.size(); ++i) {
        var op = user.operands.get(i);
        if (op == this) user.operands.set(i, value);
      }
      value.userList.add(user);
    }
  }

  public IRBaseType getType() {
    return type;
  }

  public String getName() {
    return "%" + this.name;
  }

  public String getTypeAndName() {
    return type.toString() + " " + this.getName();
  }
}
