package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.VoidType;

public class Ret extends IRBaseInst {

  public Ret(IRBasicBlock block) { // ret void
    super("ret", new VoidType(), block);
  }

  public Ret(Value value, IRBasicBlock block) { // ret <type> <value>
    super("ret", value.getType(), block);
    this.operands.add(value);
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
