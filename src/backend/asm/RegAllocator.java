package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.instruction.*;
import backend.asm.operand.*;
import utility.Debugger;

import java.util.*;

public class RegAllocator { // graph coloring
  public ASMModule asm;

  private int offset;
  private ASMFunction curFunction;
  private HashMap<Register, Integer> stackAlloc = new HashMap<>();

  private int K;
  HashSet<Register> initial, preColored, coloredNodes, spillWorkList, simplifyWorkList, freezeWorkList, spilledNodes, tinyTemp = new HashSet<>();
  HashMap<Register, Integer> degree;
  HashMap<Register, Register> alias;
  HashMap<Register, Double> weight;
  HashMap<ASMBlock, HashSet<Register>> Bin, Bout, Bgen, Bkill;

  HashSet<Register> coalescedNodes;
  Stack<Register> selectStack;

  HashSet<edge> adjSet;
  HashMap<Register, HashSet<Register>> adjList;
  HashSet<Mv> activeMoves, workListMoves, coalescedMoves, constrainedMoves, frozenMoves;
  HashMap<Register, HashSet<Mv>> moveList;

  public void liveVarAnalysis() {
    Bin = new HashMap<>();
    Bout = new HashMap<>();
    Bgen = new HashMap<>();
    Bkill = new HashMap<>();
    HashSet<ASMBlock> vis = new HashSet<>();
    LinkedList<ASMBlock> workList = new LinkedList<>();

    // In_B = gen_B \cup (Out_B - kill_B)
    for (var block : curFunction.asmBlocks) {
      HashSet<Register> curGen = new HashSet<>(), curKill = new HashSet<>();
      for (var inst : block.instrList) {
        var used = inst.getUses();
        used.removeAll(curKill);
        curGen.addAll(used);
        curKill.addAll(inst.getDefs());
      }
      Bgen.put(block, curGen);
      Bkill.put(block, curKill);
      Bin.put(block, new HashSet<>());
      Bout.put(block, new HashSet<>());
    }
    workList.add(curFunction.getExitBlock());

    while (!workList.isEmpty()) {
      ASMBlock cur = workList.poll();
      vis.remove(cur);
      var curBIn = new HashSet<>(Bout.get(cur));
      curBIn.removeAll(Bkill.get(cur));
      curBIn.addAll(Bgen.get(cur));
      if (Bin.get(cur).equals(curBIn)) continue;
      Bin.replace(cur, curBIn);

      for (var preBlock : cur.prev) {
        Bout.get(preBlock).addAll(curBIn);
        if (!vis.contains(preBlock)) {
          workList.add(preBlock);
          vis.add(preBlock);
        }
      }
    }

//    for (var block : curFunction.asmBlocks) {
//      var x = Bin.get(block);
//      System.out.println("==== " + block.label + " ====");
//      x.forEach(reg -> System.out.print(reg.toString() + " "));
//      System.out.println();
//    }
  }

  public static class edge {
    Register u, v;

    public edge(Register u, Register v) {
      this.u = u;
      this.v = v;
    }

    @Override
    public int hashCode() {
      return u.hashCode() ^ v.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      return (obj instanceof edge e) && e.u == u && e.v == v;
    }
  }

  public void addEdge(Register u, Register v) {
    if (u == v || adjSet.contains(new edge(u, v))) return;
    adjSet.add(new edge(u, v));
    adjSet.add(new edge(v, u));
    if (!preColored.contains(u)) {
      adjList.get(u).add(v);
      int deg = degree.get(u);
      degree.replace(u, deg + 1);
    }
    if (!preColored.contains(v)) {
      adjList.get(v).add(u);
      int deg = degree.get(v);
      degree.replace(v, deg + 1);
    }
  }

