package frontend;

import utility.info.BaseInfo;
import utility.info.ClassInfo;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.scope.*;

import java.util.Stack;

public class ScopeManager {
  public int forLoopCnt;
  ClassScope curClassScope;
  Stack<BaseScope> scopeStack;

  public ScopeManager() {
    forLoopCnt = 0;
    curClassScope = null;
    scopeStack = new Stack<>();
  }

  public void print() {
    for (BaseScope scope : scopeStack)
      scope.print();
  }

  public void addItem(BaseInfo info) {
    if (scopeStack.peek() instanceof ClassScope || scopeStack.peek() instanceof RootScope)
      return ;
    scopeStack.peek().addItem(info);
  }

  public BaseScope curScope() { return scopeStack.peek(); }
  public void pushScope(BaseScope cur) {
    scopeStack.push(cur);
    if (cur instanceof ClassScope) {
      assert curClassScope == null;
      curClassScope = (ClassScope) cur;
    }
  }
  public void popScope() {
    if (curScope() instanceof ClassScope)
      curClassScope = null;
    scopeStack.pop();
  }

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

  public ClassInfo getClassInfo(String className) {
    RootScope rootScope = (RootScope) scopeStack.get(0);
    return rootScope.queryClassInfo(className);
  }

  public String getClassName() {
    return curClassScope.name;
  }

}
