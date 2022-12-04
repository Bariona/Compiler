package frontend;

import frontend.ast.*;
import frontend.ast.definition.*;
import frontend.ast.expression.*;
import frontend.ast.statement.*;
import utility.error.SemanticError;
import utility.info.FuncInfo;
import utility.info.VarInfo;
import frontend.parser.MxBaseVisitor;
import frontend.parser.MxParser;
import utility.Position;
import utility.type.BaseType;
import utility.type.BaseType.BuiltinType;
import utility.type.VarType;

// use ANTLR's visitor mode to generate AST and Scope (symbol collector)

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
  @Override
  public ASTNode visitProgram(MxParser.ProgramContext ctx) {
    RootNode root = new RootNode(new Position(ctx));
    // Builtin Functions

    root.scope.addItem(
            new FuncInfo("malloc", new VarType(BuiltinType.VOID),
                    new VarInfo("size", new VarType(BuiltinType.INT)))
    );

    root.scope.addItem(
            new FuncInfo("print", new VarType(BuiltinType.VOID),
                    new VarInfo("str", new VarType(BuiltinType.STRING)))
    );
    root.scope.addItem(
            new FuncInfo("println", new VarType(BuiltinType.VOID),
                    new VarInfo("str", new VarType(BuiltinType.STRING)))
    );
    root.scope.addItem(
            new FuncInfo("printInt", new VarType(BuiltinType.VOID),
                    new VarInfo("n", new VarType(BuiltinType.INT)))
    );
    root.scope.addItem(
            new FuncInfo("printlnInt", new VarType(BuiltinType.VOID),
                    new VarInfo("n", new VarType(BuiltinType.INT)))
    );
    root.scope.addItem(new FuncInfo("getString", new VarType(BuiltinType.STRING)));
    root.scope.addItem(new FuncInfo("getInt", new VarType(BuiltinType.INT)));
    root.scope.addItem(
            new FuncInfo("toString", new VarType(BuiltinType.STRING),
                    new VarInfo("n", new VarType(BuiltinType.INT)))
    );

    ctx.def().forEach(elem -> root.defs.add((DefNode) visit(elem)));


    for (DefNode d : root.defs) {
      if (d instanceof FuncDefNode) {
        root.scope.addItem(((FuncDefNode) d).info);
      } else if (d instanceof ClassDefNode) {
        root.scope.addItem(((ClassDefNode) d).info);
      }
    }

    {
      FuncInfo ret = root.scope.queryFuncInfo("main");
      if (ret == null)
        throw new SemanticError("No main() function", root.pos);
      if (!BaseType.isIntType(ret.funcType.retType))
        throw new SemanticError("main() function should be \"int main\"", ret.pos);
      if (!ret.paraListInfo.isEmpty())
        throw new SemanticError("main() function has no parameter", ret.pos);
    }

    return root;
  }

  @Override
  public ASTNode visitSuite(MxParser.SuiteContext ctx) {
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
      node.info.type = new VarType(ctx.typeName(), true);
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
    VarType type;
    if(ctx.typeName() != null) {
      type = new VarType(ctx.typeName(), true);
    } else {
      type = new VarType(BuiltinType.CLASS);
      type.ClassName = ctx.Identifier().toString();
    }
    FuncDefNode func = new FuncDefNode(ctx.Identifier().toString(), type, new Position(ctx));
    if(ctx.typeName() == null)
      func.info.isConstructor = true; // it's a constructor
    if (ctx.parameterList() != null) {
      for (int i = 0; i < ctx.parameterList().Identifier().size(); ++i) {
        VarType argType = new VarType(ctx.parameterList().typeName(i), true);
        func.info.paraListInfo.add(new VarInfo(
                ctx.parameterList().Identifier(i).toString(),
                (VarType) argType.clone(),
                new Position(ctx.parameterList().typeName(i))
        ));
        // ↓ must be added...
        func.info.funcType.paraListType.add((VarType) argType.clone());
      }
    }
    /* suite part */
    func.stmts = (StmtNode) visit(ctx.suite());
    return func;
  }

  @Override
  public ASTNode visitFuncDefinition(MxParser.FuncDefinitionContext ctx) {
    return visit(ctx.functionDef());
  }

  @Override
  public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
    ClassDefNode cls = new ClassDefNode(ctx.Identifier().toString(), new Position(ctx));
    ctx.varDef().forEach((var) -> {
      VarDefNode varList = (VarDefNode) visit(var);
      varList.varList.forEach(v -> {
        cls.varDefs.add(v);
        cls.scope.addItem(v.info);
        cls.info.varInfos.add(v.info);
      });
    });
    ctx.functionDef().forEach((fuc) -> {
      FuncDefNode f = (FuncDefNode) visit(fuc);
      cls.funcDefs.add(f);
      cls.scope.addItem(f.info);
      cls.info.funcInfos.add(f.info);
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

    ForStmtNode forStmtNode = new ForStmtNode(
            ctx.initialExpr == null ? null : (ExprNode) visit(ctx.initialExpr),
            ctx.condiExpr == null ? null : (ExprNode) visit(ctx.condiExpr),
            ctx.stepExpr == null ? null : (ExprNode) visit(ctx.stepExpr),
            (StmtNode) visit(ctx.statement()),
            new Position(ctx)
    );

    if (ctx.varDef() != null) {
      VarDefNode node = (VarDefNode) visit(ctx.varDef());
      for (var def : node.varList)
        forStmtNode.initVarDef.add(def);
    }
    return forStmtNode;
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
  public ASTNode visitParentExpr(MxParser.ParentExprContext ctx) {
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
  public ASTNode visitNewType(MxParser.NewTypeContext ctx) {
    NewExprNode node = new NewExprNode(new VarType(ctx.typeName(), false), new Position(ctx));
    if (BaseType.isVoidType(node.exprType) || BaseType.isNullType(node.exprType))
      throw new SemanticError("New expression error", node.pos);
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
    LambdaExprNode node = new LambdaExprNode((SuiteStmtNode) visit(ctx.suite()), new Position(ctx));

    if (ctx.parameterList() != null) {
      for (int i = 0; i < ctx.parameterList().typeName().size(); ++i) {
        VarInfo varinfo = new VarInfo(
                ctx.parameterList().Identifier(i).toString(),
                new VarType(ctx.parameterList().typeName(i), true),
                new Position(ctx.parameterList().typeName(i))
        );
        node.info.paraListInfo.add(varinfo);
      }
    }

    if (ctx.argumentList() != null)
      ctx.argumentList().expression().forEach(exp -> node.argumentList.add((ExprNode) visit(exp)));

    if (node.argumentList.size() != node.info.paraListInfo.size())
      throw new SemanticError("parameter number not match", node.pos);
    return node;
  }
}
