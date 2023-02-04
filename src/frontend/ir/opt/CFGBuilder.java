package frontend.ir.opt;

import frontend.ir.hierarchy.IRModule;

public class CFGBuilder {

  public void runOnModule(IRModule module) {
    for (var func : module.irFuncList) {
      for (var block : func.blockList)
        block.insert2CFG();
    }
  }

}
