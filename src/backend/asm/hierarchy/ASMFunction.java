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

  public ArrayList<Immediate> stackOffsetImm = new ArrayList<>();

  public ASMBlock getEntryBlock() {
    return asmBlocks.get(0);
  }

  public ASMBlock getExitBlock() {
    return asmBlocks.get(1);
  }

  public void setName(String name) {
    this.name = name;
  }

}
