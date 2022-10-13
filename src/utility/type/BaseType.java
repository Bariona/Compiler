package utility.type;

import parser.MxParser;
import utility.error.SyntaxError;
import utility.Position;

abstract public class BaseType {
  public enum BultinType {
    VOID, BOOL, INT, STRING, CLASS, NULL, NEW
  }

  public BultinType type;
  public String ClassName;

  public BaseType(BultinType type) {
    this.type = type;
  }

  public BaseType(MxParser.TypeNameContext ctx) {
    ClassName = null;
    this.type = matchType(ctx);
  }

//  public BaseType(String typename) {
//    System.out.println(typename);
//    System.out.println(typename.getClass().toString());
//    this.type = BultinType.valueOf(typename);
//  }

  public BultinType matchType(MxParser.TypeNameContext ctx) {
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

  public String typename() {
    return this.type.name();
  }
}