package frontend;

import ast.ASTVisitor;
import ast.RootNode;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import utility.Position;
import utility.Scope;
import utility.type.BaseType;
import utility.type.VarType;

import java.util.Stack;

public class SemanticChecker implements ASTVisitor {
  private Stack<Scope> scopeStack = new Stack<>();

  void pushScope(Scope cur) {
    scopeStack.push(cur);
  }

  void popScope() {
    scopeStack.pop();
  }

  @Override
  public void visit(RootNode node) {
    pushScope(node.scope);
    node.defs.forEach(it -> it.accept(this));
    popScope();
  }

  @Override
  public void visit(VarDefNode node) {
    node.varList.forEach(it -> it.accept(this));
  }

  @Override
  public void visit(VarSingleDefNode node) {
    node.expr.accept(this);
    BaseType exprType = node.expr.exprType;
    // 这里要加一个类型检查, 和定义的variable定义进行比对
  }

  @Override
  public void visit(ClassDefNode node) {
//    node.varDefs.forEach();
  }

  @Override
  public void visit(FuncDefNode node) {

  }

  @Override
  public void visit(AtomExprNode node) {

  }

  @Override
  public void visit(NewExprNode node) {

  }

  @Override
  public void visit(MemberExprNode node) {

  }

  @Override
  public void visit(BracketExprNode node) {

  }

  @Override
  public void visit(FuncExprNode node) {

  }

  @Override
  public void visit(SelfExprNode node) {

  }

  @Override
  public void visit(UnaryExprNode node) {

  }

  @Override
  public void visit(BinaryExprNode node) {

  }

  @Override
  public void visit(AssignExprNode node) {

  }

  @Override
  public void visit(SuiteStmtNode node) {

  }

  @Override
  public void visit(IfStmtNode node) {

  }

  @Override
  public void visit(BreakContinueNode node) {

  }

  @Override
  public void visit(ReturnStmtNode node) {

  }

  @Override
  public void visit(WhileStmtNode node) {

  }

  @Override
  public void visit(ForStmtNode node) {

  }
}
