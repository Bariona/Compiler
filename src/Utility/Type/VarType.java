package Utility.Type;

import Parser.MxParser;
import Utility.Error.SemanticError;
import Utility.Error.SyntaxError;
import Utility.Position;

public class VarType extends BaseType {
  public int dimension;

//  public VarType(String typename) {
//    super(typename);
//  }

  public VarType(MxParser.TypeNameContext ctx, boolean checkDimValidity) {
    super(ctx);
    this.dimension = ctx.bracket().size();
//    System.out.println(this.type.name() + " " + this.dimension);
    if(checkDimValidity) {
      for(int i = 0; i < ctx.bracket().size(); ++i) {
        if(ctx.bracket(i).expression() != null) {
          throw new SyntaxError("Not Var-array type.", new Position(ctx.bracket(i).expression())); // syntax part
        }
      }
    }
  }
}
