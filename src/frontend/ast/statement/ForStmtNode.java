package frontend.ast.statement;

import frontend.ast.ASTVisitor;
import frontend.ast.definition.VarSingleDefNode;
import frontend.ast.expression.ExprNode;
import utility.Position;
import utility.scope.LoopScope;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode {
  public ExprNode initial, condition, step;
  public StmtNode stmt;
  public LoopScope scope;
  public ArrayList<VarSingleDefNode> initVarDef = new ArrayList<>();

  public ForStmtNode(ExprNode initial, ExprNode condition, ExprNode step, StmtNode stmt, Position pos) {
    super(pos);
    this.initial = initial;
    this.condition = condition;
    this.step = step;
    this.stmt = stmt;
    this.scope = new LoopScope();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
