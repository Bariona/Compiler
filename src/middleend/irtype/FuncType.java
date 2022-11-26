package middleend.irtype;

import java.util.ArrayList;
import java.util.Collections;

public class FuncType extends IRBaseType {
  public IRBaseType retType;
  public ArrayList<IRBaseType> paraTypeList;

  public FuncType(IRBaseType retType, IRBaseType... paraList) {
    this.retType = retType;
    this.paraTypeList = new ArrayList<>();
    Collections.addAll(paraTypeList, paraList);
  }

  public void addParaType(IRBaseType type) {
    paraTypeList.add(type);
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
