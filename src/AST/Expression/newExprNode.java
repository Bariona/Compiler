package AST.Expression;

import AST.ASTvisitor;
import AST.ExprNode;
import Parser.MxParser;
import Utility.Position;
import Utility.Type.VarType;

import java.util.ArrayList;

public class newExprNode extends ExprNode {
  public ArrayList<ExprNode> DimensionExpr;

  public newExprNode(VarType type, Position pos) {
    super(pos);
    DimensionExpr = new ArrayList<>();
    this.restype = type;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
