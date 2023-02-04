package frontend.ir.opt;

import frontend.ir.DominatorTree;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.*;
import frontend.ir.operands.NullConst;

import java.util.*;

public class Mem2Reg {
  // eliminate alloca and relevant load/store instructions.
  // ref: SSA Book chapter 2&3
  public IRModule module;

  IRFunction curFunction;
  DominatorTree domTree;
  ArrayList<Alloca> allocas;

  HashMap<String, Stack<Value>> nameStack;
  HashMap<Phi, String> phiAllocaName; // find newly created phi corresponding to Alloca

  private void init() {
    nameStack = new HashMap<>();
    phiAllocaName = new HashMap<>();
    allocas = new ArrayList<>();
    domTree = new DominatorTree();
    domTree.runOnFunction(curFunction);
    allocaCollect();
  }

  private void allocaCollect() {
    curFunction.blockList.forEach(b -> b.instrList.forEach(inst -> {
      if (inst instanceof Alloca a) allocas.add(a);
    }));
  }

  private ArrayList<Store> allocaDefs(Alloca inst) {
    ArrayList<Store> ret = new ArrayList<>();
    for (var user : inst.userList) {
      if (user instanceof Store store && store.storePtr() == inst)
        ret.add(store);
    }
    return ret;
  }

  public void phiInset() {
    for (Alloca alloca : allocas) {
      LinkedList<IRBlock> workList = new LinkedList<>();
      HashSet<IRBlock> visited = new HashSet<>(); // phi has been inserted
      ArrayList<Store> defs = allocaDefs(alloca);

      defs.forEach(def -> workList.add(def.parenBlock));

      while (!workList.isEmpty()) {
        IRBlock block = workList.poll();
        ArrayList<IRBlock> frontiers = domTree.frontier.get(block);
        for (var frontier : frontiers) {
          if (visited.contains(frontier)) continue;
          Phi phi = new Phi(alloca.type.dePointed(), null);
          phiAllocaName.put(phi, alloca.name);
          frontier.addPhi(phi);
          visited.add(frontier);
          if (!workList.contains(frontier))
            workList.add(frontier);
        }
      }
    }
  }

  private Value getReplace(String addr) {
    if (!nameStack.containsKey(addr) || nameStack.get(addr).isEmpty())
      return new NullConst();
    return nameStack.get(addr).lastElement(); // TODO: top()
  }

  private void updateReplace(String addr, Value value) {
    if (!nameStack.containsKey(addr))
      nameStack.put(addr, new Stack<>());
    nameStack.get(addr).push(value);
  }

  public void variableRenaming(IRBlock block) {
    ArrayList<String> rollbackRecord = new ArrayList<>();

    for (var phi : block.phiInst) {
      if (!phiAllocaName.containsKey(phi)) continue;
      updateReplace(phiAllocaName.get(phi), phi);
      rollbackRecord.add(phiAllocaName.get(phi));
    }

    var it = block.instrList.iterator();
    while (it.hasNext()) {
      IRBaseInst inst = it.next();
      if (inst instanceof Alloca) {
        it.remove();
      } else if (inst instanceof Load load) {
        if (!(load.loadPtr() instanceof Alloca addr)) continue;
        String addrName = addr.name;
        Value replace = getReplace(addrName);
        if (replace instanceof NullConst) {
          // System.out.println("load uninitialized \"" + addrName + "\" at " + load.toString());
        }
        inst.replaceUserValue(replace);
        it.remove();
      } else if (inst instanceof Store store) {
        if (!(store.storePtr() instanceof Alloca addr)) continue;
        String addrName = addr.name;
        updateReplace(addrName, store.storeValue());
        rollbackRecord.add(addrName);
        it.remove();
      }
    }

    for (IRBlock nextBlock : block.next)
      for (var nextPhi : nextBlock.phiInst)
        if (phiAllocaName.containsKey(nextPhi))
          nextPhi.addOperands(getReplace(phiAllocaName.get(nextPhi)), block);

    domTree.domChild.get(block).forEach(son -> variableRenaming(son));

    rollbackRecord.forEach(allocaAddr -> nameStack.get(allocaAddr).pop());
  }

  public void runOnFunction(IRFunction func) {
    curFunction = func;
    init();
    phiInset();
    variableRenaming(curFunction.entryBlock);
    curFunction = null;
  }

  public void runOnModule(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(this::runOnFunction);
  }


//  HashMap<Alloca, IReg> allocaMp;
//  HashMap<Load, IReg> loadMp;
//  HashMap<Value, Integer> modifiedTimes;
//
//  public void runOnFunction(IRFunction func) {
//    allocaMp = new HashMap<>();
//    loadMp = new HashMap<>();
//    modifiedTimes = new HashMap<>();
//
//    for (var block : func.blockList) {
//      LinkedList<IRBaseInst> instrList = new LinkedList<>();
//      for (int i = 0; i < block.instrList.size(); ++i) {
//        var inst = block.instrList.get(i);
//        if (inst instanceof Alloca alloca) {
//          IReg reg = new IReg(alloca.name, alloca.type.dePointed());
//          allocaMp.put(alloca, reg);
//          continue;
//        } else if (inst instanceof Load load) {
//          Value value = load.loadPtr();
//          if (value instanceof Alloca addr) {
//            boolean check = true;
//            int userCnt = 0;
//            for (int j = i + 1; j < block.instrList.size(); ++j) {
//              IRBaseInst nexInst = block.instrList.get(j);
//              if (nexInst instanceof Store store && store.storePtr() == value) {
//                check = false;
//                break;
//              }
//              if (inst.userList.contains(nexInst)) {
//                ++userCnt;
//                if (userCnt == inst.userList.size()) break;
//              }
//            }
//            if (check && userCnt == inst.userList.size()) {
//              load.replaceUserValue(allocaMp.get(addr));
//            } else {
//              IReg reg = new IReg(load.name, addr.type.dePointed());
//              loadMp.put(load, reg);
//              instrList.add(new Assign(allocaMp.get(addr), reg));
//            }
//            continue;
//          }
//        }
//        else if (inst instanceof Store store) {
//          Value value = store.storePtr();
//          if (value instanceof Alloca addr) {
//            instrList.add(new Assign(store.getOperand(0), allocaMp.get(addr)));
//            continue;
//          }
//        }
//        resetOperands(inst);
//        instrList.add(inst);
//      }
//      block.instrList = instrList;
//    }
//  }
//
//  public void resetOperands(IRBaseInst inst) {
//    for (int i = 0; i < inst.operands.size(); ++i) {
//      var op = inst.operands.get(i);
//      if (op instanceof Alloca alloca) {
//        inst.resetOperands(i, allocaMp.get(alloca));
//      } else if (op instanceof Load load) {
//        if (!loadMp.containsKey(load)) continue;
//        inst.resetOperands(i, loadMp.get(load));
//      }
//    }
//  }

}
