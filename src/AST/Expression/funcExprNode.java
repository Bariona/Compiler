package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Utility.Position;

import java.util.ArrayList;

public class funcExprNode extends ExprNode {
  public ExprNode callExpr;
  public ArrayList<ExprNode> argumentList;

  public funcExprNode(ExprNode callExpr, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.argumentList = new ArrayList<>();
    this.ExprType = null; // ???? function
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
