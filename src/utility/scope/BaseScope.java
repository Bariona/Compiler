package utility.scope;

import utility.info.*;

import java.util.HashMap;

abstract public class BaseScope {
  public HashMap<String, VarInfo> varInfoTable;

  public BaseScope() {
    varInfoTable = new HashMap<>();
  }

  abstract public void addItem(BaseInfo info);

  abstract boolean containKey(String name);

  abstract public VarInfo queryVarInfo(String name);

  abstract public FuncInfo queryFuncInfo(String name);

  abstract public ClassInfo queryClassInfo(String name);

  abstract public void print();
}
