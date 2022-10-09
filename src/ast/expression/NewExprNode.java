package ast.expression;

import ast.ASTVisitor;
import ast.ExprNode;
import utility.Position;
import utility.type.VarType;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {
  public ArrayList<ExprNode> dimensionExpr;

  public NewExprNode(VarType type, Position pos) {
    super(pos);
    dimensionExpr = new ArrayList<>();
    this.exprType = type;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
