package backend.asm;

import backend.asm.hierarchy.ASMBlock;
import backend.asm.hierarchy.ASMFunction;
import backend.asm.hierarchy.ASMModule;
import backend.asm.instruction.Branch;
import backend.asm.instruction.Call;
import backend.asm.instruction.Load;
import backend.asm.instruction.Ret;
import backend.asm.instruction.Store;
import backend.asm.instruction.*;
import backend.asm.operand.*;
import frontend.ir.IRVisitor;
import frontend.ir.SSADestruct;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;
import frontend.ir.hierarchy.IRFunction;
import frontend.ir.hierarchy.IRModule;
import frontend.ir.instruction.*;
import frontend.ir.irtype.IRBaseType;
import frontend.ir.irtype.PtrType;
import frontend.ir.irtype.StructType;
import frontend.ir.irtype.VoidType;
import frontend.ir.operands.*;
import utility.Debugger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ASMBuilder implements IRVisitor {
  private final ASMModule asm;

  private final HashMap<IRFunction, ASMFunction> funcMp = new HashMap<>();
  private final HashMap<IRBlock, ASMBlock> blockMp = new HashMap<>();

  private ASMFunction curFunction;
  private ASMBlock curBlock;

  public ASMBuilder(ASMModule asm, IRModule ir) {
    this.asm = asm;
    SSADestruct ssaDestruct = new SSADestruct();
    ssaDestruct.runOnIR(ir);
    for (var v : ir.globVarList) {
      if (v instanceof StringConst str) {
        asm.strConst.add(new GlobalReg(str.name, str.content));
      } else asm.globVar.add(new GlobalReg(v.name));
    }
    ir.irFuncList.forEach(this::visit);
  }

  public ASMFunction getFunction(IRFunction func) {
    ASMFunction ret = funcMp.get(func);
    if (ret == null) {
      ret = new ASMFunction();
      ret.setName(func.name);
      funcMp.put(func, ret);
      if (!func.isBuiltin)
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
    if (operand instanceof IRBaseConst && !(operand instanceof IReg)) {
      VirtualReg rd = new VirtualReg("tmp");
      if (operand instanceof StringConst stringConst) {
        // lui  a0, %hi(.str)
        // addi a0, a0, %lo(.str)
        VirtualReg rs = new VirtualReg("str.addr");
        new La(rs, stringConst.name, curBlock);
        return rs;
      }

      int value = 0;
      if (operand instanceof IntConst ic) value = ic.value;
      else if (operand instanceof BoolConst bc) value = bc.isTrue ? 1 : 0;

      new Li(rd, new Immediate(value), curBlock);
      return rd;
    }
    // Value: e.g. %add1 = add %a, %b
    Register reg = (Register) operand.asmOperand;
    if (reg == null) {
      reg = new VirtualReg(operand.name);
      operand.asmOperand = reg;
    }
    return reg;
  }

  @Override
  public void visit(IRFunction func) {
    curFunction = getFunction(func);
    curBlock = getBlock(func.getEntryBlock());

    ArrayList<PhysicalReg> calleeList = asm.getCallee();
    calleeList.forEach(callee -> {
      VirtualReg tmp = new VirtualReg("tmp_" + callee.name);
      curFunction.calleeSaved.add(tmp);
      new Mv(tmp, callee, curBlock);
    });
    VirtualReg tmp = new VirtualReg("tmp_ra");
    curFunction.raSaved = tmp;
    new Mv(tmp, asm.getReg("ra"), curBlock);

    // init function's arguments
    func.operands.forEach(op -> curFunction.args.add(getRegister(op)));
    int cnt = Integer.min(8, func.operands.size()); // a0-7
    for (int i = 0; i < cnt; ++i) {
      new Mv(curFunction.args.get(i), asm.a(i), curBlock);
    }
    int offset = -(func.operands.size() - cnt + 1) * 4;
    for (int i = cnt; i < func.operands.size(); ++i) { // a8+: memory
      offset += 4;
      Immediate imm = new Immediate(offset);
      curFunction.spOffset += 4;  // load functions rest(>8) arguments
      new Load(curFunction.args.get(i), asm.getReg("sp"), imm, 4, curBlock);
    }

    func.blockList.forEach(this::dealBlock);
    curFunction.entryBlock = curFunction.asmBlocks.get(0);
    curFunction.exitBlock = curFunction.asmBlocks.get(1);

    deadCodeEle(curFunction);
    curFunction = null;
  }

  public void dealBlock(IRBlock irBlock) {
    curBlock = getBlock(irBlock);
    curBlock.loopDepth = irBlock.loopDepth;
    curBlock.setLabel("." + curFunction.name + "." + irBlock.name);
    irBlock.prev.forEach(block -> curBlock.prev.add(getBlock(block)));
    irBlock.next.forEach(block -> curBlock.next.add(getBlock(block)));

    curFunction.asmBlocks.add(curBlock);
    irBlock.instrList.forEach(inst -> inst.accept(this));
    curBlock = null;
  }

  private void assign(Register rd, Value rs) {
    if (rs instanceof IRBaseInst || rs instanceof IReg) {
      new Mv(rd, getRegister(rs), curBlock);
      return;
    }
    if (rs instanceof StringConst sc) {
      new La(rd, sc.name, curBlock);
      return;
    }
    int value = 0;
    if (rs instanceof IntConst ic) value = ic.value;
    else if (rs instanceof BoolConst bc) value = bc.isTrue ? 1 : 0;
    new Li(rd, new Immediate(value), curBlock);
  }

  @Override
  public void visit(Assign inst) {
    assign(getRegister(inst.rd), inst.rs);
  }

  @Override
  public void visit(Alloca instr) {
    assert false;
  }

  @Override
  public void visit(frontend.ir.instruction.Branch branch) {
    if (branch.operands.size() <= 1) { // uncondtional jump
      new J(getBlock((IRBlock) branch.getOperand(0)), curBlock);
      return;
    }
    // br i1 <cond>, label <iftrue>, label <iffalse>
    ASMBlock trueDst = getBlock((IRBlock) branch.getOperand(1)),
            falseDst = getBlock((IRBlock) branch.getOperand(2));
    var cond = branch.getOperand(0);
    if (cond instanceof Icmp cmp) {
      String opcode = cmp.getOpcode();
      if (opcode.equals("error"))
        Debugger.error("branch error!");

      new Branch(opcode, getRegister(cmp.getOperand(0)), getRegister(cmp.getOperand(1)), trueDst, curBlock);
      new J(falseDst, curBlock);
    } else {
      new Branch("bne", getRegister(cond), asm.getReg("zero"), trueDst, curBlock);
      new J(falseDst, curBlock);
    }
  }

  @Override
  public void visit(Binary binary) {
    String opcode = binary.getOpcode();
    Register rd = getRegister(binary), rs1;
    BaseOperand rs2;
    Value x = binary.getOperand(0), y = binary.getOperand(1);

    if (opcode.equals("div") || opcode.equals("mul") || opcode.equals("rem")) {
      rs1 = getRegister(x);
      rs2 = getRegister(y);
    } else if (y instanceof IntConst ic) {
      rs1 = getRegister(x);
      if (ic.value <= 2047 && -ic.value >= -2048) {
        if (opcode.equals("sub")) {
          opcode = "add";
          rs2 = new Immediate(-ic.value);
        } else rs2 = new Immediate(ic.value);
        opcode += "i";
      } else rs2 = getRegister(y);
    } else if (x instanceof IntConst ic) {
      if (!opcode.equals("sll") && !opcode.equals("sra") && !opcode.equals("sub")) {
        rs1 = getRegister(y);
        if (ic.value <= 2047 && -ic.value >= -2048) {
          rs2 = new Immediate(ic.value);
          opcode += "i";
        } else {
          rs2 = new VirtualReg("tmp");
          new Li((VirtualReg) rs2, new Immediate(ic.value), curBlock);
        }
      } else {
        rs1 = getRegister(x);
        rs2 = getRegister(y);
      }
    } else {
      rs1 = getRegister(x);
      rs2 = getRegister(y);
    }
    new Calc(opcode, rd, rs1, rs2, curBlock);
  }

  @Override
  public void visit(frontend.ir.instruction.Load load) {
    // %val = load i32, i32* %ptr
    Value address = load.getOperand(0);
    if (address.isGlobal()) {
      VirtualReg addrOfGlb = new VirtualReg("addr");
      new La(addrOfGlb, address.name, curBlock);
      new Load(getRegister(load), addrOfGlb, new Immediate(0), 4, curBlock);
    } else {
      new Load(getRegister(load), getRegister(address), new Immediate(0), 4, curBlock);
    }
  }

  @Override
  public void visit(frontend.ir.instruction.Store store) {
    //  store i32 %add1, i32* %A_x
    Value target = store.getOperand(0), address = store.getOperand(1);
    if (address.isGlobal()) {
      VirtualReg tmp = new VirtualReg("addr");
      new La(tmp, address.name, curBlock);
      new Store(getRegister(target), tmp, new Immediate(0), 4, curBlock);
    } else {
      if (address instanceof IReg) {
        new Mv(getRegister(address), getRegister(target), curBlock);
      } else {
        new Store(getRegister(target), getRegister(address), new Immediate(0), 4, curBlock);
      }
    }
  }

  @Override
  public void visit(GetElePtr gep) {
    // e.g. %8 = getelementptr inbounds %class.A, %class.A* %1, i32 0, i32 3
    // %1: base  <- current register, say "a1"
    // i32 0: idx | i32 3: member
    Value base = gep.getOperand(0),
            idx = gep.getOperand(1),
            member = gep.getOperand(2);
    Register rs = getRegister(base), rd = getRegister(gep);
    IRBaseType type = ((PtrType) base.getType()).target; // "class.A"

    int offset = 0;
    if (member != null) { // must be a class/string
      if (type instanceof StructType structType)
        offset = structType.calcOffset(((IntConst) member).value);
    }

    if (idx instanceof IntConst ic) {
      offset += ic.value * type.size();
      new Calc("addi", rd, rs, new Immediate(offset), curBlock);
    } else { // a[f()];
      VirtualReg tmp = new VirtualReg("tmp");
      int power = (int) (Math.log(type.size()) / Math.log(2));
      assert type.size() == (1 << power);
      new Calc("mul", tmp, getRegister(idx), getRegister(new IntConst(type.size())), curBlock);
      if (offset != 0)
//          VirtualReg memberOffset = new VirtualReg("member");
        new Calc("add", tmp, tmp, new Immediate(offset), curBlock);
      new Calc("add", rd, rs, tmp, curBlock);
    }
  }

  @Override
  public void visit(Icmp icmp) {
    String opcode = icmp.opcode;
    Register rd = getRegister(icmp),
            rs1 = getRegister(icmp.getOperand(0)),
            rs2 = getRegister(icmp.getOperand(1));
    switch (opcode) {
      case "slt" -> new Calc("slt", rd, rs1, rs2, curBlock);
      case "sgt" -> new Calc("slt", rd, rs2, rs1, curBlock);
      case "sle" -> {
        VirtualReg tmp = new VirtualReg("tmp");
        new Calc("slt", tmp, rs2, rs1, curBlock);
        new Calc("xori", rd, tmp, new Immediate(1), curBlock);
      }
      case "sge" -> {
        VirtualReg tmp = new VirtualReg("tmp");
        new Calc("slt", tmp, rs1, rs2, curBlock);
        new Calc("xori", rd, tmp, new Immediate(1), curBlock);
      }
      case "eq" -> {
        VirtualReg tmp = new VirtualReg("tmp");
        new Calc("xor", tmp, rs1, rs2, curBlock);
        new Calc("sltiu", rd, tmp, new Immediate(1), curBlock);
      }
      case "ne" -> {
        VirtualReg tmp = new VirtualReg("tmp");
        new Calc("xor", tmp, rs1, rs2, curBlock);
        new Calc("snez", rd, tmp, asm.getReg("zero"), curBlock);
      }
      default -> Debugger.error("no such opcode");
    }
  }

  @Override
  public void visit(frontend.ir.instruction.Call call) {
    // e.g. call void @_Z1f1Ai(%class.A* noundef %4, i32 noundef 1)  | void f(x, 1) "x" is a class
    int cnt = Integer.min(8, call.operands.size() - 1);
    for (int i = 1; i <= cnt; ++i)
      new Mv(asm.a(i - 1), getRegister(call.getOperand(i)), curBlock);

    if (cnt < call.operands.size() - 1) {
      int spOffset = (call.operands.size() - 1 - cnt) << 2;
      spOffset = -spOffset;
      for (int i = cnt + 1; i < call.operands.size(); ++i) {
        new Store(getRegister(call.getOperand(i)), asm.getReg("sp"), new Immediate(spOffset), 4, curBlock);
        spOffset += 4;
      }
    }
    IRFunction func = (IRFunction) call.getOperand(0);
    new Call(getFunction(func), asm, curBlock);
    if (!(func.getType() instanceof VoidType))
      new Mv(getRegister(call), asm.a(0), curBlock);
  }

  @Override
  public void visit(frontend.ir.instruction.Ret ret) {
    // ret %struct.A* %f.ret.addr.load
    Value result = ret.getOperand(0);
    if (result != null)
      assign(asm.a(0), result);

    var callee = asm.getCallee(); // load callee
    for (int i = 0; i < callee.size(); ++i)
      new Mv(callee.get(i), curFunction.calleeSaved.get(i), curBlock);
    new Mv(asm.getReg("ra"), curFunction.raSaved, curBlock);
    new Ret(asm, curBlock);
  }

  @Override
  public void visit(BitCast inst) {
    new Mv(getRegister(inst), getRegister(inst.getOperand(0)), curBlock);
  }

  @Override
  public void visit(Phi instr) {
    Debugger.error("visit phi node");
  }

  @Override
  public void visit(GlobalDef globalDef) {
  }

  public void deadCodeEle(ASMFunction func) {
    boolean check = true;
    while (check) {
      check = false;
      HashSet<Register> used = new HashSet<>();
      used.add(asm.getReg("ra")); // don't ignore return address
      for (var block : func.asmBlocks)
        block.instrList.forEach(inst -> used.addAll(inst.getUses()));

      for (var block : func.asmBlocks) {
        ArrayList<ASMBaseInst> rm = new ArrayList<>();
        for (var inst : block.instrList) {
          if (inst instanceof Mv mv && mv.rd instanceof VirtualReg && !used.contains(mv.rd)) {
//            System.out.println(mv.toString());
            rm.add(inst);
            check = true;
          }
          if ((inst instanceof Calc cal && !used.contains(cal.rd)) ||
                  (inst instanceof La la && !used.contains(la.rd)) ||
                  (inst instanceof Li li && !used.contains(li.rd)) ||
                  (inst instanceof Load ld && !used.contains(ld.rd))) {
            rm.add(inst);
            check = true;
          }
        }
        rm.forEach(block::removeInst);
      }
    }
  }

}