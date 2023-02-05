package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.VoidType;

import java.util.ArrayList;

public class Ret extends IRBaseInst {

  public Ret(IRBlock parenBlock) { // ret void
    super("ret", new VoidType(), parenBlock);
  }

  public Ret(Value value, IRBlock parenBlock) { // ret <type> <value>
    super("ret", value.getType(), parenBlock);
    addOperands(value);
  }

  @Override
  public ArrayList<Value> getUses() {
    return operands;
  }

  @Override
  public String toString() {
    if (this.operands.size() > 0)
      return "ret " + getOperand(0).getTypeAndName();
    return "ret void";
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
