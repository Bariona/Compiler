package Utility.Type;

import Parser.MxParser;

import java.util.HashMap;

public class BaseType {
  public enum BultinType {
    VOID, BOOL, INT, STRING, CLASS, NULL, NEW
  }

  public BultinType type;
  HashMap<String, BultinType> members = null;

  BaseType(BultinType type) {
    this.type = type;
  }

//  public BaseType(String typename) {
//    System.out.println(typename);
//    System.out.println(typename.getClass().toString());
//    this.type = BultinType.valueOf(typename);
//  }

  public BultinType MatchType(MxParser.TypeNameContext ctx) {
    if(ctx.Identifier() != null) return BultinType.CLASS;
    if(ctx.Bool() != null) return BultinType.BOOL;
    if(ctx.Int() != null) return BultinType.INT;
    if(ctx.String() != null) return BultinType.STRING;
    if(ctx.Void() != null) return BultinType.VOID;
    throw new IllegalArgumentException();
  }

  public BaseType(MxParser.TypeNameContext ctx) {
    this.type = MatchType(ctx);
  }
}