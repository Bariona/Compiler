package AST;

import Utility.Position;
import Utility.Type.BaseType;

abstract public class ExprNode extends ASTNode {
  public BaseType type;

  public ExprNode(Position pos) {
    super(pos);
  }

  boolean isAssignable() {
    return false;
  }

//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
