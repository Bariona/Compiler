package frontend.ast.astnode.definition;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.statement.StmtNode;
import utility.info.FuncInfo;
import utility.Position;
import utility.scope.FuncScope;
import utility.type.VarType;

public class FuncDefNode extends DefNode {
  public FuncInfo info;
  public StmtNode stmts;
  public FuncScope scope;

  public FuncDefNode(String name, VarType type, Position pos) {
    super(pos);
    this.info = new FuncInfo(name, type, pos);
    scope = new FuncScope(this.info);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
