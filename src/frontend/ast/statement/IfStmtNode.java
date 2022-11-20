package frontend.ast.statement;

import frontend.ast.ASTVisitor;
import frontend.ast.expression.ExprNode;
import utility.Position;

public class IfStmtNode extends StmtNode {
  public ExprNode condition;
  public StmtNode thenStmt, elseStmt;

  public IfStmtNode(ExprNode condition, StmtNode thenStmt, StmtNode elseStmt, Position pos) {
    super(pos);
    this.condition = condition;
    this.thenStmt = thenStmt;
    this.elseStmt = elseStmt;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
