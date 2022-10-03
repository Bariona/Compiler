package Frontend;

import AST.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Utility.Position;
import Utility.Type.*;

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
  public ASTNode visitVarDefinition(MxParser.VarDefinitionContext ctx) {
    return visit(ctx.varDef()); // i.e. visitVarDef (procedure: this.visit -> ctx.accept -> visitVarDef)
  }

  @Override
  public ASTNode visitVarTerm(MxParser.VarTermContext ctx) {
    varSingleDefNode var = new varSingleDefNode(ctx.Identifier().toString(), new Position(ctx));
//    System.out.println(ctx.expression() == null);
//    System.exit(0);
    if(ctx.expression() != null) {
      var.expr = (ExprNode) visit(ctx.expression());
    }
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
  public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
    classDefNode cls = new classDefNode(ctx.Identifier().toString(), new Position(ctx));
    ctx.varDef().forEach((var) -> {
      cls.varDefs.add((DefNode) visit(var));
    });
    ctx.functionDef().forEach((fuc) -> {
      cls.fucDefs.add((DefNode) visit(fuc));
    });
    return cls;
  }

  @Override
  public ASTNode visitFunctionDef(MxParser.FunctionDefContext ctx) {
    System.out.println(ctx.Identifier().toString());
    System.exit(0);
    fucDefNode fuc = new fucDefNode(ctx.Identifier().toString(), new BaseType(ctx.typeName()), new Position(ctx));

    return fuc;
  }

  //  @Override
//  public ASTNode visitVarDefi(MxParser.VarDefiContext ctx) {
//    varDefNode vnode = new varDefNode(new Position(ctx));
//    return vnode;
//  }


  @Override
  public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return super.visitAssignExpr(ctx);
  }
}