  public void init() {
    K = asm.getColors().size();
    alias = new HashMap<>();
    degree = new HashMap<>();
    weight = new HashMap<>();
    initial = new HashSet<>();
    preColored = new HashSet<>(asm.getPhyReg()); // physical registers are all colored
    coloredNodes = new HashSet<>();
    adjSet = new HashSet<>();
    adjList = new HashMap<>();
    moveList = new HashMap<>();
    workListMoves = new HashSet<>();

    spillWorkList = new HashSet<>();
    simplifyWorkList = new HashSet<>();
    freezeWorkList = new HashSet<>();
    activeMoves = new HashSet<>();
    coalescedNodes = new HashSet<>();
    coalescedMoves = new HashSet<>();
    constrainedMoves = new HashSet<>();
    frozenMoves = new HashSet<>();
    selectStack = new Stack<>();
    spilledNodes = new HashSet<>();

    curFunction.asmBlocks.forEach(block -> block.instrList.forEach(inst -> {
      initial.addAll(inst.getDefs());
      initial.addAll(inst.getUses());
    }));
    for (Register reg : initial) {
      adjList.put(reg, new HashSet<>());
      moveList.put(reg, new HashSet<>());
      degree.put(reg, 0);
      weight.put(reg, 0.);
      alias.put(reg, reg);
      reg.color = null;
    }
    initial.removeAll(preColored);
    for (var colReg : preColored) {
      colReg.color = (PhysicalReg) colReg; // avoid being assigned to null in the above
      degree.put(colReg, Integer.MAX_VALUE);
    }

    for (var block : curFunction.asmBlocks)
      for (var inst : block.instrList) {
        inst.getDefs().forEach(x -> {
          double t = weight.get(x) + Math.pow(10.0, block.loopDepth);
          weight.replace(x, t);
        });
        inst.getUses().forEach(x -> {
          double t = weight.get(x) + Math.pow(10.0, block.loopDepth);
          weight.replace(x, t);
        });
      }
  }

  public void build() {
    // build interference graph: based on live variable analysis
    for (var block : curFunction.asmBlocks) {
      var lives = Bout.get(block);
      for (int i = block.instrList.size() - 1; i >= 0; --i) { // enumerate in reverse order
        ASMBaseInst inst = block.instrList.get(i);
        if (inst instanceof Mv mv) {
          // move edge
          lives.removeAll(mv.getUses());
          HashSet<Register> moveNodes = new HashSet<>(Arrays.asList(mv.rd, mv.rs));
          moveNodes.forEach(reg -> moveList.get(reg).add(mv));
          workListMoves.add(mv);
        }
        var defs = inst.getDefs();
        lives.addAll(defs);
        defs.forEach(curReg -> lives.forEach(nxtReg -> addEdge(curReg, nxtReg)));

        lives.removeAll(inst.getDefs()); // (Out_B - kill)
        lives.addAll(inst.getUses()); // (Out_B \cup gen)
      }
    }
  }

  HashSet<Register> adjacent(Register reg) {
    // adjacent nodes that aren't been coalesced or spilled
    HashSet<Register> ret = new HashSet<>(adjList.get(reg));
    HashSet<Register> del = new HashSet<>(selectStack);
    del.addAll(coalescedNodes);
    ret.removeAll(del);
    return ret;
  }

  HashSet<Mv> nodeMoves(Register reg) {
    // move edges (except coalesced moves)
    HashSet<Mv> ret = new HashSet<>(activeMoves);
    ret.addAll(workListMoves);
    ret.retainAll(moveList.get(reg));
    return ret;
  }

  boolean moveRelated(Register reg) {
    return !nodeMoves(reg).isEmpty();
  }

  public void decrementDegree(Register reg) {
    int d = degree.get(reg);
    degree.replace(reg, d - 1);
    if (d == K) {
      HashSet<Register> nodes = adjacent(reg);
      nodes.add(reg);
      enableMoves(nodes);
      spillWorkList.remove(reg);
      if (moveRelated(reg)) freezeWorkList.add(reg);
      else simplifyWorkList.add(reg);
    }
  }

  public void enableMoves(HashSet<Register> nodes) {
    for (Register node : nodes)
      for (Mv mv : nodeMoves(node))
        if (activeMoves.contains(mv)) {
          activeMoves.remove(mv);
          workListMoves.add(mv);
        }
  }

  public void simplify() {
    Register reg = simplifyWorkList.iterator().next();
    simplifyWorkList.remove(reg);
    selectStack.push(reg);
    adjacent(reg).forEach(this::decrementDegree);
  }

  public void addWorkList(Register reg) {
    // non-move related node with insignificant degree -> simplify
    if (!preColored.contains(reg) && degree.get(reg) < K && !moveRelated(reg)) {
      freezeWorkList.remove(reg);
      simplifyWorkList.add(reg);
    }
  }

  public boolean ok(Register node, Register r) {
    // George
    return degree.get(node) < K || preColored.contains(node) || adjSet.contains(new edge(node, r));
  }

  public boolean ok(ArrayList<Register> nodes, Register r) {
    for (var x : nodes) if (!ok(x, r)) return false;
    return true;
  }

  public boolean conservative(ArrayList<Register> nodes, ArrayList<Register> y) {
    // Briggs
    nodes.addAll(y);
    int k = 0;
    for (var reg : nodes)
      if (degree.get(reg) >= K) ++k;
    return k < K;
  }

  public Register getAlias(Register reg) {
    if (coalescedNodes.contains(reg))
      return getAlias(alias.get(reg));
    return reg;
  }

