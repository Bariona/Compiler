package AST.Statement;

import AST.ASTVisitor;
import AST.StmtNode;
import Utility.Position;

public class brkcontNode extends StmtNode {
  public boolean isBreak, isContinue;

  public brkcontNode(boolean isbreak, Position pos) {
    super(pos);
    isBreak = isContinue = false;
    if(isbreak) isBreak = true;
    else isContinue = true;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
