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

  void visit(atomExprNode it);
  void visit(newExprNode it);
  void visit(memberExprNode it);
  void visit(bracketExprNode it);
  void visit(funcExprNode it);
  void visit(selfExprNode it);
  void visit(unaryExprNode it);
  void visit(binaryExprNode it);
  void visit(assignExprNode it);

  void visit(suiteStmtNode it);
//  void visit(varStmtNode it);
  void visit(ifStmtNode it);
  void visit(brkcontNode it);
  void visit(returnStmtNode it);
  void visit(whileStmtNode it);
  void visit(forStmtNode it);
  void visit(exprStmtNode it);
}
