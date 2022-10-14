package utility.scope;

import utility.info.*;
import utility.Position;
import utility.error.NameError;

import java.util.HashMap;

public class ClassScope extends BaseScope {
  public HashMap<String, FuncInfo> funcInfoTable;

  public ClassScope() {
    super();
    funcInfoTable = new HashMap<>();
  }

  @Override
  public void addItem(BaseInfo info) {
    String name = info.name;
    if (info == null)
      throw new NameError("Ooooops, Forget add type!!", info.pos);

    if (info instanceof VarInfo) {
      if (varInfoTable.containsKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      varInfoTable.put(name, (VarInfo) info);
    } else if (info instanceof FuncInfo) {
      if (funcInfoTable.containsKey(name))
        throw new NameError("member \"" + name + "\" redefined", info.pos);
      funcInfoTable.put(name, (FuncInfo) info);
    } else {
      throw new NameError("not a member type!", info.pos);
    }
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
  public ClassInfo queryClassInfo(String name) {
    return null;
  }
}
