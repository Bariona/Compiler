package AST;

import Utility.Position;
import Utility.Type.BaseType;

abstract public class ExprNode extends StmtNode {
  public BaseType ExprType;

  public ExprNode(Position pos) {
    super(pos);
    ExprType = null;
  }

  public boolean isAssignable() {
    return false;
  }

//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
