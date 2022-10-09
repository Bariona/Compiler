package ast.statement;

import ast.*;
import utility.Position;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
  public ArrayList<StmtNode> stmts;

  public SuiteStmtNode(Position pos) {
    super(pos);
    stmts = new ArrayList<>();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
