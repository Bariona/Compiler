package AST.Definition;

import AST.*;
import Utility.Position;

import java.util.ArrayList;

public class varDefNode extends DefNode {
//  public BaseType type;

  public ArrayList<varSingleDefNode> varlist;

  public varDefNode(Position pos) {
    super(pos);
    varlist = new ArrayList<>();
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}