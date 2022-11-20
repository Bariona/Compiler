package frontend.ast.definition;

import frontend.ast.statement.StmtNode;
import utility.Position;

abstract public class DefNode extends StmtNode {
  public DefNode(Position pos) {
    super(pos);
  }
}
