//package backend.asm.instruction;
//
//import backend.asm.hierarchy.ASMBlock;
//import backend.asm.operand.Address;
//import backend.asm.operand.Immediate;
//import backend.asm.operand.Register;
//
//public class Lui extends ASMBaseInst {
//  public Register rd;
//  private Address addr;
//
//  // x[rd] = sext(immediate[31:12] << 12)
//
//  public Lui(Register rd, Address addr, ASMBlock parenBlock) {
//    super(parenBlock);
//    this.rd = rd;
//    this.addr = addr;
//  }
//
//  @Override
//  public String toString() {
//    return "lui " + rd.toString() + ", " + addr.toString();
//  }
//
//  @Override
//  public void accept(InstVisitor visitor) {
//    visitor.visit(this);
//  }
//
//}
