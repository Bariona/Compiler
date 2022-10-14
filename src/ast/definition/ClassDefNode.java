package ast.definition;

import ast.ASTVisitor;
import utility.info.ClassInfo;
import utility.Position;
import utility.scope.ClassScope;

import java.util.ArrayList;

public class ClassDefNode extends DefNode {
  public ClassInfo info;
  public ArrayList<VarSingleDefNode> varDefs = new ArrayList<>();
  public ArrayList<FuncDefNode> funcDefs = new ArrayList<>();
  public ClassScope scope;

  public ClassDefNode(String name, Position pos) {
    super(pos);
    info = new ClassInfo(name, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
