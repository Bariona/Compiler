package middleend;

import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;
import middleend.hierarchy.IRModule;

import java.io.PrintStream;

public class IRPrinter {
  private PrintStream os;
  private final String INDENT = "  ";

  public IRPrinter(PrintStream os) {
    this.os = os;
  }

  private void declareFunc(IRFunction function) {
    os.println("declare " + function.toStr());
  }

  public void printBlock(IRBasicBlock block) {
    os.println(block.name + ":");
    for (var instr : block.instrList) {
      os.println(INDENT + instr.toString());
    }
  }

  public void printFunction(IRFunction function) {
    os.println(function.toString() + " {");

    for (int i = 0; i < function.blockList.size(); ++i) {
      if (i == 1) continue;
      printBlock(function.blockList.get(i));
      os.println();
    }
    printBlock(function.getExitBlock());
    os.println();

    os.println("}");
  }

  public void printModule(IRModule module) {
    os.println("; ModuleID = " + module.identifier);
    os.println();

    for (var func : module.builtinFuncList)
      declareFunc(func);
    os.println();

    for (var glob : module.globVarList)
      os.println(glob.toString());
    if (module.globVarList.size() > 0)
      os.println();

    for (var struct : module.structList) {
      StringBuilder ret = new StringBuilder(struct.toString());
      ret.append(" = type { ");
      for (var member : struct.memberType)
        ret.append(member.toString());
      ret.append(" }");
      os.println(ret);
    }
    if (module.structList.size() > 0)
      os.println();

    for (var func : module.irFuncList) {
      printFunction(func);
      os.println();
    }

  }

}