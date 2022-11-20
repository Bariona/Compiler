package utility.scope;

import utility.error.SemanticError;
import utility.info.*;
import utility.error.NameError;

import java.util.HashMap;

public class ClassScope extends BaseScope {
  public String name;
  public HashMap<String, FuncInfo> funcInfoTable;

  public ClassScope(String name) {
    super();
    this.name = name;
    funcInfoTable = new HashMap<>();
  }

  @Override
  public void addItem(BaseInfo info) {
    String name = info.name;
    if (containKey(name))
      throw new NameError("member \"" + name + "\" redefined", info.pos);
    if (info instanceof VarInfo vinfo) {
      varInfoTable.put(name, vinfo);
      if (info.name.equals(this.name))
        throw new SemanticError("variable duplicate with class name " + this.name, vinfo.pos);
    } else if (info instanceof FuncInfo finfo) {
      funcInfoTable.put(name, finfo);
      if (finfo.name.equals(this.name) && !finfo.isConstructor)
        throw new SemanticError("function duplicate with class name " + this.name, finfo.pos);
      if (!finfo.name.equals(this.name) && finfo.isConstructor)
        throw new SemanticError("function constructor should have the same class name " + this.name, finfo.pos);
    } else throw new NameError("not a member type!", info.pos);
  }

  @Override
  boolean containKey(String name) {
    return funcInfoTable.containsKey(name) || varInfoTable.containsKey(name);
  }

  @Override
  public VarInfo queryVarInfo(String name) {
    if (varInfoTable.containsKey(name))
      return varInfoTable.get(name);
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
    System.out.println("==== Class Scope ====");
    System.out.print("  Variable Table: ");
    varInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
    System.out.print("  Function Table: ");
    funcInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
  }

  @Override
  public ClassInfo queryClassInfo(String name) {
    return null;
  }
}
