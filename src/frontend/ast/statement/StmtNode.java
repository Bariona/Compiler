package frontend.ast.statement;

import frontend.ast.ASTNode;
import utility.Position;

abstract public class StmtNode extends ASTNode {

  public StmtNode(Position pos) {
    super(pos);
  }

}
