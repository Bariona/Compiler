package backend.asm.hierarchy;

import backend.asm.operand.GlobalReg;
import backend.asm.operand.PhysicalReg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ASMModule {
  public ArrayList<ASMFunction> func = new ArrayList<>();
  public LinkedList<GlobalReg> globVar = new LinkedList<>();
  public ArrayList<GlobalReg> strConst = new ArrayList<>();

  final private HashMap<String, PhysicalReg> phyRegMp = new HashMap<>();
  final private HashMap<Integer, PhysicalReg> phyRegIdMp = new HashMap<>();
  final private String[] regName = new String[]{
          "zero", "ra", "sp", "gp", "tp", "t0",
          "t1", "t2", "s0", "s1", "a0", "a1", "a2",
          "a3", "a4", "a5", "a6", "a7", "s2", "s3",
          "s4", "s5", "s6", "s7", "s8", "s9", "s10",
          "s11", "t3", "t4", "t5", "t6"
  };

  public ASMModule() {
    for (int i = 0; i < regName.length; ++i) {
      PhysicalReg reg = new PhysicalReg(regName[i]);
      phyRegMp.put(regName[i], reg);
      phyRegIdMp.put(i, reg);
    }
  }

  public PhysicalReg getReg(String name) {
    return phyRegMp.get(name);
  }

  public PhysicalReg getReg(int id) {
    return phyRegMp.get(regName[id]);
  }

  public PhysicalReg a(int id) {
    return phyRegMp.get("a" + id);
  }

  public PhysicalReg s(int id) {
    return phyRegMp.get("s" + id);
  }

  public PhysicalReg t(int id) {
    return phyRegMp.get("t" + id);
  }

  public ArrayList<PhysicalReg> getCaller() {
    ArrayList<PhysicalReg> ret = new ArrayList<>();
    for (int i = 1; i <= 1; i++) ret.add(getReg(i));
    for (int i = 5; i <= 7; i++) ret.add(getReg(i));
    for (int i = 10; i <= 17; i++) ret.add(getReg(i));
    for (int i = 28; i <= 31; i++) ret.add(getReg(i));
    return ret;
  }

  public ArrayList<PhysicalReg> getCallee() {
    ArrayList<PhysicalReg> ret = new ArrayList<>();
    for (int i = 8; i <= 9; ++i) ret.add(getReg(i));
    for (int i = 18; i <= 27; ++i) ret.add(getReg(i));
    return ret;
  }

  public ArrayList<PhysicalReg> getColors() {
    ArrayList<PhysicalReg> ret = new ArrayList<>();
    for (int i = 0; i < 32; ++i) {
      if (i == 0 || i > 1 && i < 5)
        continue;
      PhysicalReg reg = getReg(regName[i]);
      ret.add(reg);
    }
    return ret;
  }

  public ArrayList<PhysicalReg> getPhyReg() {
    ArrayList<PhysicalReg> ret = new ArrayList<>();
    for (int i = 0; i < 32; ++i) {
      PhysicalReg reg = getReg(regName[i]);
      ret.add(reg);
    }
    return ret;
  }

}
