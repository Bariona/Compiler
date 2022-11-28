package middleend.irinst;

import middleend.IRVisitor;
import middleend.Value;
import middleend.hierarchy.IRBasicBlock;
import middleend.irtype.BoolType;
import middleend.irtype.VoidType;

public class Branch extends IRBaseInst {
  // br i1 <cond>, label <iftrue>, label <iffalse>

  public Branch(Value cond, IRBasicBlock ifTrue, IRBasicBlock ifFalse, IRBasicBlock block) {
    super("br", new VoidType(), block);
    assert cond.getType().match(new BoolType());

    addOperands(cond);
    addOperands(ifTrue);
    addOperands(ifFalse);
  }

  // br label <dest>
  public Branch(IRBasicBlock dst, IRBasicBlock block) {
    super("br", new VoidType(), block);
    addOperands(dst);
  }

  @Override
  public String toString() {
    if (this.operands.size() > 1)
      return "br " + getOperand(0).getTypeAndName() + ", " +
              getOperand(1).getTypeAndName() + ", " + getOperand(2).getTypeAndName();
    return "br " + getOperand(0).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
