package AST;

import Utility.Position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
  public ArrayList<DefNode> Defs;

  public RootNode(Position pos) {
    super(pos);
    Defs = new ArrayList<>(); // initialize in case of null!
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
