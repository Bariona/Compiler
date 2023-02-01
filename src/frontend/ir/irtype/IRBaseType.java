package frontend.ir.irtype;

public abstract class IRBaseType {

  abstract public boolean match(IRBaseType it);

  abstract public int size();

  abstract public String toString();

  public IRBaseType dePointed() {
    if (this instanceof PtrType ptr)
      return ptr.target;
    throw new RuntimeException("IR type depointed error.");
  }
}