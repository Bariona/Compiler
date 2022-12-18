package frontend.ast.astnode;

import frontend.ast.ASTVisitor;
import frontend.ir.Value;
import utility.Position;

abstract public class ASTNode {
  public Position pos;
  public Value value;

  public ASTNode(Position pos) {
    this.pos = pos;
  }

  abstract public void accept(ASTVisitor visitor);

}
