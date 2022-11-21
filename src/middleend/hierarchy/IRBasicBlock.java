package middleend.hierarchy;

import middleend.Value;
import middleend.irinst.IRBaseInst;
import middleend.irtype.LabelType;

import java.util.ArrayList;

public class IRBasicBlock extends Value {
  ArrayList<IRBaseInst> instrList;

  public IRBasicBlock(String name) {
    super(name, new LabelType());
    instrList = new ArrayList<>();
  }

  public void addInst(IRBaseInst inst) {
    instrList.add(inst);
  }

}
