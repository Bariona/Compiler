package frontend.ast.astnode.expression;

import frontend.ast.astnode.statement.StmtNode;
import utility.Position;
import utility.type.BaseType;

abstract public class ExprNode extends StmtNode {
  public BaseType exprType;

  public ExprNode(Position pos) {
    super(pos);
    exprType = null;
  }

  public boolean isAssignable() {
    return false;
  }

//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
