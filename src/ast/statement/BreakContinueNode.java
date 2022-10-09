package ast.statement;

import ast.ASTVisitor;
import ast.StmtNode;
import utility.Position;

public class BreakContinueNode extends StmtNode {
  public boolean isBreak, isContinue;

  public BreakContinueNode(boolean isbreak, Position pos) {
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
