package Utility.Type;

import Parser.MxParser;

public class VarType extends BaseType {
  public int dimension;

//  public VarType(String typename) {
//    super(typename);
//  }

  public VarType(MxParser.TypeNameContext ctx) {
    super(ctx);
    this.dimension = ctx.bracket().size();
    System.out.println(this.type.name() + " " + this.dimension);
    for(int i = 0; i < ctx.bracket().size(); ++i) {
      if(ctx.bracket(i).expression() != null) {
        throw new IllegalArgumentException(); // semantic part ??
      }
    }
  }
}
