package AST.Expression;

import AST.*;
import Utility.Position;
import Utility.Type.BaseType;

public class varExprNode extends ExprNode {
  String name;
  public varExprNode(String name, Position pos, BaseType type) {
    super(pos);
    this.name = name;
    this.type = type;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean isAssignable() {
    return true;
  }
}
