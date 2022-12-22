package backend.asm.operand;

public class GlobalReg extends Register {
  public String str = null;

  public GlobalReg(String name) {
    super(name);
  }

  public GlobalReg(String name, String str) {
    super(name);
    this.str = str;
  }

  public String reFormat() {
    return str
            .replace("\\", "\\\\")
            .replace("\n", "\\n")
            .replace("\"", "\\\"");
  }

}
