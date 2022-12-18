package utility.info;

import frontend.ir.Value;
import utility.Position;

abstract public class BaseInfo {
  public String name;
  public Value value;
  public Position pos;


  public BaseInfo(String name, Position pos) {
    this.name = name;
    this.pos = pos;
  }

  public BaseInfo(String name) {
    this.name = name;
    this.pos = null;
  }
}
