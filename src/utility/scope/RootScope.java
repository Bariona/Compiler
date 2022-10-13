package utility.scope;

import ast.info.*;
import utility.Position;
import utility.error.NameError;

import java.util.HashMap;

public class RootScope extends Scope {
  public HashMap<String, ClassInfo> classInfoTable;
  public HashMap<String, FuncInfo> funcInfoTable;

  @Override
  public void addItem(BaseInfo info) {
    String name = info.name;
    if (info == null)
      throw new NameError("F**K, Forget add type!!", info.pos);

    if (info instanceof VarInfo) {
      if (varInfoTable.containsKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      varInfoTable.put(name, (VarInfo) info);
    } else if (info instanceof ClassInfo) {
      if (classInfoTable.containsKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      classInfoTable.put(name, (ClassInfo) info);
    } else if (info instanceof FuncInfo) {
      if (funcInfoTable.containsKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      funcInfoTable.put(name, (FuncInfo) info);
    }
  }

  @Override
  public VarInfo queryVarInfo(String name, Position pos) {
    if (varInfoTable.containsKey(name))
      return varInfoTable.get(name);
    throw new NameError("member \"" + name + "\" does not exist", pos);
  }

  @Override
  public ClassInfo queryClassInfo(String name, Position pos) {
    if (classInfoTable.containsKey(name))
      return classInfoTable.get(name);
    throw new NameError("member \"" + name + "\" does not exist", pos);
  }

  @Override
  public FuncInfo queryFuncInfo(String name, Position pos) {
    if (funcInfoTable.containsKey(name))
      return funcInfoTable.get(name);
    throw new NameError("member \"" + name + "\" does not exist", pos);
  }
}
