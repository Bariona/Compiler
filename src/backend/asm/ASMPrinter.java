package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.operand.GlobalReg;

import java.io.PrintStream;

public class ASMPrinter {
  private final PrintStream os;

  public ASMPrinter(PrintStream os) {
    this.os = os;
  }

  public void printBlock(ASMBlock block) {
    os.println(block.label + ":");
    block.instrList.forEach(inst -> os.println("\t" + inst.toString()));
  }

  public void printFunction(ASMFunction func) {
    os.println("\t.global\t" + func.name);
    os.println("\tp2align\t2");
    os.println("\t.type\t" + func.name + ",@function");
    os.println(func.name + ":");
    func.asmBlocks.forEach(this::printBlock);
    os.println("\t.size\t" + func.name + ", .-" + func.name);
    os.println("\t\t\t # -- End function");
  }

  public void printGlobVar(GlobalReg reg) {
    os.println("\t.type\t" + reg.name + ",@object");
    os.println("\t.section\t.rodata");
    os.println("\t.globl\t" + reg.name);
    os.println(reg.name + ":");
    os.println("\t.word\t0");
    os.println("\t.size\t" + reg.name + ", 4");
  }

  public void printStr(GlobalReg str) {
    os.println("\t.type\t" + str.name + ",@object");
    os.println("\t.section\t.rodata");
    os.println(str.name + ":");
    os.println("\t.asciz\t\"" + str.reFormat() + "\"");
    os.println("\t.size\t" + str.name + ", " + str.str.length());
  }

  public void printModule(ASMModule asm) {
    os.println("\t.text");
    asm.func.forEach(this::printFunction);
    // os.println("\t.section\t.bss");
    asm.globVar.forEach(this::printGlobVar);
    // os.println("\t.section\t.rodata");
    asm.strConst.forEach(this::printStr);
  }

}
