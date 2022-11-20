package utility.scope;

import utility.info.BaseInfo;
import utility.info.ClassInfo;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.error.NameError;

import java.util.HashMap;

public class RootScope extends BaseScope {
  public HashMap<String, ClassInfo> classInfoTable;
  public HashMap<String, FuncInfo> funcInfoTable;

  public RootScope() {
    super();
    classInfoTable = new HashMap<>();
    funcInfoTable = new HashMap<>();
  }

  @Override
  public void addItem(BaseInfo info) {
    String name = info.name;
    if (containKey(name))
      throw new NameError("member \"" + name + "\" redefined", info.pos);
    if (info instanceof VarInfo) {
      varInfoTable.put(name, (VarInfo) info);
    } else if (info instanceof ClassInfo) {
      classInfoTable.put(name, (ClassInfo) info);
    } else if (info instanceof FuncInfo) {
      funcInfoTable.put(name, (FuncInfo) info);
    } else throw new NameError("not a member type!", info.pos);
  }

  @Override
  boolean containKey(String name) {
    return varInfoTable.containsKey(name) || classInfoTable.containsKey(name) || funcInfoTable.containsKey(name);
  }

  @Override
  public VarInfo queryVarInfo(String name) {
    if (varInfoTable.containsKey(name))
      return varInfoTable.get(name);
    return null;
  }

  @Override
  public ClassInfo queryClassInfo(String name) {
    if (classInfoTable.containsKey(name))
      return classInfoTable.get(name);
    return null;
  }

  @Override
  public FuncInfo queryFuncInfo(String name) {
    if (funcInfoTable.containsKey(name))
      return funcInfoTable.get(name);
    return null;
  }

  @Override
  public void print() {
    System.out.println("==== ROOT Scope ====");
    System.out.print("  Variable Table: ");
    varInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
    System.out.print("  Function Table: ");
    funcInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
    System.out.print("  Class Table: ");
    classInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
  }
}
