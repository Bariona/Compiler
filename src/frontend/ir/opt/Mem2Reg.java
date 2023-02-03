package frontend.ir.opt;

import frontend.ir.Value;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.*;
import frontend.ir.operands.IReg;

import java.util.HashMap;
import java.util.LinkedList;

public class Mem2Reg {
  // eliminate alloca and relevant load/store instructions.
  public IRModule module;

  HashMap<Alloca, IReg> allocaMp;
  HashMap<Load, IReg> loadMp;
  HashMap<Value, Integer> modifiedTimes;

  public void runOnModule(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(this::runOnFunction);
  }

  public void runOnFunction(IRFunction func) {
    allocaMp = new HashMap<>();
    loadMp = new HashMap<>();
    modifiedTimes = new HashMap<>();

//    HashMap<Value, Integer> pock = new HashMap<>();
//    HashMap<Load, Integer> modifiedCnt = new HashMap<>();
//    ArrayList<Load> loadCheck = new ArrayList<>();
//    for (var block : func.blockList) {
//      for (var inst : block.instrList) {
//        if (inst instanceof Store store) {
//          Value val = store.storePtr();
//          pock.putIfAbsent(val, 0);
//          pock.putIfAbsent(val, pock.get(val) + 1);
//        }
//        if (inst instanceof Load load && load.loadPtr() instanceof Alloca) {
//          modifiedCnt.put(load, pock.get(load.loadPtr()));
//          loadCheck.add(load);
//        }
//        for (var op : inst.operands)
//      }
//    }
//    loadCheck.forEach(ld -> {
//      boolean check = true;
//      for (var uses : ld.userList) {
//        if ()
//      }
//    });
    for (var block : func.blockList) {
      LinkedList<IRBaseInst> instrList = new LinkedList<>();
      for (int i = 0; i < block.instrList.size(); ++i) {
        var inst = block.instrList.get(i);
        if (inst instanceof Alloca alloca) {
          IReg reg = new IReg(alloca.name, alloca.type.dePointed());
          allocaMp.put(alloca, reg);
          continue;
        } else if (inst instanceof Load load) {
          Value value = load.loadPtr();
          if (value instanceof Alloca addr) {
            boolean check = true;
            int userCnt = 0;
            for (int j = i + 1; j < block.instrList.size(); ++j) {
              IRBaseInst nexInst = block.instrList.get(j);
              if (nexInst instanceof Store store && store.storePtr() == value) {
                check = false;
                break;
              }
              if (inst.userList.contains(nexInst)) {
                ++userCnt;
                if (userCnt == inst.userList.size()) break;
              }
            }
            if (check && userCnt == inst.userList.size()) {
              load.replaceUserValue(allocaMp.get(addr));
            } else {
              IReg reg = new IReg(load.name, addr.type.dePointed());
              loadMp.put(load, reg);
              instrList.add(new Assign(allocaMp.get(addr), reg));
            }
            continue;
          }
        }
        resetOperands(inst);
        instrList.add(inst);
      }
      block.instrList = instrList;
    }
//    for (var block : func.blockList) {
//      LinkedList<IRBaseInst> instrList = new LinkedList<>();
//      for (var inst : block.instrList) {
//        if (inst instanceof Alloca alloca) {
//          IReg reg = new IReg(alloca.name, alloca.type.dePointed());
//          allocaMp.put(alloca, reg);
//          continue;
//        } else if(inst instanceof Load load) {
//          var value = load.loadPtr();
//          if (value instanceof Alloca addr) {
////            IReg reg = new IReg(load.name, addr.type.dePointed());
////            loadMp.put(load, reg);
////            instrList.add(new Assign(allocaMp.get(addr), reg));
//            load.replaceUserValue(allocaMp.get(addr));
//            continue;
//          }
//        }
//        resetOperands(inst);
//        instrList.add(inst);
//      }
//      block.instrList = instrList;
//    }
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
