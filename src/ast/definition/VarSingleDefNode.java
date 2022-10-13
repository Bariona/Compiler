package ast.definition;

import ast.*;
import ast.info.VarInfo;
import utility.Position;
import utility.type.VarType;

public class VarSingleDefNode extends DefNode {
  public VarInfo info;
  public ExprNode expr;

  public VarSingleDefNode(String name, Position pos) {
    super(pos);
    this.info = new VarInfo(name, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
