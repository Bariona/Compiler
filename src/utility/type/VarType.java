package utility.type;

import frontend.parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

public class VarType extends BaseType {
  public int dimension;

  public VarType(BuiltinType type) {
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
    VarType type = new VarType(this.builtinType);
    type.ClassName = this.ClassName;
    type.dimension = this.dimension;
    return type;
  }

  @Override
  public boolean isSame(BaseType _it) {
    if (!(_it instanceof VarType it)) return false;
    if (it.builtinType == BuiltinType.NULL)
      return true;
    if (it.builtinType != this.builtinType)
      return false;
    if (it.builtinType == BuiltinType.CLASS && !it.ClassName.equals(this.ClassName))
      return false;
    // unsolved: class name
    return it.dimension == this.dimension;
  }
}
