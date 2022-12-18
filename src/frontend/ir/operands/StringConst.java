package frontend.ir.operands;

import frontend.ir.irtype.ArrayType;
import frontend.ir.irtype.IntType;
import frontend.ir.irtype.PtrType;

public class StringConst extends IRBaseConst {

  public final String content;

  public StringConst(String content) {
    super(".str", new PtrType(new ArrayType(content.length() + 1, new IntType(8))));
    this.content = content;
  }

  @Override
  public String getName() {
    return "@" + this.name;
  }

  @Override
  public String toString() {
    return getName() + " = private unnamed_addr constant " + ((PtrType) getType()).target.toString() +  " c\"" + replace() + "\\00\", align 1";
  }

  private String replace() {
    return content
            .replace("\\", "\\\\")
            .replace("\n", "\\0A")
            .replace("\"", "\\22");
  }
}
