package Parser;

// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SuiteStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SuiteStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSuiteStmt(MxParser.SuiteStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Definition}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Definition}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(MxParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If_Else}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_Else(MxParser.If_ElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If_Else}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_Else(MxParser.If_ElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Break}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreak(MxParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreak(MxParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinue(MxParser.ContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinue(MxParser.ContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn(MxParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn(MxParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Purexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPurexpression(MxParser.PurexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Purexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPurexpression(MxParser.PurexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Emptyexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyexpression(MxParser.EmptyexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Emptyexpression}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyexpression(MxParser.EmptyexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(MxParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(MxParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(MxParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(MxParser.BracketContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void enterVarDefi(MxParser.VarDefiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void exitVarDefi(MxParser.VarDefiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void enterClassDefi(MxParser.ClassDefiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void exitClassDefi(MxParser.ClassDefiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void enterFunctDefi(MxParser.FunctDefiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctDefi}
	 * labeled alternative in {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void exitFunctDefi(MxParser.FunctDefiContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varTerm}.
	 * @param ctx the parse tree
	 */
	void enterVarTerm(MxParser.VarTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varTerm}.
	 * @param ctx the parse tree
	 */
	void exitVarTerm(MxParser.VarTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(MxParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(MxParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcParList}.
	 * @param ctx the parse tree
	 */
	void enterFuncParList(MxParser.FuncParListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcParList}.
	 * @param ctx the parse tree
	 */
	void exitFuncParList(MxParser.FuncParListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitPrintStr(MxParser.PrintStrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintlnStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintlnStr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStr(MxParser.PrintlnStrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitPrintInt(MxParser.PrintIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintlnInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintlnInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnInt(MxParser.PrintlnIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterGetString(MxParser.GetStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitGetString(MxParser.GetStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterGetInt(MxParser.GetIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitGetInt(MxParser.GetIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterToString(MxParser.ToStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toString}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitToString(MxParser.ToStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrLength}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterStrLength(MxParser.StrLengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrLength}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitStrLength(MxParser.StrLengthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrSubstr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterStrSubstr(MxParser.StrSubstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrSubstr}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitStrSubstr(MxParser.StrSubstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrtoInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterStrtoInt(MxParser.StrtoIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrtoInt}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitStrtoInt(MxParser.StrtoIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrtoASCii}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void enterStrtoASCii(MxParser.StrtoASCiiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrtoASCii}
	 * labeled alternative in {@link MxParser#sysfunction}.
	 * @param ctx the parse tree
	 */
	void exitStrtoASCii(MxParser.StrtoASCiiContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSelfExpr(MxParser.SelfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSelfExpr(MxParser.SelfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Newtype}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewtype(MxParser.NewtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Newtype}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewtype(MxParser.NewtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpr(MxParser.BracketExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpr(MxParser.BracketExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AsignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAsignExpr(MxParser.AsignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AsignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAsignExpr(MxParser.AsignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(MxParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(MxParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallFunctionExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCallFunctionExpr(MxParser.CallFunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallFunctionExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCallFunctionExpr(MxParser.CallFunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SystemFunc}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSystemFunc(MxParser.SystemFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SystemFunc}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSystemFunc(MxParser.SystemFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
}