package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.irtype.PtrType;
import frontend.ir.irtype.VoidType;
import frontend.ir.operands.NullConst;
import utility.Debugger;

import java.util.ArrayList;

public class Store extends IRBaseInst {
  // e.g.  store i32 %add1, i32* %A_x

  public Store(Value value, Value ptr, IRBlock parenBlock) {
    super("store", new VoidType(), parenBlock);

    if (ptr.getType() instanceof PtrType ptrType) {
      if (value instanceof NullConst) {
        value = new NullConst(ptrType.target);
      } else if (!value.getType().match(ptrType.target)) {
         Debugger.error("target type: " + ptrType.toString() + " ← store value type: " + value.getType().toString());
      }
    } else {
      Debugger.error(ptr.getTypeAndName());
    }

    addOperands(value);
    addOperands(ptr);
  }

  public Value storeValue() {
    return getOperand(0);
  }

  public Value storePtr() {
    return getOperand(1);
  }

  public void resetPtr(Value value) {
    resetOperands(1, value);
  }

  @Override
  public ArrayList<Value> getUses() {
    return operands;
  }

  @Override
  public String toString() {
    return "store " + getOperand(0).getTypeAndName() + ", " + getOperand(1).getTypeAndName();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}
