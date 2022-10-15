package utility.info;

import utility.Position;
import utility.type.FuncType;
import utility.type.VarType;

import java.util.ArrayList;

public class FuncInfo extends BaseInfo {
  public FuncType funcType;
  public ArrayList<VarInfo> paraListInfo;

  public FuncInfo(String name, VarType type, Position pos) {
    super(name, pos);
    this.funcType = new FuncType(type);
    paraListInfo = new ArrayList<>();
  }

  public FuncInfo(String name, VarType type, VarInfo... args) {
    super(name);
    this.name = name;
    this.funcType = new FuncType(type);
    paraListInfo = new ArrayList<>();
    for (VarInfo arg : args) {
      paraListInfo.add(arg);
      this.funcType.paraListType.add(arg.type);
    }
  }
}
