package frontend.ir.hierarchy;

import frontend.ir.Value;
import frontend.ir.instruction.Alloca;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.irtype.LabelType;

import java.util.ArrayList;
import java.util.LinkedList;

public class IRBasicBlock extends Value {
  public LinkedList<IRBaseInst> instrList = new LinkedList<>();
  IRFunction parenFunc;
  IRBaseInst tailInst = null;

  public IRBasicBlock(String name, IRFunction func) {
    super(name, new LabelType());
    parenFunc = func;
    func.addBlock(this);
  }

  public void addInst(IRBaseInst instr) {
    if (instr instanceof Alloca) { // add alloca at the front of "entry block"
      instrList.addFirst(instr);
    } else {
      instrList.addLast(instr);
    }
    tailInst = instr;
  }

  public ArrayList<IRBasicBlock> successor() {
    return null;
  }
}
