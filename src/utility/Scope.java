package utility;

import utility.error.SyntaxError;
import utility.type.BaseType;
import utility.type.FuncType;
import utility.type.VarType;

import java.util.HashMap;

public class Scope {
  private HashMap<String, VarType> members;
  private HashMap<String, FuncType> members;
  private Scope outLayer;

  public Scope(Scope outLayer) {
    this.outLayer = outLayer;
    members = new HashMap<>();
  }

  public void addItem(String name, VarType type, Position pos) {
    if (type == null)
      throw new SyntaxError("F**K, Forget add type!!", pos);

    if (members.containsKey(name))
      throw new SyntaxError("member \"" + name + "\" redefined", pos);
    members.put(name, type);
  }

  public VarType queryMemberType(String name, Position pos) {
    if (!members.containsKey(name)) {
      if (outLayer != null) {
        return outLayer.queryMemberType(name, pos);
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
