package frontend.ir.irtype;

public class VoidType extends IRBaseType {

  @Override
  public boolean match(IRBaseType it) {
    return it instanceof VoidType;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public String toString() {
    return "void";
  }
}
