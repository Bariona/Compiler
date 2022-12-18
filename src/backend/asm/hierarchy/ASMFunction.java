package backend.asm.hierarchy;

import backend.asm.operand.Register;
import backend.asm.operand.VirtualReg;

import java.util.ArrayList;

public class ASMFunction {
  public String name;

  public ArrayList<Register> parameters = new ArrayList<>();
  public ArrayList<VirtualReg> calleeSaved = new ArrayList<>();
  public ArrayList<ASMBlock> asmBlocks = new ArrayList<>();

  public ASMFunction(String name) {
    this.name = name;
  }
}
