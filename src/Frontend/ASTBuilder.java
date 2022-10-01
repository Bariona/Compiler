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

  @Override
  public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
    classDefNode cnode = new classDefNode(ctx.Identifier().toString(), new Position(ctx));
    ctx.varDef().forEach((var) -> {

    });
    return cnode;
  }

//  @Override
//  public ASTNode visitVarDefi(MxParser.VarDefiContext ctx) {
//    varDefNode vnode = new varDefNode(new Position(ctx));
//    return vnode;
//  }


  @Override
  public ASTNode visitAsignExpr(MxParser.AsignExprContext ctx) {
    return super.visitAsignExpr(ctx);
  }
}
