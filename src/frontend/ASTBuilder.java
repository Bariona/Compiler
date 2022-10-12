package frontend;

import parser.MxBaseVisitor;
import parser.MxParser;

import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;

import utility.Position;
import utility.type.*;
import org.antlr.v4.runtime.misc.Pair;

// use ANTLR's visitor mode to generate AST and Scope (symbol collector)

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
  @Override
  public ASTNode visitProgram(MxParser.ProgramContext ctx) {
    RootNode root = new RootNode(new Position(ctx));
    root.scope.addItem("print", new FuncType(BaseType.BultinType.VOID, 0), new Position(ctx));
    root.scope.addItem("println", new FuncType(BaseType.BultinType.VOID, 0), new Position(ctx));
    root.scope.addItem("printInt", new FuncType(BaseType.BultinType.VOID, 0), new Position(ctx));
    root.scope.addItem("printlnInt", new FuncType(BaseType.BultinType.VOID, 0), new Position(ctx));
    root.scope.addItem("getString", new FuncType(BaseType.BultinType.STRING, 0), new Position(ctx));
    root.scope.addItem("getInt", new FuncType(BaseType.BultinType.INT, 0), new Position(ctx));
    root.scope.addItem("toString", new FuncType(BaseType.BultinType.STRING, 0), new Position(ctx));

    ctx.def().forEach((elem) -> {
      root.defs.add((DefNode) visit(elem)); // 存在一个向下转型
    });
    return root;
  }

  @Override
  public ASTNode visitSuite(MxParser.SuiteContext ctx) {
    if (ctx.statement().isEmpty())
      return null;
    SuiteStmtNode node = new SuiteStmtNode(new Position(ctx));

    for (var stmt : ctx.statement()) {
      StmtNode tmp = (StmtNode) visit(stmt);
      if (tmp != null) node.stmts.add(tmp); // if是为了去掉EmptyExpr
    }
    return node;
  }

  // ------  Definition Part ------

  @Override
  public ASTNode visitVarTerm(MxParser.VarTermContext ctx) {
    VarSingleDefNode node = new VarSingleDefNode(ctx.Identifier().toString(), new Position(ctx));
    if (ctx.expression() != null) {
      node.expr = (ExprNode) visit(ctx.expression());
    } else node.expr = null;
    return node;
  }

  @Override
  public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
    // a bunch of variables' definitions
    VarDefNode v = new VarDefNode(new Position(ctx));
    for (var vt : ctx.varTerm()) {
      VarSingleDefNode node = (VarSingleDefNode) visitVarTerm(vt);
      node.type = new VarType(ctx.typeName(), true);
      v.varList.add(node);
    }
    return v;
  }

  @Override
  public ASTNode visitVarDefinition(MxParser.VarDefinitionContext ctx) {
    return visit(ctx.varDef()); // i.e. visitVarDef (procedure: this.visit -> ctx.accept -> visitVarDef)
  }

  @Override
  public ASTNode visitFunctionDef(MxParser.FunctionDefContext ctx) {
    FuncDefNode func = new FuncDefNode(ctx.Identifier().toString(), new Position(ctx));
    func.type = (ctx.typeName() != null) ? new FuncType(ctx.typeName()) : null; // 为了处理构造函数
    if (ctx.parameterList() != null) {
      for (int i = 0; i < ctx.parameterList().Identifier().size(); ++i) {
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
    ClassDefNode cls = new ClassDefNode(ctx.Identifier().toString(), new Position(ctx));
    ctx.varDef().forEach((var) -> {
      cls.varDefs.add((VarDefNode) visit(var));
    });
    ctx.functionDef().forEach((fuc) -> {
      cls.funcDefs.add((FuncDefNode) visit(fuc));
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
    if (ctx.statement().size() > 1)
      elseStmt = (StmtNode) visit(ctx.statement(1));
    return new IfStmtNode(condition, thenStmt, elseStmt, new Position(ctx));
  }

  @Override
  public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
    return new BreakContinueNode(true, new Position(ctx));
  }

  @Override
  public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
    return new BreakContinueNode(false, new Position(ctx));
  }

  @Override
  public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
    ExprNode value = null;
    if (ctx.expression() != null)
      value = (ExprNode) visit(ctx.expression());
    return new ReturnStmtNode(value, new Position(ctx));
  }

  @Override
  public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
    return new WhileStmtNode((ExprNode) visit(ctx.expression()), (StmtNode) visit(ctx.statement()), new Position(ctx));
  }

  @Override
  public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
    return new ForStmtNode(
            (ExprNode) visit(ctx.initialExpr),
            (ExprNode) visit(ctx.condiExpr),
            (ExprNode) visit(ctx.stepExpr),
            (StmtNode) visit(ctx.statement()),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitPurExprStmt(MxParser.PurExprStmtContext ctx) {
    return visit(ctx.expression());
  }

  @Override
  public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
    return null;
  }

  // ------ Expression Part -----

  @Override
  public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
    return visit(ctx.expression());
  }

  @Override
  public ASTNode visitSelfExpr(MxParser.SelfExprContext ctx) {
    return new SelfExprNode(ctx.op.getText(), (ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitAtomExpr(MxParser.AtomExprContext ctx) {
    return new AtomExprNode(ctx.primary(), new Position(ctx));
  }

  @Override
  public ASTNode visitNewtype(MxParser.NewtypeContext ctx) {
    NewExprNode node = new NewExprNode(new VarType(ctx.typeName(), false), new Position(ctx));
    for (var dim : ctx.typeName().bracket()) {
      if (dim.expression() != null) {
        node.dimensionExpr.add((ExprNode) visit(dim.expression()));
      } else node.dimensionExpr.add(null);
    }
    return node;
  }

  @Override
  public ASTNode visitMemberExpr(MxParser.MemberExprContext ctx) {
    return new MemberExprNode(
            (ExprNode) visit(ctx.expression()),
            ctx.Identifier().toString(),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitBracketExpr(MxParser.BracketExprContext ctx) {
    return new BracketExprNode(
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitFunctionExpr(MxParser.FunctionExprContext ctx) {
    FuncExprNode node = new FuncExprNode((ExprNode) visit(ctx.expression()), new Position(ctx));
    if (ctx.argumentList() != null) {
      for (var expr : ctx.argumentList().expression()) {
        node.argumentList.add((ExprNode) visit(expr));
      }
    }
    return node;
  }

  @Override
  public ASTNode visitUnaryExpr(MxParser.UnaryExprContext ctx) {
    return new UnaryExprNode(ctx.op.getText(), (ExprNode) visit(ctx.expression()), new Position(ctx));
  }

  @Override
  public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    return new BinaryExprNode(
            (ExprNode) visit(ctx.expression(0)),
            (ExprNode) visit(ctx.expression(1)),
            ctx.op.getText(),
            new Position(ctx)
    );
  }

  @Override
  public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return new AssignExprNode(
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