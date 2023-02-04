package frontend.ir.opt;

import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;

public class LICM {
  // LOOP-INVARIANT COMPUTATIONS <Chapter 18.2 Tiger Book>
  IRModule module = null;
  IRFunction curFunction = null;

  public void runOnModule(IRModule module) {
    this.module = module;
  }
}
