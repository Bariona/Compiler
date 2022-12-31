package frontend.ir.hierarchy;

import frontend.ir.Value;
import frontend.ir.instruction.*;
import frontend.ir.irtype.LabelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IRBlock extends Value {
  public Phi phiInst;
  public LinkedList<IRBaseInst> instrList = new LinkedList<>();
  public HashMap<Value, Value> pCopy = new HashMap<>();
  public ArrayList<IRBlock> prev = new ArrayList<>(), next = new ArrayList<>();
  IRFunction parenFunc;
  IRBaseInst tailInst = null;

  public IRBlock(String name, IRFunction func) {
    super(name, new LabelType());
    parenFunc = func;
    if (func != null) {
      // used for phi elimination
      func.addBlock(this);
    }
  }

  public void addEdge(IRBlock block) {
    block.prev.add(this);
    this.next.add(block);
  }

  public void insert2CFG() {
    Branch inst = this.getTerminator();
    if (inst == null) // "ret" instr
      return ;
    if (inst.isJump()) {
      addEdge(inst.dstBlock());
    } else {
      addEdge(inst.ifTrueBlock());
      addEdge(inst.ifFalseBlock());
    }
  }

  public Branch getTerminator() {
    if (tailInst instanceof Ret)
      return null;
    return (Branch) tailInst;
  }

  public void addInst(IRBaseInst instr) {
    if (instr instanceof Alloca) { // add alloca at the front of "entry block"
      instrList.addFirst(instr);
    } else {
      if (instr instanceof Phi phi) {
        phiInst = phi;
      } else {
        instrList.addLast(instr);
        tailInst = instr;
      }
    }
  }

  public void addInstBack(IRBaseInst instr) {
//    System.out.println("-- " + this.name + " ---- ");
//    instrList.forEach(i -> System.out.println(i.toString()));
    instrList.add(instrList.size() - 1, instr);
//    System.out.println("----");
//    instrList.forEach(i -> System.out.println(i.toString()));
//    System.out.println("--end--");
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
