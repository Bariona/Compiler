package AST;

import Utility.Position;
import Utility.Type.BaseType;

import java.util.ArrayList;

public class fucDefNode extends DefNode {
  public BaseType type;
  public String name;
  public ArrayList<DefNode> parameterList = new ArrayList<>();

  public fucDefNode(String name, BaseType type, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
