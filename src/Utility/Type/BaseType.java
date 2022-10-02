package Utility.Type;

import java.util.HashMap;

public class BaseType {
  public enum BultinType {
    VOID, BOOL, INT, STRING, CLASS, NULL, NEW
  }

  public BultinType type;
  public HashMap<String, BultinType> members = null;

  BaseType(BultinType type) {
    this.type = type;
  }

  public BaseType(String typename) {
    System.out.println(typename);
    System.out.println(typename.getClass().toString());
    this.type = BultinType.valueOf(typename);
  }
}
