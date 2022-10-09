package ast.definition;

import ast.*;
import utility.Position;
import utility.type.VarType;

public class VarSingleDefNode extends DefNode {
  public String name;
  public ExprNode expr;
  public VarType type;

  public VarSingleDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
