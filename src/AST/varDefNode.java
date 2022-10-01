package AST;

import Utility.Position;
import Utility.Type.BaseType;

public class varDefNode extends DefNode {
  BaseType type;
  String name; // 这里不应该加一个name, 应该是VarTerm (根据g4的规则来)

  public varDefNode(BaseType type, String name, Position pos) {
    super(pos);
    this.type = type;
    this.name = name;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
