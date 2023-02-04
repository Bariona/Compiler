package backend.asm.hierarchy;

import backend.asm.operand.Immediate;
import backend.asm.operand.Register;
import backend.asm.operand.VirtualReg;

import java.util.ArrayList;

public class ASMFunction {
  public String name = null;
  public int spOffset = 0;

  public VirtualReg raSaved = null;
  public ArrayList<Register> args = new ArrayList<>();
  public ArrayList<VirtualReg> calleeSaved = new ArrayList<>();

  public ArrayList<ASMBlock> asmBlocks = new ArrayList<>();

  public void setName(String name) {
    this.name = name;
  }

  public ASMBlock entryBlock = null, exitBlock = null;

  public ASMBlock getEntryBlock() {
    return entryBlock;
  }

  public ASMBlock getExitBlock() {
    return exitBlock;
  }

}
