package Frontend;

import Parser.MxBaseVisitor;
import Parser.MxParser;

import AST.*;
import AST.Definition.*;
import AST.Expression.*;
import AST.Statement.*;

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
      for(var stmt : ctx.statement()) {
        StmtNode tmp = (StmtNode) visit(stmt);
        if(tmp != null) node.stmts.add(tmp); // 去掉EmptyExpr
      }
    }
//    System.out.println(node.stmts.size());
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
    for(var vt : ctx.varTerm()) {
      varSingleDefNode node = (varSingleDefNode) visitVarTerm(vt);
      node.type = new VarType(ctx.typeName(), true);
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
      for(int i = 0; i < ctx.parameterList().Identifier().size(); ++i) {
//        VarType para = new VarType(ctx.parameterList().typeName(i));
        func.parameterList.add(new Pair<>(
                new VarType(ctx.parameterList().typeName(i), true),
                ctx.parameterList().Identifier(i).toString()
        ));
      }
    }
    /* suite part */
    func.stmts = (StmtNode) visit(ctx.suite());
    return func;
  }

  @Override
  public ASTNode visitFunctDefinition(MxParser.FunctDefinitionContext ctx) {
    return visit(ctx.functionDef());
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

  @Override
  public ASTNode visitClassDefinition(MxParser.ClassDefinitionContext ctx) {
    return visit(ctx.classDef());
  }

  // ------ Statement Part ------

  @Override
  public ASTNode visitSuiteStmt(MxParser.SuiteStmtContext ctx) {
    return visit(ctx.suite());
  }

  @Override
  public ASTNode visitDefStmt(MxParser.DefStmtContext ctx) {
//    varStmtNode node = new varStmtNode(new Position(ctx));
//    node.Defstmt = (varDefNode) visit(ctx.def()); // 一定是VarDEF吗?
//    return node;
    return visit(ctx.def());
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
  public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
    return new brkcontNode(true, new Position(ctx));
  }

  @Override
  public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
    return new brkcontNode(false, new Position(ctx));
  }

  @Override
  public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
    ExprNode value = null;
    if(ctx.expression() != null)
      value = (ExprNode) visit(ctx.expression());
    return new returnStmtNode(value, new Position(ctx));
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

  @Override
  public ASTNode visitPurExprStmt(MxParser.PurExprStmtContext ctx) {
    return new exprStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
    return null;
  }

  // ------ Expression Part -----

  // visitSelfExpr() { ... }

  @Override
  public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
    return visit(ctx.expression());
  }

  @Override
  public ASTNode visitSelfExpr(MxParser.SelfExprContext ctx) {
    return new selfExprNode(ctx.op.getText(), (ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
    return new atomExprNode(ctx.primary(), new Position(ctx));
  }

  @Override
  public ASTNode visitNewtype(MxParser.NewtypeContext ctx) {
    newExprNode node = new newExprNode(new VarType(ctx.typeName(), false), new Position(ctx));
    for(var dim : ctx.typeName().bracket()) {
      if(dim.expression() != null) {
        node.DimensionExpr.add((ExprNode) visit(dim.expression()));
      } else node.DimensionExpr.add(null);
    }
    return node;
  }

  @Override
  public ASTNode visitMemberExpr(MxParser.MemberExprContext ctx) {
    return new memberExprNode(
            (ExprNode) visit(ctx.expression()),
            ctx.Identifier().toString(),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitBracketExpr(MxParser.BracketExprContext ctx) {
    return new bracketExprNode(
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitFunctionExpr(MxParser.FunctionExprContext ctx) {
    funcExprNode node = new funcExprNode((ExprNode) visit(ctx.expression()), new Position(ctx));
    if(ctx.argumentList() != null) {
      for(var expr : ctx.argumentList().expression()) {
        node.argumentList.add((ExprNode) visit(expr));
      }
    }
    return node;
  }

  @Override
  public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
    return new unaryExprNode(ctx.op.getText(), (ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
//    String opt = ctx.op.getText();
//    System.out.println(op);
   return new binaryExprNode(
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            ctx.op.getText(),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return new assignExprNode(
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitLambdaExpr(MxParser.LambdaExprContext ctx) {
    return super.visitLambdaExpr(ctx);
  }
}
