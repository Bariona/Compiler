package utility.info;

import utility.Position;
import utility.type.FuncType;

import java.util.ArrayList;

public class FuncInfo extends BaseInfo {
  public FuncType type;
  public ArrayList<VarInfo> parameterList;

  public FuncInfo(String name, FuncType type, Position pos) {
    super(name, pos);
    this.type = type;
    parameterList = new ArrayList<>();
  }

  public FuncInfo(String name, FuncType type, VarInfo... args) {
    super(name);
    this.name = name;
    this.type = type;
    parameterList = new ArrayList<>();
    for (VarInfo arg : args)
      parameterList.add(arg);
  }
}
