package middleend.hierarchy;

import middleend.IRVisitor;
import middleend.User;
import middleend.Value;
import middleend.irinst.Alloca;
import middleend.irinst.Load;
import middleend.irinst.Ret;
import middleend.irtype.FuncType;
import middleend.irtype.VoidType;

import java.util.ArrayList;

public class IRFunction extends User {
  public String description;
  public ArrayList<IRBasicBlock> blockList = new ArrayList<>();
  public FuncType funcType; // retType and paraType e.g. %struct = type {i32, i1}
  public Value retValPtr;

  public IRFunction(String name, FuncType funcType, Value... paraList) {
    super(name, funcType.retType);
    this.funcType = funcType;
    for (Value para : paraList)
      addOperands(para);
    initialized();
  }

  private void initialized() {
    new IRBasicBlock("entry_of_" + name, this);
    new IRBasicBlock("exit_of_" + name, this);

    if (getType() instanceof VoidType) {
      new Ret(getExitBlock());
    } else {
      retValPtr = new Alloca(name + ".ret", getType(), getEntryBlock());
      Value ret = new Load(retValPtr, getExitBlock());
      new Ret(ret, getExitBlock());
    }
  }

  public IRBasicBlock getEntryBlock() {
    return blockList.get(0);
  }

  public IRBasicBlock getExitBlock() {
    return blockList.get(1);
  }

  public void addBlock(IRBasicBlock block) {
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
