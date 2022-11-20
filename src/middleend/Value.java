package middleend;

import middleend.irtype.IRBaseType;

import java.util.ArrayList;
import java.util.HashMap;

public class Value {
  String name;
  IRBaseType type;
  ArrayList<User> useList;

  public static HashMap<String, Integer> renameTable = new HashMap<>();

  public Value(String name, IRBaseType type) {
    this.name = name;
    this.type = type;
    this.useList = new ArrayList<>();
  }

  public static String rename(String s) {
    int cnt = renameTable.get(s);
    renameTable.put(s, cnt + 1);
    if (cnt == 0) return s;
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

}
