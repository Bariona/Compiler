package frontend.ir.irtype;

import java.util.ArrayList;

public class StructType extends IRBaseType {
  public String className;
  public ArrayList<IRBaseType> memberType;

  private int size;
  private boolean calculated;

  public StructType(String clsName) {
    this.className = clsName;
    this.memberType = new ArrayList<>();
    this.calculated = false;
  }

  public void addMember(IRBaseType type) {
    memberType.add(type);
  }

  public int calcOffset(int idx) {
    int ret = 0;
    for (int i = 0; i < idx; ++i)
      ret += memberType.get(i).size();
    return ret;
  }

  @Override
  public boolean match(IRBaseType _it) {
    if (_it instanceof StructType it) {
      return this.className.equals(it.className);
    } else return false;
  }

  @Override
  public int size() {
    if (!calculated) {
      calculated = true;
      for (var it : this.memberType)
        this.size += it.size();
    }
    return this.size;
  }

  @Override
  public String toString() {
    return "%struct." + this.className;
  }
}
