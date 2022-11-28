package middleend;

import middleend.irtype.IRBaseType;

import java.util.ArrayList;
import java.util.HashMap;

public class Value {
  public String name;
  public Value recordPtr;
  private final IRBaseType type;
  private final ArrayList<User> useList;

  public static HashMap<String, Integer> renameTable = new HashMap<>();

  public Value(String name, IRBaseType type) {
    this.name = rename(name);
    this.type = type;
    this.useList = new ArrayList<>();
  }

  public static String rename(String s) {
    Integer cnt = renameTable.get(s);

    if (cnt == null) {
      renameTable.put(s, 1);
      return s;
    } else renameTable.put(s, cnt + 1);

    return s + "." + cnt;
  }

  public void addUser(User user) {
    useList.add(user);
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
