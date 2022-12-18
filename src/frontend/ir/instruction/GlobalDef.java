package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.User;
import frontend.ir.irtype.*;
import frontend.ir.operands.BoolConst;
import frontend.ir.operands.IntConst;
import frontend.ir.operands.ZeroInitPtr;

public class GlobalDef extends User {
  // global variable

  public GlobalDef(String name, IRBaseType type) {
    super("glob_" + name, new PtrType(type));

    if (type instanceof IntType) {
      addOperands(new IntConst(0));
    } else if (type instanceof BoolType) {
      addOperands(new BoolConst("false"));
    } else {
      // array and struct type
      addOperands(new ZeroInitPtr(type));
    }
  }

  @Override
  public String getName() {
    return "@" + this.name;
  }

  @Override
  public String toString() {
    return getName() + " = global " + getOperand(0).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

}
