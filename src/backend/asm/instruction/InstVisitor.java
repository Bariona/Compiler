package backend.asm.instruction;

public interface InstVisitor {

  void visit(Branch inst);

  void visit(Calc inst);

  void visit(Call inst);

  void visit(J inst);

  void visit(Li inst);

  void visit(Load inst);

  void visit(Store inst);

  void visit(Mv inst);

  void visit(Ret inst);

  // void visit(Lui inst);

  void visit(NOP nop);

  void visit(La inst);
}
