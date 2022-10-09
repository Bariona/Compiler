package utility;

import utility.error.SyntaxError;
import utility.type.BaseType;
import utility.type.VarType;

import java.util.HashMap;

public class Scope {
  private HashMap<String, BaseType> members;
  private Scope outLayer;

  public Scope(Scope outLayer) {
    this.outLayer = outLayer;
    members = new HashMap<>();
  }

  public void addItem(String name, BaseType type, Position pos) {
    if (type == null)
      throw new SyntaxError("F**K, Forget add type!!", pos);

    if (members.containsKey(name))
      throw new SyntaxError("member \"" + name + "\" redefined", pos);
    members.put(name, type);
  }

  public BaseType queryType(String name, Position pos) {
    if (!members.containsKey(name)) {
      if (outLayer != null) {
        return outLayer.queryType(name, pos);
      } else throw new SyntaxError("member \"" + name + "\" not defined", pos);
    }
    return members.get(name);
  }

  public boolean isContain(String name) {
    if (members.containsKey(name)) return true;
    if (outLayer == null) return false;
    return outLayer.isContain(name);
  }

}
