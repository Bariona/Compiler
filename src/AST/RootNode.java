package AST;

import Utility.Position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
  public ArrayList<DefNode> Defs = new ArrayList<>(); // initialize in case of null!

  public RootNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
