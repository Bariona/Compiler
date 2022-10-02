package AST;

import Utility.Position;

import java.util.ArrayList;

public class classDefNode extends DefNode {
  public String name;
  public ArrayList<DefNode> varDefs = new ArrayList<>();
  public ArrayList<DefNode> fucDefs = new ArrayList<>();

  public classDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
