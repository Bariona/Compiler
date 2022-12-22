package backend.asm.operand;

public class VirtualReg extends Register {
  public VirtualReg(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
