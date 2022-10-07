package AST;

import AST.Definition.*;
import AST.Expression.*;
import AST.Statement.*;

public interface ASTVisitor {
  void visit(RootNode it);

  // Def
  void visit(varDefNode it);
  void visit(varSingleDefNode it);
  void visit(classDefNode it);
  void visit(funcDefNode it);

  // Expr
  void visit(atomExprNode it);
  void visit(newExprNode it);
  void visit(memberExprNode it);
  void visit(bracketExprNode it);
  void visit(funcExprNode it);
  void visit(selfExprNode it);
  void visit(unaryExprNode it);
  void visit(binaryExprNode it);
  void visit(assignExprNode it);

  // Stmt
  void visit(suiteStmtNode it);
  void visit(ifStmtNode it);
  void visit(brkcontNode it);
  void visit(returnStmtNode it);
  void visit(whileStmtNode it);
  void visit(forStmtNode it);
//  void visit(exprStmtNode it);
}
