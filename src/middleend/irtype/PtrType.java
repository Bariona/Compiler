package middleend.irtype;

public class PtrType extends IRBaseType {
  public IRBaseType target;
  final int dim;

  public PtrType() {
    this.target = null;
    this.dim = 0;
  }

  public PtrType(IRBaseType target) {
    this.target = target;
    this.dim = 1 + (target instanceof PtrType ? ((PtrType) target).dim : 0);
  }

  @Override
  public boolean match(IRBaseType _it) {
    if (_it instanceof PtrType it) {
      return this.target.match(it.target);
    } else return false;
  }

  @Override
  public int size() {
    return 8;
  }

  @Override
  public String toString() {
    return target.toString() + "*";
  }
}
