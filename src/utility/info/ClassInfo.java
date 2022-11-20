package utility.info;

import utility.Position;

import java.util.ArrayList;

public class ClassInfo extends BaseInfo {
  public ArrayList<VarInfo> varInfos;
  public ArrayList<FuncInfo> funcInfos;

  // unsolved: 可以在查找部分做一个小优化 去掉List?
  public ClassInfo(String name, Position pos) {
    super(name, pos);
    this.varInfos = new ArrayList<>();
    this.funcInfos = new ArrayList<>();
  }

  public VarInfo findVarInfo(String varName) {
    for (VarInfo info : varInfos) {
      if (info.name.equals(varName))
        return info;
    }
    return null;
  }

  public FuncInfo findFuncInfo(String funcName) {
    for (FuncInfo info : funcInfos) {
      if (info.name.equals(funcName))
        return info;
    }
    return null;
  }
}
