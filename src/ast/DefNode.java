package ast;

import utility.Position;

abstract public class DefNode extends StmtNode {
  public DefNode(Position pos) {
    super(pos);
  }
}
