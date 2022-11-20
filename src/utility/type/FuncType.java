package utility.type;

import java.util.ArrayList;

public class FuncType extends BaseType {
  public VarType retType;
  public ArrayList<VarType> paraListType = new ArrayList<>();

  public FuncType(BuiltinType retType) {
    super(BuiltinType.FUNC);
    this.retType = new VarType(retType);
  }

  public FuncType(VarType retType) {
    super(BuiltinType.FUNC);
    this.retType = (VarType) retType.clone();
  }

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
