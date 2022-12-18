package frontend.ast.astnode.definition;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.expression.ExprNode;
import utility.info.VarInfo;
import utility.Position;

public class VarSingleDefNode extends DefNode {
  public VarInfo info;
  public ExprNode expr;

  public VarSingleDefNode(String name, Position pos) {
    super(pos);
    this.info = new VarInfo(name, null, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
