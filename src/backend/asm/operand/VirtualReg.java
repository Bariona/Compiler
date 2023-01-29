package backend.asm.operand;

public class VirtualReg extends Register {
//  static public int regCnt = 0;

  public VirtualReg(String name) {
    super(name);
//    ++regCnt;
  }

//  static public void resetCnt() {
//    regCnt = 0;
//  }

  @Override
  public String toString() {
    if (color != null) return color.toString();
    // === Only for debug ===
    return name + this.hashCode() % 1000;
  }
}
