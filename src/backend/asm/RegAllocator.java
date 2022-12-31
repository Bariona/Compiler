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
  private ASMFunction curFunction;
  private HashMap<Register, Integer> stackAlloc = new HashMap<>();

  public void doit(ASMModule asm) {
    this.asm = asm;
    asm.func.forEach(this::runOnFunction);
  }

  public void runOnFunction(ASMFunction func) {
    curFunction = func;
    offset = curFunction.spOffset;
    func.asmBlocks.forEach(this::runOnBlock);
    curFunction.stackOffsetImm.forEach(imm -> imm.value += offset);
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
//       curBlock.addInst(new NOP());
    });
    curBlock = null;
  }

  private Immediate allocOnStack(VirtualReg reg) {
    Integer loc = stackAlloc.get(reg);
    if (loc == null) {
      loc = (offset += 4);
      stackAlloc.put(reg, loc);
    }
    var imm = new Immediate(-loc);
    curFunction.stackOffsetImm.add(imm);
    return imm;
  }

  private BaseOperand load(BaseOperand op, String regName) {
    if (!(op instanceof VirtualReg reg))
      return op;
    PhysicalReg phyReg;
    if (reg.color != 8) {
      phyReg = asm.getReg(regName);
      new Load(phyReg, asm.getReg("sp"), allocOnStack(reg), 4, curBlock);
    } else {
      phyReg = asm.getReg("sp");
      phyReg.setOffset(allocOnStack(reg));
    }
    return phyReg;
  }

  private PhysicalReg store(Register op, String regName) {
    if (!(op instanceof VirtualReg reg))
      return (PhysicalReg) op;

    PhysicalReg phyReg;
    if (reg.color != 8) {
      phyReg = asm.getReg(regName);
      new Store(phyReg, asm.getReg("sp"), allocOnStack(reg), 4, curBlock);
    } else {
      phyReg = asm.getReg("sp");
      phyReg.setOffset(allocOnStack(reg));
    }
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
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

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
    inst.rs = (Register) load(inst.rs, "s1");
    curBlock.addInst(inst);
    inst.rd = store(inst.rd, "s0");
  }

  @Override
  public void visit(Store inst) { // store reg offset(address)
    inst.rs = (Register) load(inst.rs, "s0");
    inst.rd = (Register) load(inst.rd, "s1");
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
