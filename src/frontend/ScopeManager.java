package frontend;

import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.scope.*;

import java.util.Stack;

public class ScopeManager {
  public int forLoopCnt;
  Stack<BaseScope> scopeStack;

  public ScopeManager() {
    forLoopCnt = 0;
    scopeStack = new Stack<>();
  }

  public BaseScope curScope() { return scopeStack.peek(); }
  public void pushScope(BaseScope cur) { scopeStack.push(cur); }
  public void popScope() { scopeStack.pop(); }

  public boolean isInForLoop() { return forLoopCnt > 0; }

  public VarInfo queryVarName(String name) {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      VarInfo ret = scopeStack.get(i).queryVarInfo(name);
      if (ret != null) return ret;
    }
    return null;
  }

  public FuncInfo queryFuncName(String name) {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      BaseScope cur = scopeStack.get(i);
      if (cur instanceof ClassScope || cur instanceof RootScope) {
        FuncInfo ret = cur.queryFuncInfo(name);
        if (ret != null) return ret;
      }
    }
    return null;
  }

  public FuncScope getFuncScope() {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      if (scopeStack.get(i) instanceof FuncScope)
        return (FuncScope) scopeStack.get(i);
    }
    return null;
  }

}
