package frontend.ir.irtype;

public class BoolType extends IRBaseType { // this type is not necessary

  @Override
  public boolean match(IRBaseType it) {
    return it instanceof BoolType;
  }

  @Override
  public int size() {
    return 4;
  }

  @Override
  public String toString() {
    return "i1";
  }
}
