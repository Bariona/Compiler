package Frontend;

import AST.*;
import AST.Statement.*;
import AST.Expression.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Utility.Position;
import Utility.Type.*;
import org.antlr.v4.runtime.misc.Pair;

// use ANTLR's visitor mode to generate AST
public class ASTBuilder extends MxBaseVisitor<ASTNode> {
  @Override
  public ASTNode visitProgram(MxParser.ProgramContext ctx) {
    RootNode root = new RootNode(new Position(ctx));
    ctx.def().forEach((elem) -> {
      root.Defs.add((DefNode) visit(elem)); // 存在一个向下转型
    });
    return root;
  }

  @Override
  public ASTNode visitSuite(MxParser.SuiteContext ctx) {
    suiteStmtNode node = new suiteStmtNode(new Position(ctx));
    if(!ctx.statement().isEmpty()) {
      for(MxParser.StatementContext stmt : ctx.statement()) {
        StmtNode tmp = (StmtNode) visit(stmt);
        if(tmp != null) node.stmts.add(tmp); // 去掉EmptyExpr
      }
    }
    System.out.println(node.stmts.size());
    return node;
  }

  // ------  Definition Part ------
  @Override
  public ASTNode visitVarTerm(MxParser.VarTermContext ctx) {
    varSingleDefNode node = new varSingleDefNode(ctx.Identifier().toString(), new Position(ctx));
    if(ctx.expression() != null) {
      node.expr = (ExprNode) visit(ctx.expression());
    } else node.expr = null;
    return node;
  }

  @Override
  public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
    // a bunch of variables' definitions
    varDefNode v = new varDefNode(new Position(ctx));
    for(MxParser.VarTermContext vt : ctx.varTerm()) {
      varSingleDefNode node = (varSingleDefNode) visitVarTerm(vt);
      node.type = new VarType(ctx.typeName());
      v.varlist.add(node);
    }
    return v;
  }

  @Override
  public ASTNode visitVarDefinition(MxParser.VarDefinitionContext ctx) {
    return visit(ctx.varDef()); // i.e. visitVarDef (procedure: this.visit -> ctx.accept -> visitVarDef)
  }

  @Override
  public ASTNode visitFunctionDef(MxParser.FunctionDefContext ctx) {
    funcDefNode func = new funcDefNode(ctx.Identifier().toString(), new Position(ctx));
    func.type = (ctx.typeName() != null) ? new FuncType(ctx.typeName()) : null; // 为了处理构造函数
    if(ctx.parameterList() != null) {
      for(int i = 0; i < ctx.parameterList().typeName().size(); ++i) {
//        VarType para = new VarType(ctx.parameterList().typeName(i));
        func.parameterList.add(new Pair<>(
                new VarType(ctx.parameterList().typeName(i)),
                ctx.parameterList().Identifier().toString()
        ));
      }
    }
    /* suite part */
    visit(ctx.suite());
    return func;
  }

  @Override
  public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
    classDefNode cls = new classDefNode(ctx.Identifier().toString(), new Position(ctx));
    ctx.varDef().forEach((var) -> {
      cls.varDefs.add((varDefNode) visit(var));
    });
    ctx.functionDef().forEach((fuc) -> {
      cls.funcDefs.add((funcDefNode) visit(fuc));
    });
    return cls;
  }

  // ------ Statement Part ------
  @Override
  public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
    return null;
  }

  @Override
  public ASTNode visitSuiteStmt(MxParser.SuiteStmtContext ctx) {
//    System.out.println("@@");
    return visit(ctx.suite());
  }

  @Override
  public ASTNode visitDefStmt(MxParser.DefStmtContext ctx) {
    varStmtNode node = new varStmtNode(new Position(ctx));
    node.Defstmt = (varDefNode) visit(ctx.def()); // 一定是VarDEF吗?
    return node;
  }

  @Override
  public ASTNode visitIfElseStmt(MxParser.IfElseStmtContext ctx) {
    StmtNode thenStmt = (StmtNode) visit(ctx.statement(0)), elseStmt = null;
    ExprNode condition = (ExprNode) visit(ctx.expression());
    if(ctx.statement().size() > 1)
      elseStmt = (StmtNode) visit(ctx.statement(1));
    return new ifStmtNode(condition, thenStmt, elseStmt, new Position(ctx));
  }

  @Override
  public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
    ExprNode value = null;
    if(ctx.expression() != null)
      value = (ExprNode) visit(ctx.expression());
    return new returnStmtNode(value, new Position(ctx));
  }

  @Override
  public ASTNode visitPurExprStmt(MxParser.PurExprStmtContext ctx) {
    return new exprStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
    return new whileStmtNode((ExprNode) visit(ctx.expression()), (StmtNode) visit(ctx.statement()), new Position(ctx));
  }

  @Override
  public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
    return new forStmtNode(
            (ExprNode) visit(ctx.initialExpr),
            (ExprNode) visit(ctx.condiExpr),
            (ExprNode) visit(ctx.stepExpr),
            (StmtNode) visit(ctx.statement()),
            new Position(ctx)
    );
  }

  // ------ Expression Part -----
  @Override
  public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    return super.visitBinaryExpr(ctx);
  }

  @Override
  public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return super.visitAssignExpr(ctx);
  }
}
