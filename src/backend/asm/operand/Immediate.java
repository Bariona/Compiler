package backend.asm.operand;

public class
Immediate extends BaseOperand {
  public int value;

  public Immediate(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}
