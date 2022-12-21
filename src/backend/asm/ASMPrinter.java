package backend.asm;

import backend.asm.hierarchy.ASMModule;

import java.io.PrintStream;

public class ASMPrinter {
  private final PrintStream os;

  public ASMPrinter(PrintStream os) {
    this.os = os;
  }

  public void printModule(ASMModule asm) {
    os.println("\t.text");
    os.println("\t.section\t.bss");
    os.println("\t.section\t.rodata");

  }

}
