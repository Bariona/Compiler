package utility.type;

import parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

public class VarType extends BaseType {
  public int dimension;

  public VarType(BultinType type) {
    super(type);
    dimension = 0;
  }

  public VarType(MxParser.TypeNameContext ctx, boolean checkDimValidity) {
    super(ctx);
    this.dimension = ctx.bracket().size();
//    System.out.println(this.type.name() + " " + this.dimension);
    if (checkDimValidity) {
      for (int i = 0; i < ctx.bracket().size(); ++i) {
        if (ctx.bracket(i).expression() != null)
          throw new SyntaxError("Not Var-array type.", new Position(ctx.bracket(i).expression())); // syntax part
      }
    }
  }

  @Override
  public BaseType clone() {
    VarType type = new VarType(this.bultinType);
    type.ClassName = this.ClassName;
    type.dimension = this.dimension;
    return type;
  }

  @Override
  public boolean isSame(BaseType _it) {
    if (!(_it instanceof VarType)) return false;
    VarType it = (VarType) _it;
    if (it.bultinType != this.bultinType)
      return false;
    // unsolved: class name
    if (it.dimension != this.dimension)
      return false;
    return true;
  }
}
