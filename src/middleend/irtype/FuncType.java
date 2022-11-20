package middleend.irtype;

import java.util.ArrayList;

public class FuncType extends IRBaseType {
  public IRBaseType retType;
  public ArrayList<IRBaseType> paraListType;

  public FuncType(IRBaseType retType) {
    this.retType = retType;
    this.paraListType = new ArrayList<>();
  }

  @Override
  public boolean match(IRBaseType it) {
    return it instanceof FuncType;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public String toString() {
    return retType.toString();
  }
}
