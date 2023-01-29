package backend.asm.operand;

public class Register extends BaseOperand {
  public String name;
  public PhysicalReg color = null;
  public Immediate offset;

  public Register(String name) {
    this.name = name;
    offset = new Immediate(0);
  }

  public void setOffset(Immediate offset) {
    this.offset = offset;
  }

  @Override
  public String toString() {
    return name;
  }
}
