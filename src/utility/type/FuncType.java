package utility.type;

import java.util.ArrayList;

public class FuncType extends BaseType {
  public VarType retType;
  public ArrayList<VarType> paraListType;

  public FuncType(BuiltinType retType) {
    super(BuiltinType.FUNC);
    this.retType = new VarType(retType);
  }

  public FuncType(VarType retType) {
    super(BuiltinType.FUNC);
    this.retType = (VarType) retType.clone();
  }
//
//  public FuncType(MxParser.TypeNameContext ctx) {
//    super(ctx);
//    this.retType = new VarType(ctx);
//    for (int i = 0; i < ctx.bracket().size(); ++i) {
//      if (ctx.bracket(i).expression() != null) {
//        throw new SyntaxError("Not Var-array type.", new Position(ctx.bracket(i).expression())); // syntax part
//      }
//    }
//  }

  @Override
  public BaseType clone() {
    FuncType type = new FuncType(this.retType);
    this.paraListType.forEach(para -> type.paraListType.add((VarType) para.clone()));
    return type;
  }

  @Override
  public boolean isSame(BaseType it) {
    return false;
  }
}
