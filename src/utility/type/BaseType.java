package utility.type;

import parser.MxParser;
import utility.Position;
import utility.error.SyntaxError;

abstract public class BaseType {
  public enum BuiltinType {
    VOID, BOOL, NULL, INT, STRING, CLASS, FUNC
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
      return BuiltinType.CLASS;
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

  static public boolean isVoidType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BuiltinType.VOID));
  }

  static public boolean isBoolType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BuiltinType.BOOL));
  }

  static public boolean isIntType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BuiltinType.INT));
  }

  static public boolean isArray(BaseType it) {
    if (it instanceof FuncType) return false;
    return ((VarType) it).dimension > 0;
  }

  static public boolean isStringType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.isSame(new VarType(BuiltinType.STRING));
  }

  static public boolean isClassType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.builtinType == BuiltinType.CLASS;
  }

  static public boolean isNullType(BaseType it) {
    if (it instanceof FuncType) return false;
    return it.builtinType == BuiltinType.NULL;
  }

  static public boolean isPrimitiveType(BaseType it) {
    if (it instanceof FuncType) return false;
    return ((VarType) it).dimension == 0 && it.builtinType != BuiltinType.CLASS;
  }
}