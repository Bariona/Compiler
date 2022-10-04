package Frontend;

import AST.*;
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
  public ASTNode visitVarTerm(MxParser.VarTermContext ctx) {
    varSingleDefNode var = new varSingleDefNode(ctx.Identifier().toString(), new Position(ctx));
    if(ctx.expression() != null) {
      var.expr = (ExprNode) visit(ctx.expression());
    } else var.expr = null;
    return var;
  }

  @Override
  public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
    // a bunch of variables' definitions
    varDefNode v = new varDefNode(new Position(ctx));
    for(int i = 0; i < ctx.varTerm().size(); ++i) {
      varSingleDefNode node = (varSingleDefNode) visitVarTerm(ctx.varTerm(i));
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
    func.type =  (ctx.typeName() != null) ? new FuncType(ctx.typeName()) : null; // 为了处理构造函数
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

  @Override
  public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    return super.visitBinaryExpr(ctx);
  }

  @Override
  public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return super.visitAssignExpr(ctx);
  }
}
