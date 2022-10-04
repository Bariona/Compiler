package AST.Definition;

import AST.*;
import Utility.Position;

import java.util.ArrayList;

public class classDefNode extends DefNode {
  public String name;
  public ArrayList<varDefNode> varDefs = new ArrayList<>();
  public ArrayList<funcDefNode> funcDefs = new ArrayList<>();

  public classDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
