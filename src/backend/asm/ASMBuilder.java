package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.instruction.*;
import backend.asm.operand.*;
import frontend.ir.SSADestruct;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.IRBaseInst;
import frontend.ir.instruction.Icmp;
import frontend.ir.irtype.IRBaseType;
import frontend.ir.irtype.PtrType;
import frontend.ir.irtype.StructType;
import frontend.ir.irtype.VoidType;
import frontend.ir.operands.BoolConst;
import frontend.ir.operands.IRBaseConst;
import frontend.ir.operands.IntConst;
import frontend.ir.operands.StringConst;
import utility.Debugger;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMBuilder {
  private final ASMModule asm;

  private final HashMap<IRFunction, ASMFunction> funcMp = new HashMap<>();
  private final HashMap<IRBlock, ASMBlock> blockMp = new HashMap<>();
  private final HashMap<Value, Register> regMap = new HashMap<>();

  private ASMFunction curFunction;
  private ASMBlock curBlock;

  public ASMBuilder(ASMModule asm, IRModule ir) {
    this.asm = asm;
    SSADestruct ssaDestruct = new SSADestruct();
    ssaDestruct.runOnIR(ir);
    for (var v : ir.globVarList) {
      if (v instanceof StringConst str) {
        asm.globVar.add(new GlobalReg(str.name, str.content));
      } else {
        asm.strConst.add(new GlobalReg(v.name));
      }
    }
    ir.irFuncList.forEach(this::dealFunc);
  }

  public ASMFunction getFunction(IRFunction func) {
    ASMFunction ret = funcMp.get(func);
    if (ret == null) {
      ret = new ASMFunction();
      ret.setName(func.name);
      funcMp.put(func, ret);
      this.asm.func.add(ret);
    }
    return ret;
  }

  public ASMBlock getBlock(IRBlock block) {
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
    curFunction = getFunction(func);
    curBlock = getBlock(func.getEntryBlock());

    ArrayList<PhysicalReg> callee = asm.getCallee();
    callee.forEach(reg -> {
      VirtualReg v = new VirtualReg("tmp_" + reg.name);
      curFunction.calleeSaved.add(v);
      new Mv(v, reg, curBlock);
    });

    // init function's arguments
    func.operands.forEach(op -> curFunction.args.add(getRegister(op)));
    int cnt = Integer.min(8, func.operands.size()); // a0-7
    for (int i = 0; i < cnt; ++i) {
      new Mv(curFunction.args.get(i), asm.a(i), curBlock);
    }
    for (int i = cnt, offset = 0; i < func.operands.size(); ++i) { // a8+: memory
      new Load(curFunction.args.get(i), asm.getReg("sp"), new Immediate(offset), 4, curBlock);
      offset += 4;
    }

    func.blockList.forEach(this::dealBlock);
    curFunction = null;
  }

  public void dealBlock(IRBlock irBlock) {
    curBlock = getBlock(irBlock);
    curBlock.setLabel("." + curFunction.name + "." + irBlock.name);
    curFunction.asmBlocks.add(curBlock);
    irBlock.instrList.forEach(this::dealInst);
    curBlock = null;
  }

  public void dealInst(IRBaseInst inst) {
    if (inst instanceof frontend.ir.instruction.Alloca) {
      // do nothing
    } else if (inst instanceof frontend.ir.instruction.Binary binary) {
      String opcode = binary.getOpcode();
      Register rd = getRegister(binary), rs1;
      BaseOperand rs2;
      // TODO: can be improved sometimes into "slli"
      // TODO: mul 4 -> << 2
      Value x = binary.getOperand(0), y = binary.getOperand(1);

      if (y instanceof IntConst ic) {
        rs1 = getRegister(x);
        if (ic.value <= 2047 && -ic.value >= -2048) {
          if (opcode.equals("sub")) {
            opcode = "add";
            rs2 = new Immediate(-ic.value);
          } else rs2 = new Immediate(ic.value);
          opcode += "i";
        } else {
          rs2 = getRegister(y);
          new Li((Register) rs2, new Immediate(ic.value), curBlock);
        }
//        new Calc(opcode + "i", rd, rs1, rs2, curBlock);
      } else {
        rs1 = getRegister(x);
        rs2 = getRegister(y);
      }
      new Calc(opcode, rd, rs1, rs2, curBlock);
    } else if (inst instanceof frontend.ir.instruction.BitCast bitCast) {
      new Mv(getRegister(inst), getRegister(bitCast.getOperand(0)), curBlock);
    } else if (inst instanceof frontend.ir.instruction.Branch branch) {
      if (branch.operands.size() <= 1) {
        // uncond jump
        new J(getBlock((IRBlock)branch.getOperand(0)), curBlock);
      } else {
        // br i1 <cond>, label <iftrue>, label <iffalse>
        ASMBlock trueDst = getBlock((IRBlock) branch.getOperand(1)),
                falseDst = getBlock((IRBlock) branch.getOperand(2));
        Icmp cmp = (Icmp) branch.getOperand(0);
        String opcode = cmp.getOpcode();
        if (opcode.equals("error"))
          Debugger.error("branch error!");

        new Branch(opcode, getRegister(cmp.getOperand(0)), getRegister(cmp.getOperand(1)), trueDst, curBlock);
        new J(falseDst, curBlock);
      }

    } else if (inst instanceof frontend.ir.instruction.Call call) {
      // e.g. call void @_Z1f1Ai(%class.A* noundef %4, i32 noundef 1)  | void f(x, 1) "x" is a class
      int cnt = Integer.min(8, call.operands.size() - 1);
      for (int i = 1; i <= cnt; ++i)
        new Mv(asm.a(i - 1), getRegister(call.getOperand(i)), curBlock);

      if (cnt + 1 < call.operands.size()) {
        int spOffset = (call.operands.size() - cnt - 1) << 2;
        spOffset = -spOffset;
        for (int i = cnt + 1; i < call.operands.size(); ++i) {
          new Store(getRegister(call.getOperand(i)), asm.getReg("sp"), new Immediate(spOffset), 4, curBlock);
          spOffset += 4;
        }
      }
      IRFunction func = (IRFunction) call.getOperand(0);
      new Call(getFunction(func), curBlock);
      if (!(func.getType() instanceof VoidType))
        new Mv(getRegister(call), asm.a(0), curBlock);

    } else if (inst instanceof frontend.ir.instruction.GetElePtr gep) {
      // e.g. %8 = getelementptr inbounds %class.A, %class.A* %1, i32 0, i32 3
      // %1: base  <- current register, say "a1"
      // i32 0: idx | i32 3: member
      Value base = gep.getOperand(0),
              idx = gep.getOperand(1),
              member = gep.getOperand(2);
      Register rs = getRegister(base), rd = getRegister(inst);
      IRBaseType type = ((PtrType) base.getType()).target; // "class.A"

      int offset = 0;
      if (member != null)  // must be a class
        offset = ((StructType) type).calcOffset(((IntConst) member).value);

      if (idx instanceof IntConst ic) {
        offset += ic.value * type.size();
        new Calc("addi", rd, rs, new Immediate(offset), curBlock);
      } else { // a[f()];
        VirtualReg tmp = new VirtualReg("tmp");
        new Calc("mul", tmp, getRegister(idx), new Immediate(type.size()), curBlock);
        if (offset != 0)
//          VirtualReg memberOffset = new VirtualReg("member");
          new Calc("add", tmp, tmp, new Immediate(offset), curBlock);

        new Calc("add", rd, rs, tmp, curBlock);
      }
    } else if (inst instanceof frontend.ir.instruction.Icmp icmp) {
      String opcode = icmp.getOpcode();
      new Calc(opcode, getRegister(inst), getRegister(icmp.getOperand(0)), getRegister(icmp.getOperand(1)), curBlock);
      // TODO: optimize
    } else if (inst instanceof frontend.ir.instruction.Load load) {
      // %val = load i32, i32* %ptr
      Value address = load.getOperand(0);
      if (address.isGlobal()) {
        VirtualReg addrOfGlb = new VirtualReg("addr" );
        new Lui(addrOfGlb, new Address(true, address.name), curBlock);
        new Load(getRegister(inst), addrOfGlb, new Address(false, address.name), 4 , curBlock);
      } else {
        new Load(getRegister(inst), getRegister(address), new Immediate(0), 4, curBlock);
      }
    } else if (inst instanceof frontend.ir.instruction.Store store) {
      //  store i32 %add1, i32* %A_x
      Value target = store.getOperand(0), address = store.getOperand(1);
      if (address.isGlobal()) {
        VirtualReg addrOfGlb = new VirtualReg("addr" );
        new Lui(addrOfGlb, new Address(true, address.name), curBlock);
        new Store(getRegister(target), addrOfGlb, new Address(false, address.name), 4 , curBlock);
      } else {
        new Store(getRegister(target), getRegister(address), new Immediate(0), 4, curBlock);
      }
    } else if (inst instanceof frontend.ir.instruction.Ret ret) {
      // ret %struct.A* %f.ret.addr.load
      Value result = ret.getOperand(0);
      if (result != null)
        new Mv(asm.getReg("a0"), getRegister(result), curBlock);

      var callee = asm.getCallee(); // load callee
      for (int i = 0; i < callee.size(); ++i)
        new Mv(callee.get(i), curFunction.calleeSaved.get(i), curBlock);
      new Mv(asm.getReg("ra"), curFunction.raSaved, curBlock);
      new Ret(curBlock);
    } else {
      Debugger.error("no such instruction");
    }

  }

}