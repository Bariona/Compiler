package middleend.hierarchy;

import middleend.Value;
import middleend.irinst.IRBaseInst;
import middleend.irtype.LabelType;

import java.util.ArrayList;

public class IRBasicBlock extends Value {
  ArrayList<IRBaseInst> instrList;
  IRFunction parenFunc;
  IRBaseInst tailInst = null;

  public IRBasicBlock(String name, IRFunction func) {
    super(name, new LabelType());
    instrList = new ArrayList<>();
    parenFunc = func;
  }

  public void addInst(IRBaseInst inst) {
    instrList.add(inst);
    tailInst = inst;
  }

  public ArrayList<IRBasicBlock> successor() {
    return null;
  }
}
