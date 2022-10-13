package ast.info;

import utility.Position;
import utility.type.VarType;

public class VarInfo extends BaseInfo {
  public VarType type;

  public VarInfo(String name, VarType type, Position pos) {
    super(name, pos);
    this.type = type;
  }

  public VarInfo(String name, VarType type) {
    super(name);
    this.type = type;
  }
}
