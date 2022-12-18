package utility.scope;

import frontend.ir.Value;
import frontend.ir.hierarchy.IRBasicBlock;
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
  public ClassScope curClassScope;
  private final Stack<BaseScope> scopeStack;

  private final Stack<IRBasicBlock> loopStep = new Stack<>();
  private final Stack<IRBasicBlock> loopExit = new Stack<>();

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

  // ==== LLVM IR ↓ ====
  public Pair<Value, Boolean> queryValue(String name) {
    for (int i = scopeStack.size() - 1; i >= 0; --i) {
      BaseScope cur = scopeStack.get(i);
      VarInfo ret = cur.queryVarInfo(name);
      if (ret != null && ret.value != null) {
//        if (ret.value == null) {
//          System.out.println(cur.toString());
//          System.out.println("query " + name + "'s value failed!");
//          assert false;
//        }
        return new Pair<>(ret.value, cur instanceof ClassScope);
      }
    }
    return null;
  }

  public void pushLoopBlock(IRBasicBlock step, IRBasicBlock exit) {
    loopExit.push(exit);
    loopStep.push(step);
  }

  public void popLoopBlock() {
    loopExit.pop();
    loopStep.pop();
  }

  public IRBasicBlock loopStep() {
    return loopStep.peek();
  }

  public IRBasicBlock loopExit() {
    return loopExit.peek();
  }


}
