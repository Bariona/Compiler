package frontend.ir;

import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.Alloca;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.instruction.Load;
import frontend.ir.instruction.Store;
import frontend.ir.operands.IReg;

import java.util.HashMap;
import java.util.LinkedList;

public class Mem2Reg {
  public IRModule module;

  HashMap<Alloca, IReg> allocaMp;
  HashMap<Load, IReg> loadMp;

  public Mem2Reg(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(this::runOnFunction);
  }

  public void runOnFunction(IRFunction func) {
    allocaMp = new HashMap<>();
    loadMp = new HashMap<>();
    for (var block : func.blockList) {
      LinkedList<IRBaseInst> instrList = new LinkedList<>();
      for (var inst : block.instrList) {
        if (inst instanceof Alloca alloca) {
          IReg reg = new IReg(alloca.name, alloca.type.dePointed());
          allocaMp.put(alloca, reg);
          continue;
        } else if(inst instanceof Load load) {
          var value = load.getOperand(0);
          if (value instanceof Alloca addr) {
            IReg reg = new IReg(load.name, addr.type.dePointed());
            loadMp.put(load, reg);
            instrList.add(new Store(allocaMp.get(addr), reg, null));
            continue;
          }
        }
        resetOperands(inst);
        instrList.add(inst);
      }
      block.instrList = instrList;
    }
  }

  public void resetOperands(IRBaseInst inst) {
    for (int i = 0; i < inst.operands.size(); ++i) {
      var op = inst.operands.get(i);
      if (op instanceof Alloca alloca) {
        inst.resetOperands(i, allocaMp.get(alloca));
      } else if (op instanceof Load load) {
        if (!loadMp.containsKey(load)) continue;
        inst.resetOperands(i, loadMp.get(load));
      }
    }
  }
}
