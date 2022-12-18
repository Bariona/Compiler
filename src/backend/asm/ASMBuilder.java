package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.instruction.*;
import backend.asm.operand.*;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBasicBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.operands.BoolConst;
import frontend.ir.operands.IRBaseConst;
import frontend.ir.operands.IntConst;
import frontend.ir.operands.StringConst;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMBuilder {
  private final ASMModule asm;
  private final HashMap<IRBasicBlock, ASMBlock> blockMp = new HashMap<>();
  private final HashMap<Value, Register> regMap = new HashMap<>();

  private ASMFunction curFunction;
  private ASMBlock curBlock;

  public ASMBuilder(ASMModule asm, IRModule ir) {
    this.asm = asm;
    for (var v : ir.globVarList) {
      if (v instanceof StringConst str) {
        asm.globVar.add(new GlobalReg(str.name, str.content));
      } else {
        asm.strConst.add(new GlobalReg(v.name));
      }
    }
    ir.irFuncList.forEach(this::dealFunc);
  }

  public ASMBlock getBlock(IRBasicBlock block) {
    ASMBlock ret = blockMp.get(block);
    if (ret == null)
      blockMp.put(block, ret = new ASMBlock());
    return ret;
  }

  public Register getRegister(Value operand) {
    if (operand instanceof IRBaseConst) {
      VirtualReg rd = new VirtualReg("tmp");
      if (operand instanceof StringConst stringConst) {
        // lui  a0, %hi(.str)
        // addi a0, a0, %lo(.str)
        VirtualReg rs = new VirtualReg("address");
        new Lui(rd, new Address(true, stringConst.name), curBlock);
        new Calc("addi", rd, rd, new Address(false, stringConst.name), curBlock);
        return rd;
      }

      int value;
      if (operand instanceof IntConst ic) {
        value = ic.value;
      } else if (operand instanceof BoolConst bc) {
        value = bc.isTrue ? 1 : 0;
      } else value = 0;
      new Li(rd, new Immediate(value), curBlock);
      return rd;
    }
    // Value: e.g. %add1 = add %a, %b
    Register reg = regMap.get(operand);
    if (reg == null)
      regMap.put(operand, reg = new VirtualReg(operand.name));
    return reg;
  }

  public void dealFunc(IRFunction func) {
    curFunction = new ASMFunction(func.name);
    curBlock = getBlock(func.getEntryBlock());

    func.operands.forEach(op -> curFunction.parameters.add(getRegister(op)));

    ArrayList<PhysicalReg> callee = asm.getCallee();
    callee.forEach(reg -> {
      VirtualReg v = new VirtualReg("tmp_" + reg.name);
      curFunction.calleeSaved.add(v);
      new Mv(v, reg, curBlock);
    });

    int cnt = Integer.min(8, func.operands.size()); // a0-7
    for (int i = 0; i < cnt; ++i) {
      new Mv(curFunction.parameters.get(i), asm.a(i), curBlock);
    }

    for (int i = cnt, offset = 0; i < func.operands.size(); ++i) { // a8+: memory
      new Load(curFunction.parameters.get(i), asm.getReg("sp"), new Immediate(offset), 4, curBlock);
      offset += 4;
    }
    func.blockList.forEach(this::dealBlock);
    curFunction = null;
  }

  public void dealBlock(IRBasicBlock irBlock) {
    curBlock = getBlock(irBlock);
    curBlock.setLabel("." + curFunction.name + "." + irBlock.name);
    curFunction.asmBlocks.add(curBlock);
    irBlock.instrList.forEach(this::dealInst);
    curBlock = null;
  }

  public void dealInst(IRBaseInst inst) {

  }

}
