package AST.Statement;

import AST.ASTvisitor;
import AST.StmtNode;
import Utility.Position;

public class brkcontNode extends StmtNode {
  boolean isBreak, isContinue;

  public brkcontNode(boolean isbreak, Position pos) {
    super(pos);
    if(isbreak) isBreak = true;
    else isContinue = true;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
