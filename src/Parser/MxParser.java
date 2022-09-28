package Parser;

// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, Void=13, Bool=14, Int=15, New=16, Class=17, 
		Null=18, This=19, For=20, While=21, Break=22, Continue=23, True=24, False=25, 
		If=26, Else=27, Return=28, String=29, StringConst=30, ESC=31, Dot=32, 
		LeftParen=33, RightParen=34, LeftBracket=35, RightBracket=36, LeftBrace=37, 
		RightBrace=38, Add=39, Sub=40, Mul=41, Div=42, Mod=43, Less=44, Leq=45, 
		Greater=46, Geq=47, Equal=48, NotEqual=49, LogicAnd=50, LogicOr=51, LogicNot=52, 
		LeftShift=53, RightShift=54, And=55, Or=56, Xor=57, Not=58, Assign=59, 
		SelfAdd=60, SelfSub=61, Semi=62, Colon=63, Question=64, Identifier=65, 
		Decimal=66, WhiteSpace=67, NewLine=68, BlockComment=69, LineComment=70;
	public static final int
		RULE_program = 0, RULE_suite = 1, RULE_statement = 2, RULE_typeName = 3, 
		RULE_bracket = 4, RULE_def = 5, RULE_varDef = 6, RULE_varTerm = 7, RULE_functionDef = 8, 
		RULE_funcParList = 9, RULE_classDef = 10, RULE_sysfunction = 11, RULE_parameterList = 12, 
		RULE_expression = 13, RULE_primary = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "suite", "statement", "typeName", "bracket", "def", "varDef", 
			"varTerm", "functionDef", "funcParList", "classDef", "sysfunction", "parameterList", 
			"expression", "primary"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'print'", "'println'", "'printInt'", "'printlnInt'", "'getString'", 
			"'getInt'", "'toString'", "'length'", "'substring'", "'parseInt'", "'ord'", 
			"'void'", "'bool'", "'int'", "'new'", "'class'", "'null'", "'this'", 
			"'for'", "'while'", "'break'", "'continue'", "'true'", "'false'", "'if'", 
			"'else'", "'return'", "'string'", null, null, "'.'", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'<='", 
			"'>'", "'>='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", 
			"'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "';'", "':'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "Void", "Bool", "Int", "New", "Class", "Null", "This", "For", "While", 
			"Break", "Continue", "True", "False", "If", "Else", "Return", "String", 
			"StringConst", "ESC", "Dot", "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "LeftBrace", "RightBrace", "Add", "Sub", "Mul", "Div", 
			"Mod", "Less", "Leq", "Greater", "Geq", "Equal", "NotEqual", "LogicAnd", 
			"LogicOr", "LogicNot", "LeftShift", "RightShift", "And", "Or", "Xor", 
			"Not", "Assign", "SelfAdd", "SelfSub", "Semi", "Colon", "Question", "Identifier", 
			"Decimal", "WhiteSpace", "NewLine", "BlockComment", "LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & 4503599627436055L) != 0) {
				{
				{
				setState(30);
				def();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(LeftBrace);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 8363185755580792828L) != 0 || _la==Identifier || _la==Decimal) {
				{
				{
				setState(39);
				statement();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SuiteStmtContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public SuiteStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuiteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuiteStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PurexpressionContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public PurexpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPurexpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPurexpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturn(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefinitionContext extends StatementContext {
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public DefinitionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDefinition(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreak(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class If_ElseContext extends StatementContext {
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public If_ElseContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIf_Else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIf_Else(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ContinueContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinue(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext initialExpr;
		public ExpressionContext condiExpr;
		public ExpressionContext stepExpr;
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyexpressionContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public EmptyexpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEmptyexpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEmptyexpression(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new SuiteStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				suite();
				}
				break;
			case 2:
				_localctx = new DefinitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				def();
				}
				break;
			case 3:
				_localctx = new If_ElseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				match(If);
				setState(50);
				match(LeftParen);
				setState(51);
				expression(0);
				setState(52);
				match(RightParen);
				setState(53);
				statement();
				setState(56);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(54);
					match(Else);
					setState(55);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new BreakContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(Break);
				setState(59);
				match(Semi);
				}
				break;
			case 5:
				_localctx = new ContinueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				match(Continue);
				setState(61);
				match(Semi);
				}
				break;
			case 6:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(62);
				match(Return);
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 3751499598826119164L) != 0 || _la==Identifier || _la==Decimal) {
					{
					setState(63);
					expression(0);
					}
				}

				setState(66);
				match(Semi);
				}
				break;
			case 7:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(67);
				match(While);
				setState(68);
				match(LeftParen);
				setState(69);
				expression(0);
				setState(70);
				match(RightParen);
				setState(71);
				statement();
				}
				break;
			case 8:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(73);
				match(For);
				setState(74);
				match(LeftParen);
				setState(79);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(75);
					((ForStmtContext)_localctx).initialExpr = expression(0);
					}
					break;
				case 2:
					{
					setState(76);
					varDef();
					setState(77);
					match(Semi);
					}
					break;
				}
				setState(81);
				match(Semi);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 3751499598826119164L) != 0 || _la==Identifier || _la==Decimal) {
					{
					setState(82);
					((ForStmtContext)_localctx).condiExpr = expression(0);
					}
				}

				setState(85);
				match(Semi);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 3751499598826119164L) != 0 || _la==Identifier || _la==Decimal) {
					{
					setState(86);
					((ForStmtContext)_localctx).stepExpr = expression(0);
					}
				}

				setState(89);
				match(RightParen);
				setState(90);
				statement();
				}
				break;
			case 9:
				_localctx = new PurexpressionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(91);
				expression(0);
				setState(92);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyexpressionContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(94);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<BracketContext> bracket() {
			return getRuleContexts(BracketContext.class);
		}
		public BracketContext bracket(int i) {
			return getRuleContext(BracketContext.class,i);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & 4503599627436039L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(98);
					bracket();
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BracketContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBracket(this);
		}
	}

	public final BracketContext bracket() throws RecognitionException {
		BracketContext _localctx = new BracketContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bracket);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(LeftBracket);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 3751499598826119164L) != 0 || _la==Identifier || _la==Decimal) {
				{
				setState(105);
				expression(0);
				}
			}

			setState(108);
			match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefContext extends ParserRuleContext {
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	 
		public DefContext() { }
		public void copyFrom(DefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctDefiContext extends DefContext {
		public FunctionDefContext functionDef() {
			return getRuleContext(FunctionDefContext.class,0);
		}
		public FunctDefiContext(DefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctDefi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctDefi(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefiContext extends DefContext {
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public ClassDefiContext(DefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDefi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDefi(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDefiContext extends DefContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public VarDefiContext(DefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefi(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_def);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new VarDefiContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				varDef();
				setState(111);
				match(Semi);
				}
				break;
			case 2:
				_localctx = new ClassDefiContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				classDef();
				}
				break;
			case 3:
				_localctx = new FunctDefiContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				functionDef();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<VarTermContext> varTerm() {
			return getRuleContexts(VarTermContext.class);
		}
		public VarTermContext varTerm(int i) {
			return getRuleContext(VarTermContext.class,i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			typeName();
			setState(118);
			varTerm();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(119);
				match(T__0);
				setState(120);
				varTerm();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarTermContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarTerm(this);
		}
	}

	public final VarTermContext varTerm() throws RecognitionException {
		VarTermContext _localctx = new VarTermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(Identifier);
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(127);
				match(Assign);
				setState(128);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public FuncParListContext funcParList() {
			return getRuleContext(FuncParListContext.class,0);
		}
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunctionDef(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(131);
				typeName();
				}
				break;
			}
			setState(134);
			match(Identifier);
			setState(135);
			match(LeftParen);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & 4503599627436039L) != 0) {
				{
				setState(136);
				funcParList();
				}
			}

			setState(139);
			match(RightParen);
			setState(140);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncParListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public FuncParListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncParList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncParList(this);
		}
	}

	public final FuncParListContext funcParList() throws RecognitionException {
		FuncParListContext _localctx = new FuncParListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funcParList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			typeName();
			setState(143);
			match(Identifier);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(144);
				match(T__0);
				setState(145);
				typeName();
				setState(146);
				match(Identifier);
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxParser.RightBrace, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxParser.Semi, i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FunctionDefContext> functionDef() {
			return getRuleContexts(FunctionDefContext.class);
		}
		public FunctionDefContext functionDef(int i) {
			return getRuleContext(FunctionDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(Class);
			setState(154);
			match(Identifier);
			setState(155);
			match(LeftBrace);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & 4503599627436039L) != 0) {
				{
				setState(160);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(156);
					varDef();
					setState(157);
					match(Semi);
					}
					break;
				case 2:
					{
					setState(159);
					functionDef();
					}
					break;
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(RightBrace);
			setState(166);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SysfunctionContext extends ParserRuleContext {
		public SysfunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sysfunction; }
	 
		public SysfunctionContext() { }
		public void copyFrom(SysfunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrtoIntContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StrtoIntContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStrtoInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStrtoInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStrContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public PrintStrContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrintStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrintStr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GetIntContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public GetIntContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterGetInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitGetInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrLengthContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StrLengthContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStrLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStrLength(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintIntContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public PrintIntContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrintInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrintInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToStringContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public ToStringContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterToString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitToString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrtoASCiiContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StrtoASCiiContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStrtoASCii(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStrtoASCii(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GetStringContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public GetStringContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterGetString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitGetString(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintlnIntContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public PrintlnIntContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrintlnInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrintlnInt(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrSubstrContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public StrSubstrContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStrSubstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStrSubstr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintlnStrContext extends SysfunctionContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public PrintlnStrContext(SysfunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrintlnStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrintlnStr(this);
		}
	}

	public final SysfunctionContext sysfunction() throws RecognitionException {
		SysfunctionContext _localctx = new SysfunctionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sysfunction);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				_localctx = new PrintStrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(T__1);
				setState(169);
				match(LeftParen);
				setState(170);
				expression(0);
				setState(171);
				match(RightParen);
				}
				break;
			case T__2:
				_localctx = new PrintlnStrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(T__2);
				setState(174);
				match(LeftParen);
				setState(175);
				expression(0);
				setState(176);
				match(RightParen);
				}
				break;
			case T__3:
				_localctx = new PrintIntContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(T__3);
				setState(179);
				match(LeftParen);
				setState(180);
				expression(0);
				setState(181);
				match(RightParen);
				}
				break;
			case T__4:
				_localctx = new PrintlnIntContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(183);
				match(T__4);
				setState(184);
				match(LeftParen);
				setState(185);
				expression(0);
				setState(186);
				match(RightParen);
				}
				break;
			case T__5:
				_localctx = new GetStringContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(188);
				match(T__5);
				setState(189);
				match(LeftParen);
				setState(190);
				match(RightParen);
				}
				break;
			case T__6:
				_localctx = new GetIntContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(191);
				match(T__6);
				setState(192);
				match(LeftParen);
				setState(193);
				match(RightParen);
				}
				break;
			case T__7:
				_localctx = new ToStringContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(194);
				match(T__7);
				setState(195);
				match(LeftParen);
				setState(196);
				expression(0);
				setState(197);
				match(RightParen);
				}
				break;
			case T__8:
				_localctx = new StrLengthContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(199);
				match(T__8);
				setState(200);
				match(LeftParen);
				setState(201);
				match(RightParen);
				}
				break;
			case T__9:
				_localctx = new StrSubstrContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(202);
				match(T__9);
				setState(203);
				match(LeftParen);
				setState(204);
				expression(0);
				setState(205);
				match(T__0);
				setState(206);
				expression(0);
				setState(207);
				match(RightParen);
				}
				break;
			case T__10:
				_localctx = new StrtoIntContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(209);
				match(T__10);
				setState(210);
				match(LeftParen);
				setState(211);
				match(RightParen);
				}
				break;
			case T__11:
				_localctx = new StrtoASCiiContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(212);
				match(T__11);
				setState(213);
				match(LeftParen);
				setState(214);
				expression(0);
				setState(215);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			expression(0);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(220);
				match(T__0);
				setState(221);
				expression(0);
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfAdd() { return getToken(MxParser.SelfAdd, 0); }
		public TerminalNode SelfSub() { return getToken(MxParser.SelfSub, 0); }
		public SelfExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSelfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSelfExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LeftShift() { return getToken(MxParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode Leq() { return getToken(MxParser.Leq, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode Geq() { return getToken(MxParser.Geq, 0); }
		public TerminalNode Equal() { return getToken(MxParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxParser.NotEqual, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Xor() { return getToken(MxParser.Xor, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public TerminalNode LogicAnd() { return getToken(MxParser.LogicAnd, 0); }
		public TerminalNode LogicOr() { return getToken(MxParser.LogicOr, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBinaryExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewtypeContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public NewtypeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewtype(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BracketExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LeftBracket() { return getToken(MxParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxParser.RightBracket, 0); }
		public BracketExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBracketExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBracketExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AsignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public AsignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAsignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAsignExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExpressionContext {
		public TerminalNode LogicNot() { return getToken(MxParser.LogicNot, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnaryExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallFunctionExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public CallFunctionExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCallFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCallFunctionExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SystemFuncContext extends ExpressionContext {
		public SysfunctionContext sysfunction() {
			return getRuleContext(SysfunctionContext.class,0);
		}
		public SystemFuncContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSystemFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSystemFunc(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case This:
			case True:
			case False:
			case StringConst:
			case LeftParen:
			case Identifier:
			case Decimal:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(228);
				primary();
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
				{
				_localctx = new SystemFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(229);
				sysfunction();
				}
				break;
			case SelfAdd:
			case SelfSub:
				{
				_localctx = new SelfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(230);
				((SelfExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfAdd || _la==SelfSub) ) {
					((SelfExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(231);
				expression(17);
				}
				break;
			case LogicNot:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(232);
				match(LogicNot);
				setState(233);
				expression(15);
				}
				break;
			case Not:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(234);
				match(Not);
				setState(235);
				expression(14);
				}
				break;
			case Sub:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				match(Sub);
				setState(237);
				expression(13);
				}
				break;
			case New:
				{
				_localctx = new NewtypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(238);
				match(New);
				setState(239);
				typeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(293);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(291);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(243);
						match(Dot);
						setState(244);
						expression(21);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(246);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 15393162788864L) != 0) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(247);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(248);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(249);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(250);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(252);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(253);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(255);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 263882790666240L) != 0) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(256);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(257);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(258);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(259);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(260);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(261);
						((BinaryExprContext)_localctx).op = match(And);
						setState(262);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(263);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(264);
						((BinaryExprContext)_localctx).op = match(Xor);
						setState(265);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(266);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(267);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(268);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(269);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(270);
						((BinaryExprContext)_localctx).op = match(LogicAnd);
						setState(271);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(272);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(273);
						((BinaryExprContext)_localctx).op = match(LogicOr);
						setState(274);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new AsignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(275);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(276);
						match(Assign);
						setState(277);
						expression(1);
						}
						break;
					case 13:
						{
						_localctx = new BracketExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(278);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(279);
						match(LeftBracket);
						setState(280);
						expression(0);
						setState(281);
						match(RightBracket);
						}
						break;
					case 14:
						{
						_localctx = new CallFunctionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(283);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(284);
						match(LeftParen);
						setState(286);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((_la) & ~0x3f) == 0 && ((1L << _la) & 3751499598826119164L) != 0 || _la==Identifier || _la==Decimal) {
							{
							setState(285);
							parameterList();
							}
						}

						setState(288);
						match(RightParen);
						}
						break;
					case 15:
						{
						_localctx = new SelfExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(289);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(290);
						((SelfExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfAdd || _la==SelfSub) ) {
							((SelfExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(MxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxParser.RightParen, 0); }
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Decimal() { return getToken(MxParser.Decimal, 0); }
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_primary);
		int _la;
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				match(LeftParen);
				setState(297);
				expression(0);
				setState(298);
				match(RightParen);
				}
				break;
			case This:
				enterOuterAlt(_localctx, 2);
				{
				setState(300);
				match(This);
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 3);
				{
				setState(301);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(302);
				match(Identifier);
				}
				break;
			case Decimal:
				enterOuterAlt(_localctx, 5);
				{
				setState(303);
				match(Decimal);
				}
				break;
			case StringConst:
				enterOuterAlt(_localctx, 6);
				{
				setState(304);
				match(StringConst);
				}
				break;
			case Null:
				enterOuterAlt(_localctx, 7);
				{
				setState(305);
				match(Null);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 20);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001F\u0135\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0005\u0000"+
		" \b\u0000\n\u0000\f\u0000#\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0005\u0001)\b\u0001\n\u0001\f\u0001,\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00029\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002A\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002P\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002T\b\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"X\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002`\b\u0002\u0001\u0003\u0001\u0003\u0005\u0003"+
		"d\b\u0003\n\u0003\f\u0003g\t\u0003\u0001\u0004\u0001\u0004\u0003\u0004"+
		"k\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005t\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006z\b\u0006\n\u0006\f\u0006}\t\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0082\b\u0007\u0001\b"+
		"\u0003\b\u0085\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u008a\b\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u0095"+
		"\b\t\n\t\f\t\u0098\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0005\n\u00a1\b\n\n\n\f\n\u00a4\t\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00da\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00df"+
		"\b\f\n\f\f\f\u00e2\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00f1"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u011f\b\r\u0001\r\u0001\r\u0001\r\u0005\r\u0124\b\r\n\r\f\r\u0127\t"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0133\b\u000e"+
		"\u0001\u000e\u0000\u0001\u001a\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000\b\u0003\u0000\r\u000f"+
		"\u001d\u001dAA\u0001\u0000<=\u0001\u0000)+\u0001\u0000\'(\u0001\u0000"+
		"56\u0001\u0000,/\u0001\u000001\u0001\u0000\u0018\u0019\u0168\u0000!\u0001"+
		"\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u0004_\u0001\u0000\u0000"+
		"\u0000\u0006a\u0001\u0000\u0000\u0000\bh\u0001\u0000\u0000\u0000\ns\u0001"+
		"\u0000\u0000\u0000\fu\u0001\u0000\u0000\u0000\u000e~\u0001\u0000\u0000"+
		"\u0000\u0010\u0084\u0001\u0000\u0000\u0000\u0012\u008e\u0001\u0000\u0000"+
		"\u0000\u0014\u0099\u0001\u0000\u0000\u0000\u0016\u00d9\u0001\u0000\u0000"+
		"\u0000\u0018\u00db\u0001\u0000\u0000\u0000\u001a\u00f0\u0001\u0000\u0000"+
		"\u0000\u001c\u0132\u0001\u0000\u0000\u0000\u001e \u0003\n\u0005\u0000"+
		"\u001f\u001e\u0001\u0000\u0000\u0000 #\u0001\u0000\u0000\u0000!\u001f"+
		"\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000\u0000"+
		"\u0000#!\u0001\u0000\u0000\u0000$%\u0005\u0000\u0000\u0001%\u0001\u0001"+
		"\u0000\u0000\u0000&*\u0005%\u0000\u0000\')\u0003\u0004\u0002\u0000(\'"+
		"\u0001\u0000\u0000\u0000),\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000"+
		"\u0000*+\u0001\u0000\u0000\u0000+-\u0001\u0000\u0000\u0000,*\u0001\u0000"+
		"\u0000\u0000-.\u0005&\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/`\u0003"+
		"\u0002\u0001\u00000`\u0003\n\u0005\u000012\u0005\u001a\u0000\u000023\u0005"+
		"!\u0000\u000034\u0003\u001a\r\u000045\u0005\"\u0000\u000058\u0003\u0004"+
		"\u0002\u000067\u0005\u001b\u0000\u000079\u0003\u0004\u0002\u000086\u0001"+
		"\u0000\u0000\u000089\u0001\u0000\u0000\u00009`\u0001\u0000\u0000\u0000"+
		":;\u0005\u0016\u0000\u0000;`\u0005>\u0000\u0000<=\u0005\u0017\u0000\u0000"+
		"=`\u0005>\u0000\u0000>@\u0005\u001c\u0000\u0000?A\u0003\u001a\r\u0000"+
		"@?\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000"+
		"\u0000B`\u0005>\u0000\u0000CD\u0005\u0015\u0000\u0000DE\u0005!\u0000\u0000"+
		"EF\u0003\u001a\r\u0000FG\u0005\"\u0000\u0000GH\u0003\u0004\u0002\u0000"+
		"H`\u0001\u0000\u0000\u0000IJ\u0005\u0014\u0000\u0000JO\u0005!\u0000\u0000"+
		"KP\u0003\u001a\r\u0000LM\u0003\f\u0006\u0000MN\u0005>\u0000\u0000NP\u0001"+
		"\u0000\u0000\u0000OK\u0001\u0000\u0000\u0000OL\u0001\u0000\u0000\u0000"+
		"OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QS\u0005>\u0000\u0000"+
		"RT\u0003\u001a\r\u0000SR\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UW\u0005>\u0000\u0000VX\u0003\u001a\r\u0000"+
		"WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000"+
		"\u0000YZ\u0005\"\u0000\u0000Z`\u0003\u0004\u0002\u0000[\\\u0003\u001a"+
		"\r\u0000\\]\u0005>\u0000\u0000]`\u0001\u0000\u0000\u0000^`\u0005>\u0000"+
		"\u0000_/\u0001\u0000\u0000\u0000_0\u0001\u0000\u0000\u0000_1\u0001\u0000"+
		"\u0000\u0000_:\u0001\u0000\u0000\u0000_<\u0001\u0000\u0000\u0000_>\u0001"+
		"\u0000\u0000\u0000_C\u0001\u0000\u0000\u0000_I\u0001\u0000\u0000\u0000"+
		"_[\u0001\u0000\u0000\u0000_^\u0001\u0000\u0000\u0000`\u0005\u0001\u0000"+
		"\u0000\u0000ae\u0007\u0000\u0000\u0000bd\u0003\b\u0004\u0000cb\u0001\u0000"+
		"\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001"+
		"\u0000\u0000\u0000f\u0007\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000"+
		"\u0000hj\u0005#\u0000\u0000ik\u0003\u001a\r\u0000ji\u0001\u0000\u0000"+
		"\u0000jk\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lm\u0005$\u0000"+
		"\u0000m\t\u0001\u0000\u0000\u0000no\u0003\f\u0006\u0000op\u0005>\u0000"+
		"\u0000pt\u0001\u0000\u0000\u0000qt\u0003\u0014\n\u0000rt\u0003\u0010\b"+
		"\u0000sn\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000sr\u0001\u0000"+
		"\u0000\u0000t\u000b\u0001\u0000\u0000\u0000uv\u0003\u0006\u0003\u0000"+
		"v{\u0003\u000e\u0007\u0000wx\u0005\u0001\u0000\u0000xz\u0003\u000e\u0007"+
		"\u0000yw\u0001\u0000\u0000\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|\r\u0001\u0000\u0000\u0000}{\u0001"+
		"\u0000\u0000\u0000~\u0081\u0005A\u0000\u0000\u007f\u0080\u0005;\u0000"+
		"\u0000\u0080\u0082\u0003\u001a\r\u0000\u0081\u007f\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u000f\u0001\u0000\u0000\u0000"+
		"\u0083\u0085\u0003\u0006\u0003\u0000\u0084\u0083\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0005A\u0000\u0000\u0087\u0089\u0005!\u0000\u0000\u0088\u008a"+
		"\u0003\u0012\t\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u0089\u008a\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0005"+
		"\"\u0000\u0000\u008c\u008d\u0003\u0002\u0001\u0000\u008d\u0011\u0001\u0000"+
		"\u0000\u0000\u008e\u008f\u0003\u0006\u0003\u0000\u008f\u0096\u0005A\u0000"+
		"\u0000\u0090\u0091\u0005\u0001\u0000\u0000\u0091\u0092\u0003\u0006\u0003"+
		"\u0000\u0092\u0093\u0005A\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000"+
		"\u0094\u0090\u0001\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000"+
		"\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0013\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0005\u0011\u0000\u0000\u009a\u009b\u0005A\u0000\u0000\u009b"+
		"\u00a2\u0005%\u0000\u0000\u009c\u009d\u0003\f\u0006\u0000\u009d\u009e"+
		"\u0005>\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u00a1\u0003"+
		"\u0010\b\u0000\u00a0\u009c\u0001\u0000\u0000\u0000\u00a0\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005&\u0000"+
		"\u0000\u00a6\u00a7\u0005>\u0000\u0000\u00a7\u0015\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005\u0002\u0000\u0000\u00a9\u00aa\u0005!\u0000\u0000\u00aa"+
		"\u00ab\u0003\u001a\r\u0000\u00ab\u00ac\u0005\"\u0000\u0000\u00ac\u00da"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\u0003\u0000\u0000\u00ae\u00af"+
		"\u0005!\u0000\u0000\u00af\u00b0\u0003\u001a\r\u0000\u00b0\u00b1\u0005"+
		"\"\u0000\u0000\u00b1\u00da\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0004"+
		"\u0000\u0000\u00b3\u00b4\u0005!\u0000\u0000\u00b4\u00b5\u0003\u001a\r"+
		"\u0000\u00b5\u00b6\u0005\"\u0000\u0000\u00b6\u00da\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005\u0005\u0000\u0000\u00b8\u00b9\u0005!\u0000\u0000\u00b9"+
		"\u00ba\u0003\u001a\r\u0000\u00ba\u00bb\u0005\"\u0000\u0000\u00bb\u00da"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u0006\u0000\u0000\u00bd\u00be"+
		"\u0005!\u0000\u0000\u00be\u00da\u0005\"\u0000\u0000\u00bf\u00c0\u0005"+
		"\u0007\u0000\u0000\u00c0\u00c1\u0005!\u0000\u0000\u00c1\u00da\u0005\""+
		"\u0000\u0000\u00c2\u00c3\u0005\b\u0000\u0000\u00c3\u00c4\u0005!\u0000"+
		"\u0000\u00c4\u00c5\u0003\u001a\r\u0000\u00c5\u00c6\u0005\"\u0000\u0000"+
		"\u00c6\u00da\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\t\u0000\u0000\u00c8"+
		"\u00c9\u0005!\u0000\u0000\u00c9\u00da\u0005\"\u0000\u0000\u00ca\u00cb"+
		"\u0005\n\u0000\u0000\u00cb\u00cc\u0005!\u0000\u0000\u00cc\u00cd\u0003"+
		"\u001a\r\u0000\u00cd\u00ce\u0005\u0001\u0000\u0000\u00ce\u00cf\u0003\u001a"+
		"\r\u0000\u00cf\u00d0\u0005\"\u0000\u0000\u00d0\u00da\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d2\u0005\u000b\u0000\u0000\u00d2\u00d3\u0005!\u0000\u0000"+
		"\u00d3\u00da\u0005\"\u0000\u0000\u00d4\u00d5\u0005\f\u0000\u0000\u00d5"+
		"\u00d6\u0005!\u0000\u0000\u00d6\u00d7\u0003\u001a\r\u0000\u00d7\u00d8"+
		"\u0005\"\u0000\u0000\u00d8\u00da\u0001\u0000\u0000\u0000\u00d9\u00a8\u0001"+
		"\u0000\u0000\u0000\u00d9\u00ad\u0001\u0000\u0000\u0000\u00d9\u00b2\u0001"+
		"\u0000\u0000\u0000\u00d9\u00b7\u0001\u0000\u0000\u0000\u00d9\u00bc\u0001"+
		"\u0000\u0000\u0000\u00d9\u00bf\u0001\u0000\u0000\u0000\u00d9\u00c2\u0001"+
		"\u0000\u0000\u0000\u00d9\u00c7\u0001\u0000\u0000\u0000\u00d9\u00ca\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d1\u0001\u0000\u0000\u0000\u00d9\u00d4\u0001"+
		"\u0000\u0000\u0000\u00da\u0017\u0001\u0000\u0000\u0000\u00db\u00e0\u0003"+
		"\u001a\r\u0000\u00dc\u00dd\u0005\u0001\u0000\u0000\u00dd\u00df\u0003\u001a"+
		"\r\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e1\u0019\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0006\r\uffff\uffff\u0000\u00e4\u00f1\u0003\u001c\u000e"+
		"\u0000\u00e5\u00f1\u0003\u0016\u000b\u0000\u00e6\u00e7\u0007\u0001\u0000"+
		"\u0000\u00e7\u00f1\u0003\u001a\r\u0011\u00e8\u00e9\u00054\u0000\u0000"+
		"\u00e9\u00f1\u0003\u001a\r\u000f\u00ea\u00eb\u0005:\u0000\u0000\u00eb"+
		"\u00f1\u0003\u001a\r\u000e\u00ec\u00ed\u0005(\u0000\u0000\u00ed\u00f1"+
		"\u0003\u001a\r\r\u00ee\u00ef\u0005\u0010\u0000\u0000\u00ef\u00f1\u0003"+
		"\u0006\u0003\u0000\u00f0\u00e3\u0001\u0000\u0000\u0000\u00f0\u00e5\u0001"+
		"\u0000\u0000\u0000\u00f0\u00e6\u0001\u0000\u0000\u0000\u00f0\u00e8\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ea\u0001\u0000\u0000\u0000\u00f0\u00ec\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1\u0125\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\n\u0014\u0000\u0000\u00f3\u00f4\u0005 "+
		"\u0000\u0000\u00f4\u0124\u0003\u001a\r\u0015\u00f5\u00f6\n\u000b\u0000"+
		"\u0000\u00f6\u00f7\u0007\u0002\u0000\u0000\u00f7\u0124\u0003\u001a\r\f"+
		"\u00f8\u00f9\n\n\u0000\u0000\u00f9\u00fa\u0007\u0003\u0000\u0000\u00fa"+
		"\u0124\u0003\u001a\r\u000b\u00fb\u00fc\n\t\u0000\u0000\u00fc\u00fd\u0007"+
		"\u0004\u0000\u0000\u00fd\u0124\u0003\u001a\r\n\u00fe\u00ff\n\b\u0000\u0000"+
		"\u00ff\u0100\u0007\u0005\u0000\u0000\u0100\u0124\u0003\u001a\r\t\u0101"+
		"\u0102\n\u0007\u0000\u0000\u0102\u0103\u0007\u0006\u0000\u0000\u0103\u0124"+
		"\u0003\u001a\r\b\u0104\u0105\n\u0006\u0000\u0000\u0105\u0106\u00057\u0000"+
		"\u0000\u0106\u0124\u0003\u001a\r\u0007\u0107\u0108\n\u0005\u0000\u0000"+
		"\u0108\u0109\u00059\u0000\u0000\u0109\u0124\u0003\u001a\r\u0006\u010a"+
		"\u010b\n\u0004\u0000\u0000\u010b\u010c\u00058\u0000\u0000\u010c\u0124"+
		"\u0003\u001a\r\u0005\u010d\u010e\n\u0003\u0000\u0000\u010e\u010f\u0005"+
		"2\u0000\u0000\u010f\u0124\u0003\u001a\r\u0004\u0110\u0111\n\u0002\u0000"+
		"\u0000\u0111\u0112\u00053\u0000\u0000\u0112\u0124\u0003\u001a\r\u0003"+
		"\u0113\u0114\n\u0001\u0000\u0000\u0114\u0115\u0005;\u0000\u0000\u0115"+
		"\u0124\u0003\u001a\r\u0001\u0116\u0117\n\u0013\u0000\u0000\u0117\u0118"+
		"\u0005#\u0000\u0000\u0118\u0119\u0003\u001a\r\u0000\u0119\u011a\u0005"+
		"$\u0000\u0000\u011a\u0124\u0001\u0000\u0000\u0000\u011b\u011c\n\u0012"+
		"\u0000\u0000\u011c\u011e\u0005!\u0000\u0000\u011d\u011f\u0003\u0018\f"+
		"\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000"+
		"\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120\u0124\u0005\"\u0000\u0000"+
		"\u0121\u0122\n\u0010\u0000\u0000\u0122\u0124\u0007\u0001\u0000\u0000\u0123"+
		"\u00f2\u0001\u0000\u0000\u0000\u0123\u00f5\u0001\u0000\u0000\u0000\u0123"+
		"\u00f8\u0001\u0000\u0000\u0000\u0123\u00fb\u0001\u0000\u0000\u0000\u0123"+
		"\u00fe\u0001\u0000\u0000\u0000\u0123\u0101\u0001\u0000\u0000\u0000\u0123"+
		"\u0104\u0001\u0000\u0000\u0000\u0123\u0107\u0001\u0000\u0000\u0000\u0123"+
		"\u010a\u0001\u0000\u0000\u0000\u0123\u010d\u0001\u0000\u0000\u0000\u0123"+
		"\u0110\u0001\u0000\u0000\u0000\u0123\u0113\u0001\u0000\u0000\u0000\u0123"+
		"\u0116\u0001\u0000\u0000\u0000\u0123\u011b\u0001\u0000\u0000\u0000\u0123"+
		"\u0121\u0001\u0000\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125"+
		"\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126"+
		"\u001b\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128"+
		"\u0129\u0005!\u0000\u0000\u0129\u012a\u0003\u001a\r\u0000\u012a\u012b"+
		"\u0005\"\u0000\u0000\u012b\u0133\u0001\u0000\u0000\u0000\u012c\u0133\u0005"+
		"\u0013\u0000\u0000\u012d\u0133\u0007\u0007\u0000\u0000\u012e\u0133\u0005"+
		"A\u0000\u0000\u012f\u0133\u0005B\u0000\u0000\u0130\u0133\u0005\u001e\u0000"+
		"\u0000\u0131\u0133\u0005\u0012\u0000\u0000\u0132\u0128\u0001\u0000\u0000"+
		"\u0000\u0132\u012c\u0001\u0000\u0000\u0000\u0132\u012d\u0001\u0000\u0000"+
		"\u0000\u0132\u012e\u0001\u0000\u0000\u0000\u0132\u012f\u0001\u0000\u0000"+
		"\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132\u0131\u0001\u0000\u0000"+
		"\u0000\u0133\u001d\u0001\u0000\u0000\u0000\u0019!*8@OSW_ejs{\u0081\u0084"+
		"\u0089\u0096\u00a0\u00a2\u00d9\u00e0\u00f0\u011e\u0123\u0125\u0132";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}