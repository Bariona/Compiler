package backend.asm.operand;

public class PhysicalReg extends Register {
  public PhysicalReg(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
