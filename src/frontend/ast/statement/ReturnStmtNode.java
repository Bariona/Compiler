package frontend.ast.statement;

import frontend.ast.ASTVisitor;
import frontend.ast.expression.ExprNode;
import utility.Position;

public class ReturnStmtNode extends StmtNode {
  public ExprNode ret;

  public ReturnStmtNode(ExprNode ret, Position pos) {
    super(pos);
    this.ret = ret;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
