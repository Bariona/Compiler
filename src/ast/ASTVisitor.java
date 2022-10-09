package ast;

import ast.definition.*;
import ast.expression.*;
import ast.statement.*;

public interface ASTVisitor {
  void visit(RootNode it);

  // Def
  void visit(VarDefNode it);
  void visit(VarSingleDefNode it);
  void visit(ClassDefNode it);
  void visit(FuncDefNode it);

  // Expr
  void visit(AtomExprNode it);
  void visit(NewExprNode it);
  void visit(MemberExprNode it);
  void visit(BracketExprNode it);
  void visit(FuncExprNode it);
  void visit(SelfExprNode it);
  void visit(UnaryExprNode it);
  void visit(BinaryExprNode it);
  void visit(AssignExprNode it);

  // Stmt
  void visit(SuiteStmtNode it);
  void visit(IfStmtNode it);
  void visit(BreakContinueNode it);
  void visit(ReturnStmtNode it);
  void visit(WhileStmtNode it);
  void visit(ForStmtNode it);
//  void visit(exprStmtNode it);
}
