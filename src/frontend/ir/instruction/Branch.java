package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.BoolType;
import frontend.ir.irtype.VoidType;

public class Branch extends IRBaseInst {
  // br i1 <cond>, label <iftrue>, label <iffalse>

  public Branch(Value cond, IRBlock ifTrue, IRBlock ifFalse, IRBlock block) {
    super("br", new VoidType(), block);
    assert cond.getType().match(new BoolType());

    addOperands(cond);
    addOperands(ifTrue);
    addOperands(ifFalse);
  }

  // br label <dest>
  public Branch(IRBlock dst, IRBlock block) {
    super("br", new VoidType(), block);
    addOperands(dst);
  }

  public boolean isJump() {
    return this.operands.size() == 1;
  }

  public IRBlock dstBlock() { return (IRBlock) getOperand(0); }
  public IRBlock ifTrueBlock() { return (IRBlock) getOperand(1); }
  public IRBlock ifFalseBlock() { return (IRBlock) getOperand(2); }

  @Override
  public String toString() {
    if (isJump())
      return "br " + getOperand(0).getTypeAndName() + ", " +
              getOperand(1).getTypeAndName() + ", " + getOperand(2).getTypeAndName();
    return "br " + getOperand(0).getTypeAndName();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
