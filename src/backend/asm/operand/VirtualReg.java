package backend.asm.operand;

public class VirtualReg extends Register {
  static public int regCnt = 0;

  public VirtualReg(String name) {
    super(name);
    ++regCnt;
  }

  static public void resetCnt() {
    regCnt = 0;
  }

  @Override
  public String toString() {
    if (color == 0)
      return name + this.hashCode();
    return null; // shouldn't goto here.
  }
}
