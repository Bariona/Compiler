// Generated from java-escape by ANTLR 4.11.1
package antlrYx;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YxParser}.
 */
public interface YxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(YxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link YxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(YxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link YxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(YxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link YxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(YxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link YxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(YxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link YxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(YxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock(YxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock(YxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDefinition}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinition(YxParser.VarDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDefinition}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinition(YxParser.VarDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If_Else}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_Else(YxParser.If_ElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If_Else}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_Else(YxParser.If_ElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code return}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn(YxParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code return}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn(YxParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Purexpression}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPurexpression(YxParser.PurexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Purexpression}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPurexpression(YxParser.PurexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Emptyexpression}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyexpression(YxParser.EmptyexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Emptyexpression}
	 * labeled alternative in {@link YxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyexpression(YxParser.EmptyexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(YxParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(YxParser.ArithExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(YxParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(YxParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AsignExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAsignExpr(YxParser.AsignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AsignExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAsignExpr(YxParser.AsignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(YxParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link YxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(YxParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link YxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(YxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link YxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(YxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link YxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(YxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link YxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(YxParser.LiteralContext ctx);
}