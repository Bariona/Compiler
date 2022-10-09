package ast.definition;

import ast.*;
import utility.Position;

import java.util.ArrayList;

public class VarDefNode extends DefNode {
//  public BaseType type;

  public ArrayList<VarSingleDefNode> varList;

  public VarDefNode(Position pos) {
    super(pos);
    varList = new ArrayList<>();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
