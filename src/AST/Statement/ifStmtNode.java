package AST.Statement;

import AST.*;
import Utility.Position;

public class ifStmtNode extends StmtNode {
  public ExprNode condition;
  public StmtNode thenStmt, elseStmt;

  public ifStmtNode(ExprNode condition, StmtNode thenStmt, StmtNode elseStmt, Position pos) {
    super(pos);
    this.condition = condition;
    this.thenStmt = thenStmt;
    this.elseStmt = elseStmt;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
