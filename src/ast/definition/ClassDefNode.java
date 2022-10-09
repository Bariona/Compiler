package ast.definition;

import ast.*;
import utility.Position;

import java.util.ArrayList;

public class ClassDefNode extends DefNode {
  public String name;
  public ArrayList<VarDefNode> varDefs = new ArrayList<>();
  public ArrayList<FuncDefNode> funcDefs = new ArrayList<>();

  public ClassDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
