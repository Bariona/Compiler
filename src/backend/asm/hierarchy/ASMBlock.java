package backend.asm.hierarchy;

import backend.asm.instruction.ASMBaseInst;

import java.util.ArrayList;

public class ASMBlock {
  public String label = null;
  public ArrayList<ASMBaseInst> instrList = new ArrayList<>();

  public ASMBlock() {
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void addInst(ASMBaseInst inst) {
    instrList.add(inst);
  }

  @Override
  public String toString() {
    return label;
  }
}
