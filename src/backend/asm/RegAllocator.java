package backend.asm;

import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;

public class RegAllocator {
  public ASMModule asm;
  private int sp;

  public RegAllocator(ASMModule asm) {
    this.asm = asm;
  }

  public void doit() {
    for (var func : asm.func) {
      sp = 0;
      runFunction(func);
    }
  }

  public void runFunction(ASMFunction func) {

  }

}
