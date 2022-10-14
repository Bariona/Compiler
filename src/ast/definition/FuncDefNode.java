package ast.definition;

import ast.ASTVisitor;
import ast.statement.StmtNode;
import utility.info.FuncInfo;
import utility.Position;
import utility.type.FuncType;

public class FuncDefNode extends DefNode {
  public FuncInfo info;
  public StmtNode stmts;

  public FuncDefNode(String name, FuncType type, Position pos) {
    super(pos);
    this.info = new FuncInfo(name, type, pos);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
