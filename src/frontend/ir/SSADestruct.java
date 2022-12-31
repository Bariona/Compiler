package frontend.ir;

import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.Assign;
import frontend.ir.instruction.Branch;

import java.util.ArrayList;
import java.util.HashMap;

public class SSADestruct {

  private void criticalPath(IRFunction func) {
    // split critical path
    ArrayList<IRBlock> midBlocks = new ArrayList<>();
    for (var srcBlock : func.blockList) {
      if (srcBlock.next.size() <= 1)
        continue;

      HashMap<IRBlock, IRBlock> relinkMp = new HashMap<>();
      // src -> mid -> dst
      for (var dstBlock : srcBlock.next) {
        var phi = dstBlock.phiInst;
        if (phi == null) continue;

        System.out.println("critical path: " + srcBlock.name + " -> " + dstBlock.name);
        IRBlock midBlock = new IRBlock("midBlock", null);
        midBlocks.add(midBlock);
        new Branch(dstBlock, midBlock);
        relinkMp.put(midBlock, dstBlock);

        // change dst's phi(1st) instruction
        for (int i = 0; i < phi.operands.size(); i += 2)
          if (phi.getOperand(i + 1) == dstBlock)
            phi.resetOperands(i + 1, midBlock);
      }

      relinkMp.forEach((mid, dst) -> {
        srcBlock.relinkBlock(mid, dst); // change src's terminate instruction
        srcBlock.next.remove(dst);
        srcBlock.next.add(mid);
        mid.insert2CFG();
        dst.prev.remove(srcBlock);
        dst.prev.add(mid);
      });
      relinkMp.clear();
    }
    func.blockList.addAll(midBlocks);
  }

  public void killPhi(IRFunction func) {
    for (var block : func.blockList) {
      // parallel copy
      var phi = block.phiInst;
      if (phi == null) continue;
      for (int i = 0; i < phi.operands.size(); i += 2) {
        var b = (IRBlock) phi.getOperand(i + 1);
        b.pCopy.put(phi, phi.getOperand(i));
      }
      block.phiInst = null; // eliminate phi
    }

    for (var block : func.blockList) {
      if (block.pCopy.isEmpty()) continue;
      block.pCopy.forEach((target, value) ->
              block.addInstBack(new Assign(target, value)));
      System.out.println(block.name + " " + block.instrList.getLast().toString());
    }
  }

  private void runOnFunc(IRFunction func) {
    criticalPath(func);
    killPhi(func);
  }

  public void runOnIR(IRModule ir) {
    ir.irFuncList.forEach(this::runOnFunc);
  }
}
