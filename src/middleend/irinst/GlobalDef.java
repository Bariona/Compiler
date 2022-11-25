package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.BoolType;
import middleend.irtype.IRBaseType;
import middleend.irtype.IntType;
import middleend.irtype.StructType;
import middleend.operands.BoolConst;
import middleend.operands.IntConst;
import middleend.operands.ZeroInitPtr;

public class GlobalDef extends IRBaseInst {
  // global variable

  public GlobalDef(String name, Value value, IRBasicBlock parenBlock) {
    super("glob_" + name, value.getType(), parenBlock);
    addOperands(value);
  }

  public GlobalDef(String name, IRBaseType type, IRBasicBlock parenBlock) {
    super("glob_" + name, type, parenBlock);
    if (type instanceof StructType) {
      addOperands(new ZeroInitPtr(type));
    } else if (type instanceof IntType) {
      addOperands(new IntConst(0));
    } else if (type instanceof BoolType) {
      addOperands(new BoolConst("false"));
    } else assert false;
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
