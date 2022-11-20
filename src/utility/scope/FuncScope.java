package utility.scope;

import utility.info.FuncInfo;

public class FuncScope extends SuiteScope {
  public FuncInfo info;

  public FuncScope(FuncInfo info) {
    this.info = info;
  }

  @Override
  public void print() {
    System.out.println("==== Function Scope ====");
    System.out.print("  Variable Table: ");
    varInfoTable.forEach((str, info) -> System.out.print(" " + str)); System.out.println();
  }

}
