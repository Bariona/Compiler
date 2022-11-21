package middleend;

import middleend.hierarchy.IRFunction;
import middleend.irinst.*;

public interface IRVisitor {
  void visit(Ret instr);

  void visit(Branch instr);

  void visit(Binary instr);

  void visit(Alloca instr);

  void visit(Load instr);

  void visit(Store instr);

  void visit(GetElePtr instr);

  void visit(Icmp instr);

  void visit(Phi instr);

  void visit(Call instr);

  void visit(IRFunction irFunction);

  void visit(GlobalDef globalDef);
}