  public void coalesce() {
    Mv mv = workListMoves.iterator().next();
    workListMoves.remove(mv);
    Register x = getAlias(mv.rd), y = getAlias(mv.rs);
    if (preColored.contains(y)) {
      Register t = x;
      x = y;
      y = t;
    }
    if (x == y) {
      coalescedMoves.add(mv);
      addWorkList(x);
    } else if (preColored.contains(y) || adjSet.contains(new edge(x, y))) {
      constrainedMoves.add(mv);
      addWorkList(x);
      addWorkList(y);
    } else if ((preColored.contains(x) && ok(new ArrayList<>(adjacent(y)), x)) ||
            (!preColored.contains(x) && conservative(new ArrayList<>(adjacent(x)), new ArrayList<>(adjacent(y))))) {
      coalescedMoves.add(mv);
      combine(x, y);
      addWorkList(x);
    } else {
      activeMoves.add(mv); // yet not been able to coalesced
    }
  }

  public void combine(Register x, Register y) {
    // combine y into x
    if (freezeWorkList.contains(y)) freezeWorkList.remove(y);
    else spillWorkList.remove(y);
    coalescedNodes.add(y);
    alias.put(y, x);
    moveList.get(x).addAll(moveList.get(y)); // merge x & y
    enableMoves(new HashSet<>(Collections.singletonList(y)));
    adjacent(y).forEach(vertex -> {
      addEdge(vertex, x);
      decrementDegree(vertex);
    });
    if (degree.get(x) >= K && freezeWorkList.contains(x)) {
      freezeWorkList.remove(x);
      spillWorkList.add(x);
    }
  }

  public void freezeMoves(Register reg) {
    nodeMoves(reg).forEach(m -> {
      Register dst = m.rd, src = m.rs;
      Register y = (getAlias(reg) == getAlias(src)) ? getAlias(dst) : getAlias(src);
      activeMoves.remove(m);
      frozenMoves.add(m);
      if (nodeMoves(y).isEmpty() && degree.get(y) < K) {
        freezeWorkList.remove(y);
        simplifyWorkList.add(y);
      }
    });
  }

  public void freeze() {
    Register reg = freezeWorkList.iterator().next();
    freezeWorkList.remove(reg);
    simplifyWorkList.add(reg);
    freezeMoves(reg);
  }

  public void selectSpill() {
    Register target = null;
    double cur = Double.POSITIVE_INFINITY;
    for (var x : spillWorkList) {
      if (tinyTemp.contains(x) || preColored.contains(x)) continue;
      double tmp = weight.get(x) / degree.get(x);
      if (tmp < cur) {
        cur = tmp;
        target = x;
      }
    }
    spillWorkList.remove(target);
    simplifyWorkList.add(target);
    freezeMoves(target);
  }

  public void assignColors() {
    while (!selectStack.isEmpty()) {
      Register cur = selectStack.pop();
      ArrayList<PhysicalReg> okColors = new ArrayList<>(asm.getColors());
      HashSet<Register> colored = new HashSet<>(preColored);
      colored.addAll(coloredNodes);
      adjList.get(cur).forEach(nex -> {
        if (colored.contains(getAlias(nex)))
          okColors.remove(getAlias(nex).color);
      });
      if (okColors.isEmpty()) {
        spilledNodes.add(cur);
      } else {
        coloredNodes.add(cur);
        cur.color = okColors.get(0);
      }
    }
    for (var x : coalescedNodes)
      x.color = getAlias(x).color;
  }

  public void rewriteProgram() {
    for (var reg : spilledNodes) {
      int loc = offset;
      offset += 4;
      stackAlloc.put(reg, loc);
//      System.out.println(reg.toString());
    }
    for (var block : curFunction.asmBlocks) {
      for (int i = 0; i < block.instrList.size(); ++i) {
        var inst = block.instrList.get(i);
        if (inst instanceof Mv mv && spilledNodes.contains(mv.rs) && spilledNodes.contains(mv.rd)) {
          VirtualReg tmp = new VirtualReg("tmp");
          block.instrList.set(i, new Load(tmp, asm.getReg("sp"), allocaOnStack((VirtualReg) mv.rs), 4, null));
          block.instrList.add(i + 1, new Store(tmp, asm.getReg("sp"), allocaOnStack((VirtualReg) mv.rd), 4, null));
          ++i;
          continue;
        }
        for (var reg : inst.getUses())
          if (spilledNodes.contains(reg)) {
            if (inst instanceof Mv mv) {
              block.instrList.set(i, new Load(mv.rd, asm.getReg("sp"), allocaOnStack((VirtualReg) reg), 4, null));
            } else {
              VirtualReg tmp = new VirtualReg("tmp");
              tinyTemp.add(tmp);
              inst.replaceUses(reg, tmp);
              block.instrList.add(i, new Load(tmp, asm.getReg("sp"), allocaOnStack((VirtualReg) reg), 4, null));
              ++i;
            }
          }
        for (var reg : inst.getDefs())
          if (spilledNodes.contains(reg)) {
            if (inst instanceof Mv mv) {
              block.instrList.set(i, new Store(mv.rs, asm.getReg("sp"), allocaOnStack((VirtualReg) reg), 4, null));
            } else {
              VirtualReg tmp = new VirtualReg("tmp");
              tinyTemp.add(tmp);
              inst.replaceDefs(reg, tmp);
              block.instrList.add(i + 1, new Store(tmp, asm.getReg("sp"), allocaOnStack((VirtualReg) reg), 4, null));
              ++i;
            }
          }
      }
    }
  }

