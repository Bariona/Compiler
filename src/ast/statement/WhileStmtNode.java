package ast.statement;

import ast.ASTVisitor;
import ast.expression.ExprNode;
import utility.Position;

public class WhileStmtNode extends StmtNode {
  public ExprNode condition;
  public StmtNode stmt;

  public WhileStmtNode(ExprNode condition, StmtNode stmt, Position pos) {
    super(pos);
    this.condition = condition;
    this.stmt = stmt;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
