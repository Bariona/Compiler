package AST.Expression;

import AST.ASTvisitor;
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
    this.restype = null; // ???? function
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
