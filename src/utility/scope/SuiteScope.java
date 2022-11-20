package utility.scope;

import utility.info.*;
import utility.error.NameError;

public class SuiteScope extends BaseScope {

  @Override
  public void addItem(BaseInfo info) {
    String name = info.name;

    if (info instanceof VarInfo) {
      if (containKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      varInfoTable.put(name, (VarInfo) info);
    } else {
      throw new NameError("not a member type!", info.pos);
    }
  }

  @Override
  boolean containKey(String name) {
    return varInfoTable.containsKey(name);
  }

  @Override
  public VarInfo queryVarInfo(String name) {
    if (varInfoTable.containsKey(name))
      return varInfoTable.get(name);
    return null;
  }

  @Override
  public FuncInfo queryFuncInfo(String name) {
    return null;
  }

  @Override
  public ClassInfo queryClassInfo(String name) {
    return null;
  }

  @Override
  public void print() {
    System.out.println("==== Suite Scope ====");
    System.out.print("  Variable Table: ");
    varInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
  }
}
