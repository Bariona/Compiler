package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;

public class Assign extends IRBaseInst {
  // used for asm
  public Value rs, rd;

  public Assign(Value rs, Value rd, IRBlock parenBlock) {
    super("assign", null, parenBlock);
    this.rs = rs;
    this.rd = rd;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}