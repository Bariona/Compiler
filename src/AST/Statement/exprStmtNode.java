package AST.Statement;

import AST.*;
import Utility.Position;

public class exprStmtNode extends StmtNode {
  ExprNode expr;

  public exprStmtNode(ExprNode expr, Position pos) {
    super(pos);
    this.expr = expr;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
