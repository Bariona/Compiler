package frontend.ast.astnode;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.definition.DefNode;
import utility.Position;
import utility.scope.RootScope;

import java.util.ArrayList;

public class RootNode extends ASTNode {
  public ArrayList<DefNode> defs;
  public RootScope scope;

  public RootNode(Position pos) {
    super(pos);
    defs = new ArrayList<>();
    scope = new RootScope();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
