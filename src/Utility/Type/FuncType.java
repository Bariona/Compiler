package Utility.Type;

import Parser.MxParser;
import Utility.Error.SyntaxError;
import Utility.Position;

public class FuncType extends BaseType {
  public int dimension;

  public FuncType(MxParser.TypeNameContext ctx) {
    super(ctx);
    this.dimension = ctx.bracket().size();
    for(int i = 0; i < ctx.bracket().size(); ++i) {
      if(ctx.bracket(i).expression() != null) {
        throw new SyntaxError("Not Var-array type.", new Position(ctx.bracket(i).expression())); // syntax part
      }
    }
  }
}
