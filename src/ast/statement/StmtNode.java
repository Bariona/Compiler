package ast.statement;

import ast.ASTNode;
import utility.Position;

abstract public class StmtNode extends ASTNode {

  public StmtNode(Position pos) {
    super(pos);
  }

}
