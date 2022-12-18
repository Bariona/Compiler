package frontend.ast.astnode.definition;

import frontend.ast.astnode.statement.StmtNode;
import utility.Position;

abstract public class DefNode extends StmtNode {
  public DefNode(Position pos) {
    super(pos);
  }
}
