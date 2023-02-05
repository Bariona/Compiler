package frontend.ir.instruction;

import frontend.ir.IRVisitor;
import frontend.ir.Value;
import frontend.ir.hierarchy.IRBlock;

import java.util.ArrayList;
import java.util.Collections;

public class Assign extends IRBaseInst {
  // used for asm
  public Value rs, rd;

  public Assign(Value rs, Value rd) {
    super("assign", null, null);
    this.rs = rs;
    this.rd = rd;
  }

  @Override
  public ArrayList<Value> getUses() {
    return new ArrayList<>(Collections.singletonList(rs));
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public String toString() {
    return "assign " + rd.getName() + " = " + rs.getName();
  }

}
