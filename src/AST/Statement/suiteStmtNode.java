package AST.Statement;

import AST.*;
import Utility.Position;

import java.util.ArrayList;

public class suiteStmtNode extends StmtNode {
  public ArrayList<StmtNode> stmts;

  public suiteStmtNode(Position pos) {
    super(pos);
    stmts = new ArrayList<>();
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
