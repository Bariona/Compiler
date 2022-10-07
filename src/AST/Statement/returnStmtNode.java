package AST.Statement;

import AST.*;
import Utility.Position;

public class returnStmtNode extends StmtNode {
  public ExprNode ret;

  public returnStmtNode(ExprNode ret, Position pos) {
    super(pos);
    this.ret = ret;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
