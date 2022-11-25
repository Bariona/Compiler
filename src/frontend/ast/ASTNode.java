package frontend.ast;

import middleend.Value;
import utility.Position;

abstract public class ASTNode {
  public Position pos;
  public Value value;

  public ASTNode(Position pos) {
    this.pos = pos;
  }

  abstract public void accept(ASTVisitor visitor);

}
