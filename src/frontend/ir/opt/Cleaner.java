package frontend.ir.opt;

import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.Assign;
import frontend.ir.instruction.Branch;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.operands.IReg;

import java.util.ArrayList;

public class Cleaner {
  IRModule module;
  IRFunction curFunction;

  private void doBlock(IRBlock block) {
    curFunction.blockList.add(block);
    block.next.forEach(nex -> {
      if (!curFunction.blockList.contains(nex))
        doBlock(nex);
    });
  }

  private void blockCollect() {
    IRBlock entry = curFunction.getEntryBlock();
    curFunction.blockList = new ArrayList<>();
    doBlock(entry);
  }

  private void deadBlockEle() {
    curFunction.blockList.forEach(block -> {
      ArrayList<IRBlock> rmBlocks = new ArrayList<>();
      for (var pre : block.prev) {
        if (!curFunction.blockList.contains(pre))
          rmBlocks.add(pre);
      }
      for (var remove : rmBlocks)
        block.prev.remove(remove);
    });
  }

  private void blockMerge() {
    for (IRBlock block : curFunction.blockList) {
      if (block.prev.size() == 1 && block.prev.get(0).getTerminator() instanceof Branch br) {
        if (!br.isJump()) continue;
        // [pre -> block] -> ...
        IRBlock pre = block.prev.get(0);
        pre.next = block.next;
        pre.removeTerminator();

        if (block == curFunction.exitBlock)
          curFunction.exitBlock = pre;

        for (var inst : block.instrList) {
          pre.addInst(inst);
          inst.parenBlock = pre;
        }
        for (IRBlock b : block.next) {
          b.prev.remove(block);
          b.prev.add(pre);
          for (var phi : b.phiInst)
            phi.changeBlock(block, pre);
        }
      }
    }
    blockCollect();
  }

  public void doEachInst() {
    for (IRBlock block : curFunction.blockList) {
      for (var phi : block.phiInst) {
        for (int i = 1; i < phi.operands.size(); i += 2) {
          ArrayList<Integer> rmIdx = new ArrayList<>();
          if (!block.prev.contains((IRBlock) phi.getOperand(i)))
            rmIdx.add(i);
          for (int idx : rmIdx) {
            phi.operands.remove(idx);
            phi.operands.remove(idx - 1);
          }
        }
        if (phi.operands.size() == 2) {
          // not tested yet
          assert false;
          phi.replaceUserValue(phi.getOperand(0));
          block.phiInst.remove(phi);
        }
      }
    }
  }

  public void runOnFunction(IRFunction func) {
    curFunction = func;
    blockCollect();
    deadBlockEle();
    doEachInst();
    blockMerge();
//    System.out.println(curFunction.name + " " + curFunction.blockList.size());
//    for (var block : curFunction.blockList)
//      System.out.print(block.name + " ");
//    System.out.println();
    curFunction = null;
  }

  public void runOnModule(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(this::runOnFunction);
  }

}
