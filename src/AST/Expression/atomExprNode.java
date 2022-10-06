package AST.Expression;

import AST.ASTvisitor;
import AST.ExprNode;
import Parser.MxParser;
import Utility.Position;

public class atomExprNode extends ExprNode {
  public MxParser.PrimaryContext atom;

  public atomExprNode(MxParser.PrimaryContext atom, Position pos) {
    super(pos);
    this.atom = atom;
//    if(atom.StringConst() != null) {
//      System.out.println(atom.StringConst().toString());
//      System.out.println("abc\n");
//    }
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
