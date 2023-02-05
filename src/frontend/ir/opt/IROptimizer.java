package frontend.ir.opt;

import frontend.ir.hierarchy.IRModule;

public class IROptimizer {
  IRModule module;

  public void runOnModule(IRModule module) {
    this.module = module;
    new CFGBuilder().runOnModule(module);

    new CallGraph().runOnModule(module);
    new Glob2Loc().runOnModule(module);
    new Cleaner().runOnModule(module);
    new Mem2Reg().runOnModule(module);

    new Cleaner().runOnModule(module);
    new LICM().runOnModule(module);
    new Cleaner().runOnModule(module);
  }

}
