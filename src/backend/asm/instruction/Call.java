package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;

public class Call extends ASMBaseInst {
  ASMFunction func;

  public Call(ASMFunction func, ASMBlock parenBlock) {
    super(parenBlock);
    this.func = func;
  }

  @Override
  public String toString() {
    return "call " + func.name;
  }

  @Override
  public void accept(InstVisitor visitor) {
    visitor.visit(this);
  }

}
