package frontend.ir.hierarchy;

import frontend.ir.Value;
import frontend.ir.instruction.*;
import frontend.ir.irtype.LabelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IRBlock extends Value {
  public int loopDepth;

  public ArrayList<Phi> phiInst = new ArrayList<>();
  public LinkedList<IRBaseInst> instrList = new LinkedList<>();

  public HashMap<Value, Value> pCopy = new HashMap<>();
  public ArrayList<IRBlock> prev = new ArrayList<>(), next = new ArrayList<>();

  public IRFunction parenFunc;
  private boolean terminated = false;
  private IRBaseInst tailInst = null;

  public IRBlock(String name, IRFunction func, int loopDepth) {
    super(name, new LabelType());
    parenFunc = func;
    this.loopDepth = loopDepth;
    if (func != null) // used for phi elimination
      func.addBlock(this);
  }

  public void addEdge(IRBlock block) {
    block.prev.add(this);
    this.next.add(block);
  }

  public void insert2CFG() {
    IRBaseInst terminator = this.getTerminator();
    if (terminator instanceof Branch inst) {
      if (inst.isJump()) {
        addEdge(inst.dstBlock());
      } else {
        addEdge(inst.ifTrueBlock());
        addEdge(inst.ifFalseBlock());
      }
    }
  }

  public IRBaseInst getTerminator() {
    return tailInst;
  }

  public void removeTerminator() {
    if (!terminated) return;
    IRBaseInst inst = instrList.get(instrList.size() - 1);
    if (inst instanceof Branch br) {
      if (br.isJump()) {
        next.remove(br.dstBlock());
        br.dstBlock().prev.remove(this);
      } else {
        next.remove(br.ifTrueBlock());
        next.remove(br.ifFalseBlock());
        br.ifTrueBlock().prev.remove(this);
        br.ifFalseBlock().prev.remove(this);
      }
    }
    instrList.remove(inst);
    terminated = false;
  }

  public void addInst(IRBaseInst instr) {
    if (instr instanceof Alloca) { // add alloca at the front of "entry block"
      instrList.addFirst(instr);
    } else {
      if (instr instanceof Phi phi) {
        phiInst.add(phi);
      } else if (instr instanceof Branch || instr instanceof Ret){
        if (!terminated) {
          terminated = true;
          tailInst = instr;
          instrList.add(instr);
        }
      } else if (!terminated) {
        instrList.add(instr);
      }
    }
  }

  public void addPhi(Phi phi) {
    phi.parenBlock = this;
    phiInst.add(phi);
  }

  public void addInstFront(IRBaseInst instr) {
    instr.parenBlock = this;
    instrList.add(0, instr);
  }

  public void addInstBack(IRBaseInst instr) {
    instr.parenBlock = this;
    instrList.add(instrList.size() - 1, instr);
  }

  public void relinkBlock(IRBlock prev, IRBlock nex) {
    // change src's terminate instruction
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
