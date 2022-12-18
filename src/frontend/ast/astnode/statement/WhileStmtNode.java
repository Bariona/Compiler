package frontend.ast.astnode.statement;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.expression.ExprNode;
import utility.Position;
import utility.scope.LoopScope;

public class WhileStmtNode extends StmtNode {
  public ExprNode condition;
  public StmtNode stmt;
  public LoopScope scope;

  public WhileStmtNode(ExprNode condition, StmtNode stmt, Position pos) {
    super(pos);
    this.condition = condition;
    this.stmt = stmt;
    this.scope = new LoopScope();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
