package frontend.ast.astnode.statement;

import frontend.ast.ASTVisitor;
import utility.Position;
import utility.scope.SuiteScope;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
  public ArrayList<StmtNode> stmts;
  public SuiteScope scope;

  public SuiteStmtNode(Position pos) {
    super(pos);
    stmts = new ArrayList<>();
    scope = new SuiteScope();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
