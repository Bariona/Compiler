package AST.Statement;

import AST.*;
import Utility.Position;

public class forStmtNode extends StmtNode {
  ExprNode initial, condition, step;
  StmtNode stmt;

  public forStmtNode(ExprNode initial, ExprNode condition, ExprNode step, StmtNode stmt, Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
