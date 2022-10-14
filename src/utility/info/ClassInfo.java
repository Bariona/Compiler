package utility.info;

import utility.Position;

import java.util.ArrayList;

public class ClassInfo extends BaseInfo {
  public ArrayList<VarInfo> varInfos;
  public ArrayList<FuncInfo> funcInfos;

  public ClassInfo(String name, Position pos) {
    super(name, pos);
    this.varInfos = new ArrayList<>();
    this.funcInfos = new ArrayList<>();
  }
}
