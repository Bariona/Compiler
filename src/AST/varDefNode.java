package AST;

import Utility.Position;

import java.util.ArrayList;

public class varDefNode extends DefNode {
//  public BaseType type;

  public ArrayList<varSingleDefNode> varlist = new ArrayList<>();

  public varDefNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
