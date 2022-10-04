package AST;

import Utility.Position;

public class varStmtNode extends StmtNode {
  public varDefNode Defstmt;

  public varStmtNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
