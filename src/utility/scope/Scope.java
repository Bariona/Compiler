package utility.scope;

import ast.info.*;
import utility.Position;
import utility.error.SyntaxError;

import java.util.HashMap;

abstract public class Scope {
  public HashMap<String, VarInfo> varInfoTable;

  public Scope() {
    varInfoTable = new HashMap<>();
  }

  abstract public void addItem(BaseInfo info);

  abstract public VarInfo queryVarInfo(String name, Position pos);
  abstract public FuncInfo queryFuncInfo(String name, Position pos);
  abstract public ClassInfo queryClassInfo(String name, Position pos);

}
