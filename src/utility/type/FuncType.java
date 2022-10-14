package utility.type;

import parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

public class FuncType extends BaseType {
  public int dimension;

  public FuncType(BultinType type) {
    super(type);
    this.dimension = 0;
  }

  public FuncType(MxParser.TypeNameContext ctx) {
    super(ctx);
    this.dimension = ctx.bracket().size();
    for (int i = 0; i < ctx.bracket().size(); ++i) {
      if (ctx.bracket(i).expression() != null) {
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
  boolean isSame(BaseType it) {
    return false;
  }
}
