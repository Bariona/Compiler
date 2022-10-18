package ast.expression;

import ast.ASTVisitor;
import parser.MxParser;
import utility.Position;

public class AtomExprNode extends ExprNode {
  public MxParser.PrimaryContext atom;

  public AtomExprNode(MxParser.PrimaryContext atom, Position pos) {
    super(pos);
    this.atom = atom;
  }

  @Override
  public boolean isAssignable() {
    return atom.Identifier() != null;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
