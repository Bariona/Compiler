package ast.statement;

import ast.*;
import utility.Position;

public class ForStmtNode extends StmtNode {
  public ExprNode initial, condition, step;
  public StmtNode stmt;

  public ForStmtNode(ExprNode initial, ExprNode condition, ExprNode step, StmtNode stmt, Position pos) {
    super(pos);
    this.initial = initial;
    this.condition = condition;
    this.step = step;
    this.stmt = stmt;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
