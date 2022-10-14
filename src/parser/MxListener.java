package parser;// Generated from java-escape by ANTLR 4.11.1

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link MxParser#program}.
   *
   * @param ctx the parse tree
   */
  void enterProgram(MxParser.ProgramContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#program}.
   *
   * @param ctx the parse tree
   */
  void exitProgram(MxParser.ProgramContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#suite}.
   *
   * @param ctx the parse tree
   */
  void enterSuite(MxParser.SuiteContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#suite}.
   *
   * @param ctx the parse tree
   */
  void exitSuite(MxParser.SuiteContext ctx);

  /**
   * Enter a parse tree produced by the {@code SuiteStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterSuiteStmt(MxParser.SuiteStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code SuiteStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitSuiteStmt(MxParser.SuiteStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code DefStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterDefStmt(MxParser.DefStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code DefStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitDefStmt(MxParser.DefStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code IfElseStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterIfElseStmt(MxParser.IfElseStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code IfElseStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitIfElseStmt(MxParser.IfElseStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code BreakStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterBreakStmt(MxParser.BreakStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code BreakStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitBreakStmt(MxParser.BreakStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code ContinueStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterContinueStmt(MxParser.ContinueStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code ContinueStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitContinueStmt(MxParser.ContinueStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code ReturnStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterReturnStmt(MxParser.ReturnStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code ReturnStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitReturnStmt(MxParser.ReturnStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code WhileStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterWhileStmt(MxParser.WhileStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code WhileStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitWhileStmt(MxParser.WhileStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code ForStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterForStmt(MxParser.ForStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code ForStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitForStmt(MxParser.ForStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code PurExprStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterPurExprStmt(MxParser.PurExprStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code PurExprStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitPurExprStmt(MxParser.PurExprStmtContext ctx);

  /**
   * Enter a parse tree produced by the {@code EmptyStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterEmptyStmt(MxParser.EmptyStmtContext ctx);

  /**
   * Exit a parse tree produced by the {@code EmptyStmt}
   * labeled alternative in {@link MxParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitEmptyStmt(MxParser.EmptyStmtContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#typeName}.
   *
   * @param ctx the parse tree
   */
  void enterTypeName(MxParser.TypeNameContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#typeName}.
   *
   * @param ctx the parse tree
   */
  void exitTypeName(MxParser.TypeNameContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#bracket}.
   *
   * @param ctx the parse tree
   */
  void enterBracket(MxParser.BracketContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#bracket}.
   *
   * @param ctx the parse tree
   */
  void exitBracket(MxParser.BracketContext ctx);

  /**
   * Enter a parse tree produced by the {@code VarDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void enterVarDefinition(MxParser.VarDefinitionContext ctx);

  /**
   * Exit a parse tree produced by the {@code VarDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void exitVarDefinition(MxParser.VarDefinitionContext ctx);

  /**
   * Enter a parse tree produced by the {@code ClassDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void enterClassDefinition(MxParser.ClassDefinitionContext ctx);

  /**
   * Exit a parse tree produced by the {@code ClassDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void exitClassDefinition(MxParser.ClassDefinitionContext ctx);

  /**
   * Enter a parse tree produced by the {@code FunctDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void enterFunctDefinition(MxParser.FunctDefinitionContext ctx);

  /**
   * Exit a parse tree produced by the {@code FunctDefinition}
   * labeled alternative in {@link MxParser#def}.
   *
   * @param ctx the parse tree
   */
  void exitFunctDefinition(MxParser.FunctDefinitionContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#varDef}.
   *
   * @param ctx the parse tree
   */
  void enterVarDef(MxParser.VarDefContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#varDef}.
   *
   * @param ctx the parse tree
   */
  void exitVarDef(MxParser.VarDefContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#varTerm}.
   *
   * @param ctx the parse tree
   */
  void enterVarTerm(MxParser.VarTermContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#varTerm}.
   *
   * @param ctx the parse tree
   */
  void exitVarTerm(MxParser.VarTermContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#functionDef}.
   *
   * @param ctx the parse tree
   */
  void enterFunctionDef(MxParser.FunctionDefContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#functionDef}.
   *
   * @param ctx the parse tree
   */
  void exitFunctionDef(MxParser.FunctionDefContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#parameterList}.
   *
   * @param ctx the parse tree
   */
  void enterParameterList(MxParser.ParameterListContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#parameterList}.
   *
   * @param ctx the parse tree
   */
  void exitParameterList(MxParser.ParameterListContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#classDef}.
   *
   * @param ctx the parse tree
   */
  void enterClassDef(MxParser.ClassDefContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#classDef}.
   *
   * @param ctx the parse tree
   */
  void exitClassDef(MxParser.ClassDefContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#argumentList}.
   *
   * @param ctx the parse tree
   */
  void enterArgumentList(MxParser.ArgumentListContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#argumentList}.
   *
   * @param ctx the parse tree
   */
  void exitArgumentList(MxParser.ArgumentListContext ctx);

  /**
   * Enter a parse tree produced by the {@code SelfExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterSelfExpr(MxParser.SelfExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code SelfExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitSelfExpr(MxParser.SelfExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code FunctionExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterFunctionExpr(MxParser.FunctionExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code FunctionExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitFunctionExpr(MxParser.FunctionExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code MemberExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterMemberExpr(MxParser.MemberExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code MemberExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitMemberExpr(MxParser.MemberExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code LambdaExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterLambdaExpr(MxParser.LambdaExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code LambdaExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitLambdaExpr(MxParser.LambdaExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code BinaryExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterBinaryExpr(MxParser.BinaryExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code BinaryExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitBinaryExpr(MxParser.BinaryExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code Newtype}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterNewtype(MxParser.NewtypeContext ctx);

  /**
   * Exit a parse tree produced by the {@code Newtype}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitNewtype(MxParser.NewtypeContext ctx);

  /**
   * Enter a parse tree produced by the {@code BracketExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterBracketExpr(MxParser.BracketExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code BracketExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitBracketExpr(MxParser.BracketExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code AtomExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterAtomExpr(MxParser.AtomExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code AtomExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitAtomExpr(MxParser.AtomExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code ParenExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterParenExpr(MxParser.ParenExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code ParenExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitParenExpr(MxParser.ParenExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code UnaryExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterUnaryExpr(MxParser.UnaryExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code UnaryExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitUnaryExpr(MxParser.UnaryExprContext ctx);

  /**
   * Enter a parse tree produced by the {@code AssignExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterAssignExpr(MxParser.AssignExprContext ctx);

  /**
   * Exit a parse tree produced by the {@code AssignExpr}
   * labeled alternative in {@link MxParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitAssignExpr(MxParser.AssignExprContext ctx);

  /**
   * Enter a parse tree produced by {@link MxParser#primary}.
   *
   * @param ctx the parse tree
   */
  void enterPrimary(MxParser.PrimaryContext ctx);

  /**
   * Exit a parse tree produced by {@link MxParser#primary}.
   *
   * @param ctx the parse tree
   */
  void exitPrimary(MxParser.PrimaryContext ctx);
}