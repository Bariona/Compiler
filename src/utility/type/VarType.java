package utility.type;

import parser.MxParser;
import utility.error.SyntaxError;
import utility.Position;

public class VarType extends BaseType {
  public int dimension;

//  public VarType(String typename) {
//    super(typename);
//  }

  public VarType(MxParser.TypeNameContext ctx, boolean checkDimValidity) {
    super(ctx);
    this.dimension = ctx.bracket().size();
//    System.out.println(this.type.name() + " " + this.dimension);
    if (checkDimValidity) {
      for (int i = 0; i < ctx.bracket().size(); ++i) {
        if (ctx.bracket(i).expression() != null) {
          throw new SyntaxError("Not Var-array type.", new Position(ctx.bracket(i).expression())); // syntax part
        }
      }
    }
  }

  boolean compare(VarType it) {
    if (it.type != this.type)
      return false;
    if (it.type == BultinType.CLASS && this.ClassName != it.ClassName)
      return false;
    if (it.dimension != this.dimension)
      return false;
    return true;
  }
}
