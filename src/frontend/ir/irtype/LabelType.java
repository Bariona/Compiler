package frontend.ir.irtype;

public class LabelType extends IRBaseType {
  @Override
  public boolean match(IRBaseType it) {
    return it instanceof LabelType;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public String toString() {
    return "label";
  }
}
