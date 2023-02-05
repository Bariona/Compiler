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

}
