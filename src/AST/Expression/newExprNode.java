package AST.Expression;

import AST.ASTVisitor;
import AST.ExprNode;
import Utility.Position;
import Utility.Type.VarType;

import java.util.ArrayList;

public class newExprNode extends ExprNode {
  public ArrayList<ExprNode> DimensionExpr;

  public newExprNode(VarType type, Position pos) {
    super(pos);
    DimensionExpr = new ArrayList<>();
    this.ExprType = type;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
