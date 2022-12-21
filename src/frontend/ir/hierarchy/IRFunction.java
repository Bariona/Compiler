package frontend.ir.hierarchy;

import frontend.ir.IRVisitor;
import frontend.ir.User;
import frontend.ir.Value;
import frontend.ir.instruction.Alloca;
import frontend.ir.instruction.Load;
import frontend.ir.instruction.Ret;
import frontend.ir.irtype.FuncType;
import frontend.ir.irtype.VoidType;

import java.util.ArrayList;

public class IRFunction extends User {
  public String description = null;
  public boolean isMethod = false;
  public FuncType funcType; // retType and paraType e.g. %struct = type {i32, i1}
  public Value retValPtr;
  public ArrayList<IRBlock> blockList = new ArrayList<>();

  public IRFunction(String name, FuncType funcType, Value... paraList) {
    super(name, funcType.retType);
    this.funcType = funcType;
    for (Value para : paraList)
      addOperands(para);
    initialized();
  }

  private void initialized() {
    new IRBlock("entry_of_" + name, this);
    new IRBlock("exit_of_" + name, this);

    if (getType() instanceof VoidType) {
      new Ret(getExitBlock());
    } else {
      retValPtr = new Alloca(name + ".ret", getType(), getEntryBlock());
      Value ret = new Load(retValPtr, getExitBlock());
      new Ret(ret, getExitBlock());
    }
  }

  public IRBlock getEntryBlock() {
    return blockList.get(0);
  }

  public IRBlock getExitBlock() {
    return blockList.get(1);
  }

  public void addBlock(IRBlock block) {
    blockList.add(block);
  }

  public void addParameters(Value para) {
    addOperands(para);
    funcType.addParaType(para.getType());
  }

  public String toStr() { // without "define"
    StringBuilder ret = new StringBuilder(this.getType().toString() + " " + this.getName() + "(");
    for (int i = 0; i < operands.size(); ++i) {
      ret.append(getOperand(i).getTypeAndName());
      if (i < operands.size() - 1)
        ret.append(", ");
    }
    ret.append(")");
    return ret.toString();
  }

  @Override
  public String getName() {
    return "@" + this.name;
  }

  @Override
  public String toString() {
    return "define " + this.toStr();
  }

  @Override
  protected void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

}
