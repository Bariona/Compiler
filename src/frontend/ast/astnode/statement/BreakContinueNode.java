package frontend.ast.astnode.statement;

import frontend.ast.ASTVisitor;
import utility.Position;

public class BreakContinueNode extends StmtNode {
  public boolean isBreak, isContinue;

  public BreakContinueNode(boolean isBreak, Position pos) {
    super(pos);
    this.isBreak = isContinue = false;
    if (isBreak) this.isBreak = true;
    else isContinue = true;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
