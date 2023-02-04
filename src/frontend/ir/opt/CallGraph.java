package frontend.ir.opt;

import frontend.ir.Value;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.Call;
import frontend.ir.instruction.GlobalDef;
import frontend.ir.instruction.Store;

import java.util.HashSet;
import java.util.Stack;

public class CallGraph {
  // build call graph
  IRModule module;

  public static class Node {
    public IRFunction func;
    public boolean cyclic = false;
    public HashSet<Call> callInst;
    public HashSet<IRFunction> caller, callee;
    // globUses: all possible uses even during the process of function call.
    public HashSet<Value> globUses, globDefs;

    public Node(IRFunction func) {
      this.func = func;
      callInst = new HashSet<>();
      caller = new HashSet<>();
      callee = new HashSet<>();
      globUses = new HashSet<>();
      globDefs = new HashSet<>();
    }

  }

  HashSet<IRFunction> visited = new HashSet<>();
  Stack<IRFunction> callStack = new Stack<>();

  private void build() {
    for (var func : module.irFuncList)
      for (var block : func.blockList)
        for (var inst : block.instrList) {
          if (inst instanceof Call call) {
            func.node.callInst.add(call);
            var callee = call.callFunc();
            func.node.callee.add(callee);
            callee.node.caller.add(func);
          }
          // global usages
          for (var op : inst.operands) {
            if (op instanceof GlobalDef)
              func.node.globUses.add(op);
          }
          if (inst instanceof Store store && store.storePtr() instanceof GlobalDef)
            func.node.globDefs.add(store.storePtr());
        }
    // propagate function's global uses
    boolean changed = true;
    while (changed) {
      changed = false;
      for (var func : module.irFuncList)
        for (var callee : func.node.callee) {
          if (!func.node.globUses.containsAll(callee.node.globUses)) {
            changed = true;
            func.node.globUses.addAll(callee.node.globUses);
          }
        }
    }
  }

  public void dfs(IRFunction func) {
    if (visited.contains(func)) return ;
    visited.add(func);
    callStack.push(func);
    for (var caller : callStack)
      if (func.node.callee.contains(caller)) {
        func.node.cyclic = true;
        break;
      }
    for (var callee : func.node.callee)
      dfs(callee);
    callStack.pop();
  }

  public void runOnModule(IRModule module) {
    this.module = module;
    module.irFuncList.forEach(func -> func.node = new Node(func));
    build();
    for (var func : module.irFuncList) // find circle in call graph
      if (!visited.contains(func)) dfs(func);
  }
}
