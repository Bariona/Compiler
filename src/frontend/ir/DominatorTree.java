package frontend.ir;

import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DominatorTree {

  IRFunction curFunction = null;

  HashSet<IRBlock> visited = new HashSet<>();
  LinkedList<IRBlock> blocks = new LinkedList<>();

  public ArrayList<IRBlock> rNodes = new ArrayList<>();
  public HashMap<IRBlock, HashSet<IRBlock>> domSubTree = new HashMap<>();

  public HashMap<IRBlock, Integer> dfn = new HashMap<>();
  public HashMap<IRBlock, IRBlock> iDom = new HashMap<>();
  public HashMap<IRBlock, ArrayList<IRBlock>> domChild = new HashMap<>();
  public HashMap<IRBlock, ArrayList<IRBlock>> frontier = new HashMap<>();

  private void dfs(IRBlock block) {
    visited.add(block);
    block.next.forEach(b -> {
      if (!visited.contains(b)) dfs(b);
    });
    blocks.addFirst(block);
  }

  public IRBlock intersect(IRBlock a, IRBlock b) { // lca
    if (a == null) return b;
    if (b == null) return a;
    while (a != b) {
      while (dfn.get(a) > dfn.get(b)) a = iDom.get(a);
      while (dfn.get(b) > dfn.get(a)) b = iDom.get(b);
    }
    return a;
  }

  public void build() { // proposed by Keith D.Cooper
    for (int i = 0; i < blocks.size(); ++i) {
      IRBlock block = blocks.get(i);
      dfn.put(block, i);
      iDom.put(block, null);
      domChild.put(block, new ArrayList<>());
    }
    iDom.replace(curFunction.entryBlock, curFunction.entryBlock);
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int i = 1; i < blocks.size(); ++i) {
        IRBlock cur = blocks.get(i), new_idom = null;
        for (var pre : cur.prev)
          if (iDom.get(pre) != null)
            new_idom = intersect(new_idom, pre);
        if (iDom.get(cur) != new_idom) {
          iDom.put(cur, new_idom);
          changed = true;
        }
      }
    }
    iDom.forEach((u, fa) -> {
      if (fa != null && u != fa) domChild.get(fa).add(u);
      assert fa != null;
    });
  }

  private void getDomFrontier() {
    blocks.forEach(b -> frontier.put(b, new ArrayList<>()));
    for (IRBlock b : blocks) {
      if (b.prev.size() < 2) continue;
      for (IRBlock p : b.prev) {
        IRBlock runner = p;
        while (runner != iDom.get(b)) {
          frontier.get(runner).add(b);
          runner = iDom.get(runner);
        }
      }
    }
  }

  private void dfsOnTree(IRBlock u) {
    HashSet<IRBlock> subTree = new HashSet<>();
    for (var v : domChild.get(u)) {
      subTree.add(v);
      dfsOnTree(v);
      subTree.addAll(domSubTree.get(v));
    }
    rNodes.add(u);
    domSubTree.put(u, subTree);
  }

  public void runOnFunction(IRFunction func) {
    curFunction = func;
    dfs(func.getEntryBlock());
    build();
    getDomFrontier();
    dfsOnTree(curFunction.entryBlock);
  }

  public void printIDom() {
    System.out.println("====  IDOM =====");
    for (var block : curFunction.blockList) {
      System.out.println(block.name + " idom: " + iDom.get(block).name);
    }
    System.out.println("================");
  }


}
