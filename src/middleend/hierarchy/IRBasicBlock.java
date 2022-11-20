package middleend.hierarchy;

import middleend.irinst.IRBaseInst;

import java.util.ArrayList;

public class IRBasicBlock {
  ArrayList<IRBaseInst> baseInstList;

  public IRBasicBlock() {
    baseInstList = new ArrayList<>();
  }

  public void addInst(IRBaseInst inst) {
    baseInstList.add(inst);
  }

}
