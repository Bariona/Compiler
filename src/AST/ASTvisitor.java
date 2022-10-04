package AST;

import AST.Definition.*;
import AST.Expression.*;
import AST.Statement.*;

public interface ASTvisitor {
  void visit(RootNode it);

  void visit(varDefNode it);
  void visit(varSingleDefNode it);
  void visit(classDefNode it);
  void visit(funcDefNode it);

  void visit(varExprNode it);
  void visit(cmpExprNode it);
  void visit(binaryExprNode it);
  void visit(assignExprNode it);

  void visit(suiteStmtNode it);
  void visit(varStmtNode it);
  void visit(ifStmtNode it);
  void visit(returnStmtNode it);
  void visit(whileStmtNode it);
  void visit(forStmtNode it);
  void visit(exprStmtNode it);
}
