package utility.info;

import utility.Position;
import utility.type.FuncType;
import utility.type.VarType;

import java.util.ArrayList;

public class FuncInfo extends BaseInfo {
  public FuncType type;
  public ArrayList<VarInfo> paraListInfo;

  public FuncInfo(String name, VarType type, Position pos) {
    super(name, pos);
    this.type.retType = type;
    paraListInfo = new ArrayList<>();
  }

  public FuncInfo(String name, VarType type, VarInfo... args) {
    super(name);
    this.name = name;
    this.type.retType = type;
    paraListInfo = new ArrayList<>();
    for (VarInfo arg : args) {
      paraListInfo.add(arg);
      this.type.paraListType.add(arg.type);
    }
  }
}
