package middleend.irtype;

public abstract class IRBaseType {

  abstract public boolean match(IRBaseType it);

  abstract public int size();

  abstract public String toString();

}