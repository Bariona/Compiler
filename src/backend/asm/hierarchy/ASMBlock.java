package backend.asm.hierarchy;

import backend.asm.instruction.ASMBaseInst;

import java.util.ArrayList;

public class ASMBlock {
  public String label = null;
  public int loopDepth = 0;
  public ArrayList<ASMBaseInst> instrList = new ArrayList<>();
  public ArrayList<ASMBlock> prev = new ArrayList<>(), next = new ArrayList<>();

  public void setLabel(String label) {
    this.label = label;
  }

  public void addInst(ASMBaseInst inst) {
    instrList.add(inst);
  }

  public void addInstFront(ASMBaseInst inst) {
    instrList.add(0, inst);
  }

  public void addInstBack(ASMBaseInst inst) {
    instrList.add(instrList.size() - 1, inst);
  }

  public void removeInst(ASMBaseInst inst) {
    instrList.remove(inst);
  }

  public ASMBaseInst getTerminator() {
    return instrList.get(instrList.size() - 1);
  }

  @Override
  public String toString() {
    return label;
  }
}
