package backend.asm.operand;

public class Register extends BaseOperand {
  public String name;

  public Register(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
