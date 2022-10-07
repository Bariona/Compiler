package AST.Definition;

import AST.*;
import Utility.Position;
import Utility.Type.VarType;

public class varSingleDefNode extends DefNode {
  public String name;
  public ExprNode expr;
  public VarType type;

  public varSingleDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
