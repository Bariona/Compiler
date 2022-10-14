package ast.statement;

import ast.ASTNode;
import utility.Position;

abstract public class StmtNode extends ASTNode {

  public StmtNode(Position pos) {
    super(pos);
  }

//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
