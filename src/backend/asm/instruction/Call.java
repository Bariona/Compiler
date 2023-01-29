package backend.asm.instruction;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.operand.Register;

import java.util.HashSet;

public class Call extends ASMBaseInst {
  ASMFunction func;
  ASMModule asm;

  public Call(ASMFunction func, ASMModule asm, ASMBlock parenBlock) {
    super(parenBlock);
    this.func = func;
    this.asm = asm;
  }

  @Override
  public HashSet<Register> getUses() {
    HashSet<Register> ret = new HashSet<>();
    for (int i = 0; i < Integer.min(8, func.args.size()); ++i)
      ret.add(asm.a(i));
    return ret;
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
