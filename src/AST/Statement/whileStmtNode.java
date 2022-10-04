package AST.Statement;

import AST.*;
import Utility.Position;

public class whileStmtNode extends StmtNode {
  ExprNode condition;
  StmtNode stmt;

  public whileStmtNode(ExprNode condition, StmtNode stmt, Position pos) {
    super(pos);
    this.condition = condition;
    this.stmt = stmt;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
