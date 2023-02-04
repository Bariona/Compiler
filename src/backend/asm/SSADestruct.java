package backend.asm;

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
        if (dstBlock.phiInst.size() == 0) continue;

        // System.out.println("critical path: " + srcBlock.name + " -> " + dstBlock.name);
        IRBlock midBlock = new IRBlock("midBlock", null, 0);
        midBlocks.add(midBlock);
        new Branch(dstBlock, midBlock); // br mid -> dst
        relinkMp.put(midBlock, dstBlock);

        // change dst's phi instructions
        for (var phi : dstBlock.phiInst)
          for (int i = 0; i < phi.operands.size(); i += 2)
            if (phi.getOperand(i + 1) == srcBlock)
              phi.resetOperands(i + 1, midBlock);
      }

      relinkMp.forEach((mid, dst) -> {
        srcBlock.relinkBlock(dst, mid); // change src's terminate instruction
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
      if (block.phiInst.size() == 0) continue;
      for (var phi : block.phiInst)
        for (int i = 0; i < phi.operands.size(); i += 2) {
          var b = (IRBlock) phi.getOperand(i + 1);
          b.pCopy.put(phi, phi.getOperand(i));
        }
      block.phiInst.clear(); // eliminate phi
    }

    for (var block : func.blockList) {
      if (block.pCopy.isEmpty()) continue;
      block.pCopy.forEach((target, value) ->
              block.addInstBack(new Assign(value, target)));
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
