package ast.definition;

import ast.*;
import ast.info.ClassInfo;
import utility.Position;

import java.util.ArrayList;

public class ClassDefNode extends DefNode {
  public ClassInfo info;
  public ArrayList<VarDefNode> varDefs = new ArrayList<>();
  public ArrayList<FuncDefNode> funcDefs = new ArrayList<>();

  public ClassDefNode(String name, Position pos) {
    super(pos);
    info = new ClassInfo(name, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
