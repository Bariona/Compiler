package Frontend;

import AST.ASTvisitor;
import AST.Definition.*;
import AST.Expression.*;
import AST.Statement.*;
import AST.RootNode;

import java.io.PrintStream;

public class ASTPrinter implements ASTvisitor {
  final String indent = "  ";
  private PrintStream out;
  private int IndentCnt, id;

  public ASTPrinter(PrintStream out) {
    this.out = out;
    IndentCnt = -1;
    id = 0;
  }

  private void PrintIn(String str) {
    out.printf("%s---- %s ----\n", indent.repeat(IndentCnt), str);
  }

  private void Print(String str) {
    out.printf("%s%s\n", indent.repeat(IndentCnt), str);
  }

  @Override
  public void visit(RootNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Root Node " + tmp + " position:" + node.pos.toString());
    node.Defs.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(varDefNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("VariableList " + tmp + " position:" + node.pos.toString());
    node.varlist.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(varSingleDefNode node) {
    ++IndentCnt;
    Print("Name: " + node.name);
    Print("Type: " + node.type.typename() + "[]".repeat(node.type.dimension));
    if(node.expr != null) {
      node.expr.accept(this);
    }

    --IndentCnt;
    out.println();
  }

  @Override
  public void visit(classDefNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn(node.name);
    node.varDefs.forEach(v -> v.accept(this));
    node.funcDefs.forEach(f -> f.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(funcDefNode node) {
    int tmp = ++id;
    ++IndentCnt;
    PrintIn("Function Definition: " + node.name + " pos: " + node.pos.toString());

    String paralist = indent;
    for(var p : node.parameterList) {
      paralist += p.a.typename() + " " + p.b + ", ";
    }
    Print(paralist);

    --IndentCnt;
  }

  @Override
  public void visit(varExprNode it) {

  }

  @Override
  public void visit(cmpExprNode it) {

  }

  @Override
  public void visit(atomExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("AtomExpr Node " + tmp + " pos: " + node.pos);
    Print(indent + node.atom.getText());

    --IndentCnt;
  }

  @Override
  public void visit(newExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("NewExpr Node " + tmp + " pos: " + node.pos);
    Print("  Type: " + node.restype.typename() + ", dimension: " + node.DimensionExpr.size());
    node.DimensionExpr.forEach(d -> d.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(memberExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("MemberExpr Node " + tmp + " pos: " + node.pos);
    Print("  Type: " + node.restype.typename() + " member: " + node.member);
    node.callExpr.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(bracketExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Bracket Node " + tmp + " pos: " + node.pos);
    node.callExpr.accept(this);
    node.index.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(funcExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("FunctionCall Node " + tmp + " pos: " + node.pos);
    node.callExpr.accept(this);
    Print("ArgumentList");
    node.argumentList.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(selfExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("SelfADD/SUB Node " + tmp + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode);
    Print("Expression ↓");
    node.expression.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(unaryExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Unary Node " + tmp + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode);
    Print(indent + "Expression ↓");
    node.expression.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(binaryExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Binary Node " + tmp + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode + ", OpType: " + node.opType);
    Print(indent + "Expression ↓");
    node.lhs.accept(this);
    node.rhs.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(assignExprNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Assign Node " + tmp + " pos: " + node.pos);
    Print(indent + "Expression ↓");
    node.lhs.accept(this);
    node.rhs.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(suiteStmtNode node) {
    int tmp = ++id;
    ++IndentCnt;

    PrintIn("Assign Node " + tmp + " pos: " + node.pos);
    node.stmts.forEach(it -> it.accept(this));
    --IndentCnt;
  }

  @Override
  public void visit(varStmtNode it) {

  }

  @Override
  public void visit(ifStmtNode it) {

  }

  @Override
  public void visit(brkcontNode it) {

  }

  @Override
  public void visit(returnStmtNode it) {

  }

  @Override
  public void visit(whileStmtNode it) {

  }

  @Override
  public void visit(forStmtNode it) {

  }

  @Override
  public void visit(exprStmtNode it) {

  }

}
