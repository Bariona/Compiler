package middleend;

import middleend.hierarchy.IRBasicBlock;
import middleend.hierarchy.IRFunction;
import middleend.hierarchy.IRModule;

import java.io.PrintStream;

public class IRPrinter {
  private final PrintStream os;
  private static final boolean PRINTLABEL = true;
  private static final boolean NOTPRINTLABEL = false;
  private static final String INDENT = "  ";

  public IRPrinter(PrintStream os) {
    this.os = os;
  }

  public void printModule(IRModule module) {
    os.println("; ModuleID = " + module.identifier);
    os.println();

    for (var func : module.builtinFuncList)
      declareFunc(func);
    os.println();

    os.println("; String builtin functions");
    for (var func : module.strBuiltinFunction)
      declareFunc(func);
    os.println();

    for (var glob : module.globVarList)
      os.println(glob.toString());
    if (module.globVarList.size() > 0)
      os.println();

    for (var struct : module.structMap.values()) {
      StringBuilder ret = new StringBuilder(struct.toString());
      ret.append(" = type { ");
      for (int i = 0; i < struct.memberType.size(); ++i) {
        if (i > 0) ret.append(", ");
        ret.append(struct.memberType.get(i).toString());
      }

      ret.append(" }");
      os.println(ret);
    }
    if (module.structMap.size() > 0)
      os.println();

    for (var func : module.irFuncList) {
      printFunction(func);
      os.println();
    }

  }

  private void declareFunc(IRFunction function) {
    if (function.description != null)
      os.println("; " + function.description);
    os.println("declare " + function.toStr());
    os.println();
  }

  public void printBlock(IRBasicBlock block, boolean ifPrintLabel) {
    if (ifPrintLabel)
      os.println(block.name + ":");
    for (var instr : block.instrList) {
      os.println(INDENT + instr.toString());
    }
  }

  public void printFunction(IRFunction function) {
    if (function.description != null)
      os.println("; " + function.description);

    os.println(function + " {");

    printBlock(function.getEntryBlock(), PRINTLABEL);
    os.println();
    for (int i = 2; i < function.blockList.size(); ++i) {
      printBlock(function.blockList.get(i), PRINTLABEL);
      os.println();
    }
    printBlock(function.getExitBlock(), PRINTLABEL);

    os.println("}");
  }

}