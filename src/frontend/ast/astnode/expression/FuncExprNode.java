package frontend.ast.astnode.expression;

import frontend.ast.ASTVisitor;
import utility.Position;

import java.util.ArrayList;

public class FuncExprNode extends ExprNode {
  public ExprNode callExpr;
  public ArrayList<ExprNode> argumentList;

  public FuncExprNode(ExprNode callExpr, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.argumentList = new ArrayList<>();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
