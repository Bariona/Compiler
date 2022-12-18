package frontend.ir.operands;

import frontend.ir.irtype.IRBaseType;
import frontend.ir.irtype.PtrType;
import frontend.ir.irtype.VoidType;

public class NullConst extends IRBaseConst {

  public NullConst() {
    super("Null", new PtrType(new VoidType()));
  }

  public NullConst(IRBaseType type) {
    super("Null", type);
  }

  @Override
  public String getName() {
    return "null";
  }
}
