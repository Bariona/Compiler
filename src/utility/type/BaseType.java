package utility.type;

import parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

abstract public class BaseType {
  public enum BuiltinType {
    VOID, BOOL, INT, STRING, CLASS, FUNC
  }

  public BuiltinType builtinType;
  public String ClassName;

  public BaseType(BuiltinType type) {
    this.builtinType = type;
  }

  public BaseType(MxParser.TypeNameContext ctx) {
    ClassName = null;
    this.builtinType = matchType(ctx);
  }

  abstract public BaseType clone();

  BuiltinType matchType(MxParser.TypeNameContext ctx) {
    if (ctx.Identifier() != null) {

      ClassName = ctx.Identifier().toString();
      System.out.println(ClassName);
      return BuiltinType.CLASS; // need a name
    }
    if (ctx.Bool() != null) return BuiltinType.BOOL;
    if (ctx.Int() != null) return BuiltinType.INT;
    if (ctx.String() != null) return BuiltinType.STRING;
    if (ctx.Void() != null) return BuiltinType.VOID;
    throw new SyntaxError("Not a type.", new Position(ctx));
  }

  abstract public boolean isSame(BaseType it);

  public String typename() {
    return this.builtinType.name();
  }
}