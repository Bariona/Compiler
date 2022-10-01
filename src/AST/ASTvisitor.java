package AST;

public interface ASTvisitor {
  void visit(RootNode it);

  void visit(varDefNode it);
  void visit(classDefNode it);
  void visit(assignExprNode it);

  void visit(binaryExprNode it);
  void visit(varExprNode it);
  void visit(cmpExprNode it);

  void visit(ifStmtNode it);
}