  public void makeWorkList() {
    for (Register reg : initial) {
      if (degree.get(reg) >= K) spillWorkList.add(reg);
      else if (moveRelated(reg)) freezeWorkList.add(reg); // move relateds
      else simplifyWorkList.add(reg); // non-move related
    }
  }

  public void runOnFunction(ASMFunction func) {
    curFunction = func;
    offset = curFunction.spOffset;

    tinyTemp = new HashSet<>();
    while (true) {
      init();
      liveVarAnalysis();
      build();
      makeWorkList();
      while (!simplifyWorkList.isEmpty() || !workListMoves.isEmpty() || !freezeWorkList.isEmpty() || !spillWorkList.isEmpty()) {
        if (!simplifyWorkList.isEmpty()) simplify();
        else if (!workListMoves.isEmpty()) coalesce();
        else if (!freezeWorkList.isEmpty()) freeze();
        else selectSpill();
      }
      assignColors();
      if (spilledNodes.isEmpty()) {
        removeDeadMv();
        mergeBlock();
        break;
      }
      rewriteProgram();
    }

    if (offset != 0) {
      func.getEntryBlock().addInstFront(new Calc("addi", asm.getReg("sp"), asm.getReg("sp"), new Immediate(-offset), null));
      func.getExitBlock().addInstBack(new Calc("addi", asm.getReg("sp"), asm.getReg("sp"), new Immediate(offset), null));
    }
  }

  public void removeDeadMv() {
    for (var block : curFunction.asmBlocks) {
      ArrayList<ASMBaseInst> rmInst = new ArrayList<>();
      for (int i = 0; i < block.instrList.size(); ++i) {
        var inst = block.instrList.get(i);
        if (!(inst instanceof Mv mv)) continue;
        if (mv.rd.color == mv.rs.color) {
          rmInst.add(inst);
        } else if (i > 0 && block.instrList.get(i - 1) instanceof Mv lastMv) {
          if ((lastMv.rd.color == mv.rd.color && lastMv.rs.color == mv.rs.color)
                  || lastMv.rd.color == mv.rs.color && lastMv.rs.color == mv.rd.color)
            rmInst.add(inst);
        }
      }
      rmInst.forEach(block::removeInst);
    }
  }

  public void mergeBlock() {
    for (var block : curFunction.asmBlocks) {
      ArrayList<ASMBaseInst> instrList = new ArrayList<>();
      for (var inst : block.instrList) {
        instrList.add(inst);
        if (inst instanceof J) break;
      }
      block.instrList = instrList;
    }
    while (true) {
      boolean flag = false;
      ArrayList<ASMBlock> rmBlock = new ArrayList<>();
      for (var block : curFunction.asmBlocks) {
        if (block.prev.size() != 1) continue;
        var pre = block.prev.get(0);
        // pre -> block
        //    |-> nex
        if (pre.getTerminator() instanceof J j && j.dest == block) {
          pre.removeInst(j);
          pre.next = block.next;
          pre.instrList.addAll(block.instrList);
          for (var nex : pre.next) {
            for (int i = 0; i < nex.prev.size(); ++i)
              if (nex.prev.get(i) == block)
                nex.prev.set(i, pre);
          }
          if (block == curFunction.getExitBlock())
            curFunction.exitBlock = pre;
          rmBlock.add(block);
        }
      }
      for (var block : rmBlock)
        curFunction.asmBlocks.remove(block);
      if (!flag) break;
    }
  }

  private Immediate allocaOnStack(VirtualReg reg) {
    Integer loc = stackAlloc.get(reg);
    assert loc != null;
    return new Immediate(loc);
  }

  public void runOnModule(ASMModule asm) {
    this.asm = asm;
    asm.func.forEach(this::runOnFunction);
  }

}
