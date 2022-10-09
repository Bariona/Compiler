package ast.expression;

import ast.ASTVisitor;
import ast.ExprNode;
import utility.Position;

import java.util.ArrayList;

public class FuncExprNode extends ExprNode {
  public ExprNode callExpr;
  public ArrayList<ExprNode> argumentList;

  public FuncExprNode(ExprNode callExpr, Position pos) {
    super(pos);
    this.callExpr = callExpr;
    this.argumentList = new ArrayList<>();
    this.exprType = null; // ???? function
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
