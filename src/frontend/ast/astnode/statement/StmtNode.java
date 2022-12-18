package frontend.ast.astnode.statement;

import frontend.ast.astnode.ASTNode;
import utility.Position;

abstract public class StmtNode extends ASTNode {

  public StmtNode(Position pos) {
    super(pos);
  }

}
