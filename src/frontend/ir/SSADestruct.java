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
      if (srcBlock.phiInst == null || srcBlock.next.size() <= 1)
        continue;
      HashMap<IRBlock, IRBlock> relinkMp = new HashMap<>();
      // src -> mid -> dst
      for (var dstBlock : srcBlock.next) {
        IRBlock midBlock = new IRBlock("midBlock", null);
        midBlocks.add(midBlock);
        new Branch(dstBlock, midBlock);

        // change dst's phi(1st) instruction
        var phi = dstBlock.phiInst;
        for (int i = 0; i < phi.operands.size(); i += 2)
          if (phi.getOperand(i + 1) == dstBlock) {
            srcBlock.copyMp.put(phi, phi.getOperand(i));
            phi.resetOperands(i + 1, midBlock);
          }
        relinkMp.put(midBlock, dstBlock);
      }
      // change src's terminate instruction
      relinkMp.forEach((mid, dst) -> {
        srcBlock.relinkBlock(mid, dst);
        srcBlock.next.remove(dst);
        srcBlock.next.add(mid);
        mid.insert2CFG();
        dst.prev.remove(srcBlock);
        dst.prev.add(mid);
      });
    }
    func.blockList.addAll(midBlocks);
  }

  public void killPhi(IRFunction func) {
    for (var block : func.blockList) {
      block.copyMp.forEach((target, value) ->
              block.addInstBack(new Assign(target, value, block)));
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
