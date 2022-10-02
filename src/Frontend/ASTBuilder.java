package Frontend;

import AST.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Utility.Position;
import Utility.Type.BaseType;

// use ANTLR's visitor mode to generate AST
public class ASTBuilder extends MxBaseVisitor<ASTNode> {
  BaseType type;

  @Override
  public ASTNode visitProgram(MxParser.ProgramContext ctx) {
    RootNode root = new RootNode(new Position(ctx));
    ctx.def().forEach((elem) -> {
      root.Defs.add((DefNode) visit(elem)); // 存在一个向下转型
    });
    return root;
  }

//  @Override
//  public ASTNode visitTypeName(MxParser.TypeNameContext ctx) {
//    return ;
//  }

  @Override
  public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
    System.out.println(ctx.Identifier().toString());
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
    fucDefNode fuc = new fucDefNode(ctx.Identifier().toString(), new BaseType(ctx.typeName().toString()), new Position(ctx));

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
