package frontend.ir.hierarchy;

import frontend.ir.Value;
import frontend.ir.instruction.Alloca;
import frontend.ir.instruction.Branch;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.instruction.Phi;
import frontend.ir.irtype.LabelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IRBlock extends Value {
  public Phi phiInst;
  public LinkedList<IRBaseInst> instrList = new LinkedList<>();
  public HashMap<Value, Value> copyMp = new HashMap<>();
  public ArrayList<IRBlock> prev = new ArrayList<>(), next = new ArrayList<>();
  IRFunction parenFunc;
  IRBaseInst tailInst = null;

  public IRBlock(String name, IRFunction func) {
    super(name, new LabelType());
    parenFunc = func;
    func.addBlock(this);
  }

  public void addEdge(IRBlock block) {
    block.prev.add(this);
    this.next.add(block);
  }

  public void insert2CFG() {
    Branch inst = this.getTerminator();
    if (inst.isJump()) {
      addEdge(inst.dstBlock());
    } else {
      addEdge(inst.ifTrueBlock());
      addEdge(inst.ifFalseBlock());
    }
  }

  public Branch getTerminator() {
    return (Branch) tailInst;
  }

  public void addInst(IRBaseInst instr) {
    if (instr instanceof Alloca) { // add alloca at the front of "entry block"
      instrList.addFirst(instr);
    } else {
      if (instr instanceof Phi phi) {
        phiInst = phi;
      } else instrList.addLast(instr);
    }
    tailInst = instr;
  }

  public void addInstBack(IRBaseInst instr) {
    instrList.add(instrList.size() - 1, instr);
  }

  public void relinkBlock(IRBlock prev, IRBlock nex) {
    this.next.remove(prev);
    this.next.add(nex);
    for (var inst : instrList) {
      if (inst instanceof Branch) {
        for (int i = 0; i < inst.operands.size(); ++i) {
          var block = inst.operands.get(i);
          if (block instanceof IRBlock && block == prev)
            inst.resetOperands(i, nex);
        }
      }
    }
  }

}
