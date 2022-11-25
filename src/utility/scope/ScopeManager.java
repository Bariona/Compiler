package utility.scope;

import middleend.Value;
import org.antlr.v4.runtime.misc.Pair;
import utility.error.SemanticError;
import utility.info.BaseInfo;
import utility.info.ClassInfo;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import utility.type.VarType;

import java.util.Stack;

public class ScopeManager {
  private int loopCnt;
  public Stack<VarType> lambdaReturn;
  private ClassScope curClassScope;
  private final Stack<BaseScope> scopeStack;

  public ScopeManager() {
    loopCnt = 0;
    curClassScope = null;
    scopeStack = new Stack<>();
    lambdaReturn = new Stack<>();
  }

  public void print() {
    for (BaseScope scope : scopeStack)
      scope.print();
  }

  public void addItem(BaseInfo info) {
    if (scopeStack.peek() instanceof ClassScope)
      return; // avoid class's function being pushed twice
    if (queryClassInfo(info.name) != null)
      throw new SemanticError("name duplicated with class " + info.name, info.pos);
    scopeStack.peek().addItem(info);
  }

  public BaseScope curScope() {
    return scopeStack.peek();
  }

  public void pushScope(BaseScope cur) {
    scopeStack.push(cur);
    if (cur instanceof ClassScope) {
      assert curClassScope == null;
      curClassScope = (ClassScope) cur;
    }

    if (cur instanceof LoopScope)
      ++loopCnt;
  }

  public void popScope() {
    if (curScope() instanceof ClassScope)
      curClassScope = null;
    if (curScope() instanceof LoopScope)
      --loopCnt;
    scopeStack.pop();
  }

  public boolean isInForLoop() {
    return loopCnt > 0;
  }

  public BaseInfo queryName(String name) {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      BaseScope cur = scopeStack.get(i);
      if (cur instanceof ClassScope || cur instanceof RootScope) {
        FuncInfo ret = cur.queryFuncInfo(name);
        if (ret != null) return ret;
      }
      VarInfo ret = cur.queryVarInfo(name);
      if (ret != null) return ret;
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

  public ClassInfo queryClassInfo(String className) {
    RootScope rootScope = (RootScope) scopeStack.get(0);
    return rootScope.queryClassInfo(className);
  }

  public String getCurClassName() {
    return curClassScope.name;
  }

  public ClassInfo getCurClassInfo() {
    return queryClassInfo(getCurClassName());
  }

  public Pair<Value, Boolean> queryValue(String name) {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      BaseScope cur = scopeStack.get(i);
      VarInfo ret = cur.queryVarInfo(name);
      if (ret != null) {
        assert ret.value != null;
        return new Pair<>(ret.value, cur instanceof ClassScope);
      }
    }
    return null;
  }

}
