package ast.definition;

import ast.*;
import ast.info.FuncInfo;
import utility.Position;
import utility.scope.Scope;
import utility.type.FuncType;

public class FuncDefNode extends DefNode {
  public FuncInfo info;
  public StmtNode stmts;
  public Scope scope;

  public FuncDefNode(String name, FuncType type, Position pos) {
    super(pos);
    this.info = new FuncInfo(name, type, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
