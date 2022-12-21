package backend.asm.hierarchy;

import backend.asm.operand.Register;
import backend.asm.operand.VirtualReg;

import java.util.ArrayList;

public class ASMFunction {
  public String name = null;

  public VirtualReg raSaved = null;

  public ArrayList<Register> args = new ArrayList<>();
  public ArrayList<VirtualReg> calleeSaved = new ArrayList<>();
  public ArrayList<ASMBlock> asmBlocks = new ArrayList<>();

  public ASMFunction() {
  }

  public void setName(String name) {
    this.name = name;
  }

}
