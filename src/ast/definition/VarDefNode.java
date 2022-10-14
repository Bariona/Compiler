package ast.definition;

import ast.ASTVisitor;
import utility.Position;

import java.util.ArrayList;

public class VarDefNode extends DefNode {
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
