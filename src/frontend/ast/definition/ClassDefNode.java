package frontend.ast.definition;

import frontend.ast.ASTVisitor;
import utility.info.ClassInfo;
import utility.Position;
import utility.scope.ClassScope;

import java.util.ArrayList;

public class ClassDefNode extends DefNode {
  public ClassInfo info;
  public ClassScope scope;
  public ArrayList<VarSingleDefNode> varDefs = new ArrayList<>();
  public ArrayList<FuncDefNode> funcDefs = new ArrayList<>();

  public ClassDefNode(String name, Position pos) {
    super(pos);
    scope = new ClassScope(name);
    info = new ClassInfo(name, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
