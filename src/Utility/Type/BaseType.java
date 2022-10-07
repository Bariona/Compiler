package Utility.Type;

import Parser.MxParser;
import Utility.Error.SyntaxError;
import Utility.Position;

import java.util.HashMap;

abstract public class BaseType {
  public enum BultinType {
    VOID, BOOL, INT, STRING, CLASS, NULL, NEW
  }

  public BultinType type;
  String ClassName;
//  HashMap<String, BultinType> members = null;

  BaseType(BultinType type) {
    this.type = type;
  }
  public BaseType(MxParser.TypeNameContext ctx) {
    this.type = MatchType(ctx);
    ClassName = null;
  }

//  public BaseType(String typename) {
//    System.out.println(typename);
//    System.out.println(typename.getClass().toString());
//    this.type = BultinType.valueOf(typename);
//  }

  public BultinType MatchType(MxParser.TypeNameContext ctx) {
    if(ctx.Identifier() != null) {
      ClassName = ctx.Identifier().toString();
      return BultinType.CLASS; // need a name
    }
    if(ctx.Bool() != null) return BultinType.BOOL;
    if(ctx.Int() != null) return BultinType.INT;
    if(ctx.String() != null) return BultinType.STRING;
    if(ctx.Void() != null) return BultinType.VOID;
    throw new SyntaxError("Not a type.", new Position(ctx));
  }

  public String typename() {
    return this.type.name();
  }
}