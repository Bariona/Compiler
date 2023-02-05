package frontend.ir.opt;

import frontend.ir.DominatorTree;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.*;
import frontend.ir.irtype.VoidType;

import java.util.*;

public class LICM {
  // Loop-invariant computations <Chapter 18.2 Tiger Book>
  // conservative version without alias analysis

  IRModule module = null;
  IRFunction curFunction = null;
  DominatorTree domTree;
  HashMap<Value, IRBaseInst> defs;

  private boolean simplyCheck(IRBaseInst inst, HashSet<IRBlock> loopBlocks) {
    ArrayList<Value> uses = inst.getUses();
    for (Value op : uses) {
      if (defs.containsKey(op) && loopBlocks.contains(defs.get(op).parenBlock))
        return false;
    }
    return true;
  }

  private Value checkHasDef(IRBaseInst inst) {
    assert !(inst instanceof Alloca);
    if (inst instanceof Assign x) return x.rd;
    if (inst instanceof Binary ||
            inst instanceof BitCast ||
            inst instanceof GetElePtr ||
            inst instanceof Load ||
            inst instanceof Phi)
      return inst;
    if (inst instanceof Call call && !(call.getType() instanceof VoidType))
      return inst;
    return null;
  }

  private void collectDefs() {
    for (IRBlock block : curFunction.blockList) {
      for (var phi : block.phiInst)
        defs.put(phi, phi);
      for (var inst : block.instrList) {
        var def = checkHasDef(inst);
        if (def != null) defs.put(def, inst);
      }
    }
  }

  private void runOnLoops() {
    for (var block : domTree.rNodes) {
      var inst = block.getTerminator();
      if (!(inst instanceof Branch br && br.isJump())) continue;
      IRBlock head = br.dstBlock();

      ArrayList<IRBlock> tails = new ArrayList<>();
      HashSet<IRBlock> sub = domTree.domSubTree.get(block);

      for (IRBlock v : sub) {
        if (v.getTerminator() instanceof Branch brc
                && brc.isJump() && brc.dstBlock() == head)
          tails.add(v);
      }
      if (tails.isEmpty()) continue;
//      System.out.println(block.name);
//      System.out.println("head: " + head.name);
//      System.out.print("tails: ");
//      for (var b : tails) System.out.print(b.name + " ");
//      System.out.println();


      HashSet<IRBlock> loopBlocks = new HashSet<>(tails);
      loopBlocks.add(head);
      LinkedList<IRBlock> workList = new LinkedList<>(tails);
      while (!workList.isEmpty()) {
        IRBlock b = workList.poll();
        b.prev.forEach(x -> {
          if (!loopBlocks.contains(x)) {
            loopBlocks.add(x);
            workList.add(x);
          }
        });
      }

      boolean check = true;
      for (var b : loopBlocks) {
        if (!sub.contains(b)) {
          check = false;
          break;
        }
      }
      if (!check) continue;

      for (IRBlock b : loopBlocks) {
        ArrayList<IRBaseInst> rmInst = new ArrayList<>();
        for (var instr : b.instrList)
          if ((instr instanceof Binary || instr instanceof BitCast || instr instanceof GetElePtr)
                  && simplyCheck(instr, loopBlocks)) {
          block.addInstBack(instr);
          rmInst.add(instr);
        }
        for (var instr : rmInst)
          b.instrList.remove(instr);
      }
    }
  }

  public void runOnFunction(IRFunction func) {
    curFunction = func;
    domTree = new DominatorTree();
    domTree.runOnFunction(func);
    defs = new HashMap<>();
    collectDefs();
    runOnLoops();
    curFunction = null;
  }

  public void runOnModule(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(this::runOnFunction);
  }

}
