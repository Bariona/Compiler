package ast;

import utility.Position;
import utility.scope.Scope;

import java.util.ArrayList;

public class RootNode extends ASTNode {
  public ArrayList<DefNode> defs;
  public Scope scope;

  public RootNode(Position pos) {
    super(pos);
    defs = new ArrayList<>();
    scope = new Scope();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
