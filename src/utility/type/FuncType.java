package utility.type;

import parser.MxParser;
import utility.error.SyntaxError;
import utility.Position;
import java.util.ArrayList;

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
}
