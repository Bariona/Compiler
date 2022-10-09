package utility;

import utility.error.SyntaxError;

import java.util.HashSet;

public class Scope {
  private HashSet<String> members;
  private Scope LastScope;

  public Scope(Scope LastScope) {
    members = new HashSet<>();
    this.LastScope = LastScope;
  }

  public void DefineVariable(String name, Position pos) {
    if(members.contains(name))
      throw new SyntaxError("member \"" + name + "\" redefined", pos);
    members.add(name);
  }

  public boolean ContainVariable(String name) {
    if(members.contains(name))
      return true;
    if(LastScope == null)
      return false;
    return LastScope.ContainVariable(name);
  }

}
