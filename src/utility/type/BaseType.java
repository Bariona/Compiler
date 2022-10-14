package utility.type;

import parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

abstract public class BaseType {
  public enum BultinType {
    VOID, BOOL, INT, STRING, CLASS, NULL, NEW
  }

  public BultinType bultinType;
  public String ClassName;

  public BaseType() {}
  public BaseType(BultinType type) {
    this.bultinType = type;
  }

  public BaseType(MxParser.TypeNameContext ctx) {
    ClassName = null;
    this.bultinType = matchType(ctx);
  }

  abstract public BaseType clone();

  BultinType matchType(MxParser.TypeNameContext ctx) {
    if (ctx.Identifier() != null) {

      ClassName = ctx.Identifier().toString();
      System.out.println(ClassName);
      return BultinType.CLASS; // need a name
    }
    if (ctx.Bool() != null) return BultinType.BOOL;
    if (ctx.Int() != null) return BultinType.INT;
    if (ctx.String() != null) return BultinType.STRING;
    if (ctx.Void() != null) return BultinType.VOID;
    throw new SyntaxError("Not a type.", new Position(ctx));
  }

  abstract public boolean isSame(BaseType it);

  public String typename() {
    return this.bultinType.name();
  }
}