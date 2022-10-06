package AST;

import Utility.Position;

abstract public class DefNode extends StmtNode {
  public DefNode(Position pos) {
    super(pos);
  }
}
