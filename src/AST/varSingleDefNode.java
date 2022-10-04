package AST;

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
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
