package Parser;

// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MxParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SuiteStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Definition}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If_Else}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_Else(MxParser.If_ElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(MxParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue(MxParser.ContinueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(MxParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Purexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPurexpression(MxParser.PurexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Emptyexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyexpression(MxParser.EmptyexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(MxParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracket(MxParser.BracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefi(MxParser.VarDefiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefi(MxParser.ClassDefiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctDefi(MxParser.FunctDefiContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarTerm(MxParser.VarTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(MxParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcParList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParList(MxParser.FuncParListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintlnStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintlnInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetString(MxParser.GetStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetInt(MxParser.GetIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code toString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToString(MxParser.ToStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrLength}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrLength(MxParser.StrLengthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrSubstr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrSubstr(MxParser.StrSubstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrtoInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrtoInt(MxParser.StrtoIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StrtoASCii}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrtoASCii(MxParser.StrtoASCiiContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfExpr(MxParser.SelfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MxParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Newtype}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewtype(MxParser.NewtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BracketExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketExpr(MxParser.BracketExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AsignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignExpr(MxParser.AsignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(MxParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallFunctionExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunctionExpr(MxParser.CallFunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SystemFunc}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSystemFunc(MxParser.SystemFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(MxParser.PrimaryContext ctx);
}