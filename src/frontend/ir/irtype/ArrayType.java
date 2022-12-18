package frontend.ir.irtype;

public class ArrayType extends IRBaseType {
  public final int size;
  public IRBaseType elementType;

  public ArrayType(int size, IRBaseType elementType) {
    this.size = size;
    this.elementType = elementType;
  }

  @Override
  public boolean match(IRBaseType _it) {
    if (_it instanceof ArrayType it) {
      return this.size == it.size && this.elementType.match(it.elementType);
    } else return false;
  }

  @Override
  public int size() {
    return size * elementType.size();
  }

  @Override
  public String toString() {
    return "[" + this.size + " x " + this.elementType.toString() + "]";
  }
}
