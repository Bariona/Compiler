package frontend;

import ast.ASTVisitor;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import ast.RootNode;

import java.io.PrintStream;

public class ASTPrinter implements ASTVisitor {
  final String indent = "  ";
  private PrintStream out;
  private int IndentCnt;

  public ASTPrinter(PrintStream out) {
    this.out = out;
    IndentCnt = -1;
  }

  private void PrintIn(String str) {
    out.printf("%s---- %s ----\n", indent.repeat(IndentCnt), str);
  }

  private void Print(String str) {
    out.printf("%s%s\n", indent.repeat(IndentCnt), str);
  }

  @Override
  public void visit(RootNode node) {
    ++IndentCnt;

    PrintIn("Root Node " + " position:" + node.pos.toString());
    node.defs.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(VarDefNode node) {
    ++IndentCnt;

    PrintIn("Variable Definitions ↓ " + " position:" + node.pos.toString());
    node.varList.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(VarSingleDefNode node) {
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
  public void visit(ClassDefNode node) {
    ++IndentCnt;

    PrintIn(node.name);
    node.varDefs.forEach(v -> v.accept(this));
    node.funcDefs.forEach(f -> f.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(FuncDefNode node) {
    ++IndentCnt;

    PrintIn("Function Definition: " + node.name + " pos: " + node.pos.toString());
    String paralist = indent;
    for(var p : node.parameterList)
      paralist += p.a.typename() + " " + p.b + ", ";
    Print(paralist);
    node.stmts.accept(this);
    --IndentCnt;
  }

  @Override
  public void visit(AtomExprNode node) {
    ++IndentCnt;

    PrintIn("AtomExpr Node " + " pos: " + node.pos);
    Print(indent + node.atom.getText());

    --IndentCnt;
  }

  @Override
  public void visit(NewExprNode node) {
    ++IndentCnt;

    PrintIn("NewExpr Node " + " pos: " + node.pos);
    Print("  TypeName: " + node.exprType.typename() + ", dimension: " + node.dimensionExpr.size());
    node.dimensionExpr.forEach(d -> d.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(MemberExprNode node) {
    ++IndentCnt;

    PrintIn("MemberExpr Node " + " pos: " + node.pos);
    Print("  Type: " + node.exprType.typename() + " member: " + node.member);
    node.callExpr.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(BracketExprNode node) {
    ++IndentCnt;

    PrintIn("Bracket Node " + " pos: " + node.pos);
    node.callExpr.accept(this);
    node.index.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(FuncExprNode node) {
    ++IndentCnt;

    PrintIn("FunctionCall Node " + " pos: " + node.pos);
    node.callExpr.accept(this);
    Print("ArgumentList");
    node.argumentList.forEach(it -> it.accept(this));

    --IndentCnt;
  }

  @Override
  public void visit(SelfExprNode node) {
    ++IndentCnt;

    PrintIn("SelfADD/SUB Node " + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode);
    Print("Expression ↓");
    node.expression.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(UnaryExprNode node) {
    ++IndentCnt;

    PrintIn("Unary Node " + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode);
    Print(indent + "Expression ↓");
    node.expression.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(BinaryExprNode node) {
    ++IndentCnt;

    PrintIn("Binary Node " + " pos: " + node.pos);
    Print(indent + "opCode: " + node.opCode + ", OpType: " + node.opType);
    Print(indent + "Expression ↓");
    node.lhs.accept(this);
    node.rhs.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(AssignExprNode node) {
    ++IndentCnt;

    PrintIn("Assign Node " + " pos: " + node.pos);
    Print(indent + "Expression ↓");
    node.lhs.accept(this);
    node.rhs.accept(this);

    --IndentCnt;
  }

  @Override
  public void visit(SuiteStmtNode node) {
    ++IndentCnt;
    out.println(indent.repeat(IndentCnt) + "===== Suite Part " + " pos: " + node.pos + " =====");
    node.stmts.forEach(it -> it.accept(this));
    out.println(indent.repeat(IndentCnt) + "====== End ======");
    --IndentCnt;
  }

  @Override
  public void visit(IfStmtNode node) {
    ++IndentCnt;
    PrintIn("If-Else Statement " + " pos: " + node.pos);
    if(node.condition != null) {
      Print(indent + "Condition Expr:");
      node.condition.accept(this);
    }
    if(node.thenStmt != null) {
      Print(indent + "If Expr:");
      node.thenStmt.accept(this);
    }
    if(node.elseStmt != null) {
      Print(indent + "Else Expr:");
      node.elseStmt.accept(this);
    }
    --IndentCnt;
  }

  @Override
  public void visit(BreakContinueNode node) {
    ++IndentCnt;
    if(node.isBreak) {
      out.println(indent.repeat(IndentCnt) + "Break; " + " pos: " + node.pos);
    } else {
      out.println(indent.repeat(IndentCnt) + "Continue; " + " pos: " + node.pos);
    }
    --IndentCnt;
  }

  @Override
  public void visit(ReturnStmtNode node) {
    ++IndentCnt;
    PrintIn("Return " + " pos: " + node.pos);
    if(node.ret != null)
      node.ret.accept(this);
    --IndentCnt;
  }

  @Override
  public void visit(WhileStmtNode node) {
    ++IndentCnt;
    PrintIn("While Statement " + " pos: " + node.pos);
    node.condition.accept(this);
    node.stmt.accept(this);
    --IndentCnt;
  }

  @Override
  public void visit(ForStmtNode node) {
    ++IndentCnt;
    PrintIn("For Statement " + " pos: " + node.pos);
    if(node.initial != null) {
      Print(indent + " Initial:");
      node.initial.accept(this);
    }
    if(node.condition != null) {
      Print(indent + " Condition:");
      node.condition.accept(this);
    }
    if(node.step != null) {
      Print(indent + " Step Expr:");
      node.step.accept(this);
    }
    node.stmt.accept(this);
    --IndentCnt;
  }

//  @Override
//  public void visit(exprStmtNode node) {
//    ++IndentCnt;
//    node.expr.accept(this);
//    --IndentCnt;
//  }

}
