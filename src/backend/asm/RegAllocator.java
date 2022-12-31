package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.instruction.*;
import backend.asm.operand.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RegAllocator implements InstVisitor { // codegen: actually there are only stack allocation
  public ASMModule asm;
  private int offset;
  private ASMBlock curBlock;
  private HashMap<Register, Integer> stackLoc = new HashMap<>();

  public void doit(ASMModule asm) {
    this.asm = asm;
    asm.func.forEach(this::runOnFunction);
  }

  public void runOnFunction(ASMFunction func) {
    offset = 0;
    func.asmBlocks.forEach(this::runOnBlock);
    // offset += 4;
    for (var block : func.asmBlocks) {
      block.instrList.forEach(inst -> {
        if (inst instanceof Load ld && ld.rs.name.equals("sp")) {
          ld.offset.value += offset;
        } else if (inst instanceof Store store && store.rd.name.equals("sp")) {
          store.offset.value += offset;
        }
      });
    }
    // func.getEntryBlock().addInstFront(new Calc("addi", asm.getReg("s0"), asm.getReg("sp"), new Immediate(offset), null));
    func.getEntryBlock().addInstFront(new Calc("addi", asm.getReg("sp"), asm.getReg("sp"), new Immediate(-offset), null));

    func.getExitBlock().addInstBack(new Calc("addi", asm.getReg("sp"), asm.getReg("sp"), new Immediate(offset), null));
  }

  public void runOnBlock(ASMBlock block) {
    ArrayList<ASMBaseInst> older = block.instrList;
    block.instrList = new ArrayList<>();
    curBlock = block;
//    System.out.println("---- cur Block: " + block.label + " ----");
    older.forEach(inst -> {
      inst.accept(this);
      // curBlock.addInst(new NOP());
    });
    curBlock = null;
  }

  private int Location(VirtualReg reg) {
    Integer loc = stackLoc.get(reg);
    if (loc == null) {
      offset += 4;
      stackLoc.put(reg, offset);
      loc = offset;
//      System.out.println("sp: " + reg.name + " " + offset);
    }
    return loc;
  }

  private BaseOperand load(BaseOperand op, String regName) {
    if (!(op instanceof VirtualReg reg))
      return op;
    PhysicalReg phyReg = asm.getReg(regName);
    new Load(phyReg, asm.getReg("sp"), new Immediate(-Location(reg)), 4, curBlock);
    return phyReg;
  }

  private PhysicalReg store(Register op, String regName) {
    if (!(op instanceof VirtualReg reg))
      return (PhysicalReg) op;
    PhysicalReg phyReg = asm.getReg(regName);
    new Store(phyReg, asm.getReg("sp"), new Immediate(-Location(reg)), 4, curBlock);
    return phyReg;
  }

  @Override
  public void visit(Branch inst) {
    inst.rs1 = (Register) load(inst.rs1, "s0");
    inst.rs2 = (Register) load(inst.rs2, "s1");
    curBlock.addInst(inst);
  }

  @Override
  public void visit(Calc inst) {
    inst.rs1 = load(inst.rs1, "s0");
    inst.rs2 = load(inst.rs2, "s1");
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

  @Override
  public void visit(Call inst) {
    curBlock.addInst(inst);
  }

  @Override
  public void visit(J inst) {
    curBlock.addInst(inst);
  }

  @Override
  public void visit(Li inst) {
//    if (inst.rd.color == null)
//      inst.rd.color = asm.s(0);
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

//  @Override
//  public void visit(Lui inst) {
////    if (inst.rd.color == null)
////      inst.rd.color = asm.s(0);
//    inst.rd = (Register) load(inst.rd, "s0");
//    curBlock.addInst(inst);
//    inst.rd = store(inst.rd, "s0");
//  }

  @Override
  public void visit(Mv inst) {
    inst.rs = (Register) load(inst.rs, "s0");
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s1");
  }

  @Override
  public void visit(Ret inst) {
    curBlock.addInst(inst);
  }

  @Override
  public void visit(Load inst) { // load reg offset(address)
//    assert inst.address instanceof VirtualReg;
    if (inst.address == null) {
      inst.rs = (Register) load(inst.rs, "s1");
    } else inst.offset.value = -Location((VirtualReg) inst.address);
//    inst.offset.value = -Location((VirtualReg) inst.rs);
//    inst.rs = asm.getReg("sp");
//    memoryAccess.add(inst);
//    inst.address = (Register) load(inst.address, "s0");
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

  @Override
  public void visit(Store inst) { // store reg offset(address)
    inst.rs = (Register) load(inst.rs, "s0");
    if (inst.address == null) {
      inst.rd = (Register) load(inst.rd, "s1");
    } else inst.offset.value = -Location((VirtualReg) inst.address);
    // inst.address = (Register) load(inst.address, "s1");
    // load(inst.address, 1);
//    assert inst.address instanceof VirtualReg;
//    inst.offset.value = -Location((VirtualReg) inst.address);
//    inst.address = asm.getReg("sp");
//    memoryAccess.add(inst);
    curBlock.addInst(inst);
  }

  @Override
  public void visit(La inst) {
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

  @Override
  public void visit(NOP nop) {
  }

}
