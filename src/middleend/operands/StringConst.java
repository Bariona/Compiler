package middleend.operands;

import middleend.irtype.ArrayType;
import middleend.irtype.IntType;
import middleend.irtype.PtrType;

public class StringConst extends IRBaseConst {

  private final String content;

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
    return getName() + " = private unnamed_addr constant " + ((PtrType) getType()).target.toString() +  " c\"" + content + "\\00\", align 1";
  }
}
