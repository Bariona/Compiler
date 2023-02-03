package frontend.ir.opt;

import frontend.ir.Value;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.*;

import java.util.HashMap;
import java.util.HashSet;

public class Glob2Loc {
  // accelerate the code by copy global variables as local var
  public IRModule module;
  public static final int UsageThreshold = 1;

  HashMap<GlobalDef, Integer> refMp = new HashMap<>();
  HashSet<GlobalDef> globConst = new HashSet<>(), localized = new HashSet<>();

  private boolean isConst(GlobalDef def) {
    // only those defs that are not modified during execution are constants.
    if (def.initValue == null) return false;
    for (var inst : def.userList) {
      if (inst instanceof Store && !((Store) inst).parenBlock.parenFunc.isGlobInit())
        return false;
    }
    return true;
  }

  private void checkOnFunction(IRFunction func) {
    for (var block : func.blockList) {
      for (var inst : block.instrList) {
        if (!(inst instanceof Load || inst instanceof Store)) continue;
        Value glob = inst.getOperand(0);
        if (!(glob instanceof GlobalDef def)) continue;
        refMp.put(def, (refMp.get(def) != null) ? refMp.get(def) + 1 : 1);
      }
    }
    for (var glob : refMp.keySet()) {
      if (isConst(glob)) {
        globConst.add(glob);
        continue;
      }
      if (refMp.get(glob) >= UsageThreshold) {
        boolean check = true;
        for (var callee : func.node.callee) {
          // e.g. glob_a = 1
          // f: glob_a += 1
          //    call(g)
          // g: glob_a += 1
          if (callee.node.globUses.contains(glob)) {
            check = false;
            break;
          }
        }
        if (check) {
//          System.out.println("can localize " + glob.toString() + " in " + func.name);
          localized.add(glob);
        }
      }
    }
  }

  public void runOnFunction(IRFunction func) {
    if (func.node.cyclic || func.isGlobInit()) return;
    globConst = new HashSet<>();
    localized = new HashSet<>();
    checkOnFunction(func);

    for (var globDef : localized) {
      IRBaseInst locDef = new Alloca(globDef.name.replace("glob_", "loc_"), globDef.getType().dePointed(), null),
              globLoad = new Load(globDef, null),
              locStore = new Store(globLoad, locDef, null),
              locLoad = null, globStore = null;
      func.getEntryBlock().addInstFront(locStore);
      func.getEntryBlock().addInstFront(globLoad);
      func.getEntryBlock().addInstFront(locDef);

      if (func.node.globDefs.contains(globDef)) {
        locLoad = new Load(locDef, null);
        globStore = new Store(locLoad, globDef, null);
        func.getExitBlock().addInstFront(globStore);
        func.getExitBlock().addInstFront(locLoad);
      }

      for (var block : func.blockList)
        for (var inst : block.instrList) {
          if (inst instanceof Load load && load.loadPtr() == globDef) {
            if (load != locLoad && load != globLoad)
              load.resetPtr(locDef);
          }
          if (inst instanceof Store store && store.storePtr() == globDef) {
            if (store != locStore && store != globStore)
              store.resetPtr(locDef);
          }
        }
    }

    for (var gbConst : globConst) {
      for (var use : gbConst.userList) {
        if (use instanceof Load ld)
          ld.replaceUserValue(gbConst.initValue);
        ((IRBaseInst) use).parenBlock.instrList.remove(use);
      }
    }
  }

  public void runOnModule(IRModule module) {
    this.module = module;
    for (var func : module.irFuncList)
      runOnFunction(func);
  }
}
