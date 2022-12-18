package frontend.ir.irtype;

public class IntType extends IRBaseType {
  final int bitWidth;

  public IntType(int bitWidth) {
    assert (bitWidth & 7) == 0;
    this.bitWidth = bitWidth;
  }
  public IntType() {
    this.bitWidth = 32;
  }

  @Override
  public boolean match(IRBaseType _it) {
    if (_it instanceof IntType it) {
      return this.bitWidth == it.bitWidth;
    } else return false;
  }

  @Override
  public int size() {
    return this.bitWidth / 8;
  }

  @Override
  public String toString() {
    return "i" + this.bitWidth;
  }
}
