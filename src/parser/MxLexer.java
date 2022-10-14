package parser;// Generated from java-escape by ANTLR 4.11.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MxLexer extends Lexer {
  static {
    RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
          new PredictionContextCache();
  public static final int
          T__0 = 1, Void = 2, Bool = 3, Int = 4, New = 5, Class = 6, Null = 7, This = 8, For = 9,
          While = 10, Break = 11, Continue = 12, True = 13, False = 14, If = 15, Else = 16, Return = 17,
          String = 18, StringConst = 19, ESC = 20, Dot = 21, LeftParen = 22, RightParen = 23,
          LeftBracket = 24, RightBracket = 25, LeftBrace = 26, RightBrace = 27, Add = 28,
          Sub = 29, Mul = 30, Div = 31, Mod = 32, Arrow = 33, Less = 34, Leq = 35, Greater = 36,
          Geq = 37, Equal = 38, NotEqual = 39, LogicAnd = 40, LogicOr = 41, LogicNot = 42, LeftShift = 43,
          RightShift = 44, And = 45, Or = 46, Xor = 47, Not = 48, Assign = 49, SelfAdd = 50, SelfSub = 51,
          Semi = 52, Colon = 53, Question = 54, Identifier = 55, Decimal = 56, WhiteSpace = 57,
          NewLine = 58, BlockComment = 59, LineComment = 60;
  public static String[] channelNames = {
          "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
  };

  public static String[] modeNames = {
          "DEFAULT_MODE"
  };

  private static String[] makeRuleNames() {
    return new String[]{
            "T__0", "Void", "Bool", "Int", "New", "Class", "Null", "This", "For",
            "While", "Break", "Continue", "True", "False", "If", "Else", "Return",
            "String", "StringConst", "ESC", "Dot", "LeftParen", "RightParen", "LeftBracket",
            "RightBracket", "LeftBrace", "RightBrace", "Add", "Sub", "Mul", "Div",
            "Mod", "Arrow", "Less", "Leq", "Greater", "Geq", "Equal", "NotEqual",
            "LogicAnd", "LogicOr", "LogicNot", "LeftShift", "RightShift", "And",
            "Or", "Xor", "Not", "Assign", "SelfAdd", "SelfSub", "Semi", "Colon",
            "Question", "Identifier", "Decimal", "WhiteSpace", "NewLine", "BlockComment",
            "LineComment"
    };
  }

  public static final String[] ruleNames = makeRuleNames();

  private static String[] makeLiteralNames() {
    return new String[]{
            null, "','", "'void'", "'bool'", "'int'", "'new'", "'class'", "'null'",
            "'this'", "'for'", "'while'", "'break'", "'continue'", "'true'", "'false'",
            "'if'", "'else'", "'return'", "'string'", null, null, "'.'", "'('", "')'",
            "'['", "']'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'%'", "'->'",
            "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", "'||'", "'!'",
            "'<<'", "'>>'", "'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "';'",
            "':'", "'?'"
    };
  }

  private static final String[] _LITERAL_NAMES = makeLiteralNames();

  private static String[] makeSymbolicNames() {
    return new String[]{
            null, null, "Void", "Bool", "Int", "New", "Class", "Null", "This", "For",
            "While", "Break", "Continue", "True", "False", "If", "Else", "Return",
            "String", "StringConst", "ESC", "Dot", "LeftParen", "RightParen", "LeftBracket",
            "RightBracket", "LeftBrace", "RightBrace", "Add", "Sub", "Mul", "Div",
            "Mod", "Arrow", "Less", "Leq", "Greater", "Geq", "Equal", "NotEqual",
            "LogicAnd", "LogicOr", "LogicNot", "LeftShift", "RightShift", "And",
            "Or", "Xor", "Not", "Assign", "SelfAdd", "SelfSub", "Semi", "Colon",
            "Question", "Identifier", "Decimal", "WhiteSpace", "NewLine", "BlockComment",
            "LineComment"
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


  public MxLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "Mx.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN =
          "\u0004\u0000<\u0170\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
                  "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
                  "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
                  "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
                  "\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002" +
                  "\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002" +
                  "\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002" +
                  "\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002" +
                  "\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002" +
                  "\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002" +
                  "\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007" +
                  "!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007" +
                  "&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007" +
                  "+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007" +
                  "0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007" +
                  "5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007" +
                  ":\u0002;\u0007;\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001" +
                  "\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                  "\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001" +
                  "\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001" +
                  "\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001" +
                  "\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001" +
                  "\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001" +
                  "\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
                  "\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b" +
                  "\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                  "\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e" +
                  "\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f" +
                  "\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                  "\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011" +
                  "\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012" +
                  "\u00db\b\u0012\n\u0012\f\u0012\u00de\t\u0012\u0001\u0012\u0001\u0012\u0001" +
                  "\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00e6\b\u0013\u0001" +
                  "\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001" +
                  "\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001" +
                  "\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001" +
                  "\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001" +
                  " \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001" +
                  "$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001" +
                  "\'\u0001\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001*\u0001*\u0001*\u0001" +
                  "+\u0001+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001/\u0001" +
                  "/\u00010\u00010\u00011\u00011\u00011\u00012\u00012\u00012\u00013\u0001" +
                  "3\u00014\u00014\u00015\u00015\u00016\u00016\u00056\u0139\b6\n6\f6\u013c" +
                  "\t6\u00017\u00017\u00057\u0140\b7\n7\f7\u0143\t7\u00017\u00037\u0146\b" +
                  "7\u00018\u00018\u00018\u00018\u00019\u00019\u00019\u00039\u014f\b9\u0001" +
                  "9\u00019\u0001:\u0001:\u0001:\u0001:\u0005:\u0157\b:\n:\f:\u015a\t:\u0001" +
                  ":\u0001:\u0001:\u0001:\u0001:\u0001;\u0001;\u0001;\u0001;\u0005;\u0165" +
                  "\b;\n;\f;\u0168\t;\u0001;\u0003;\u016b\b;\u0001;\u0001;\u0001;\u0001;" +
                  "\u0003\u00dc\u0158\u0166\u0000<\u0001\u0001\u0003\u0002\u0005\u0003\u0007" +
                  "\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b" +
                  "\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013" +
                  "\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d" +
                  ";\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s" +
                  ":u;w<\u0001\u0000\u0005\u0002\u0000AZaz\u0004\u000009AZ__az\u0001\u0000" +
                  "19\u0001\u000009\u0002\u0000\t\t  \u0179\u0000\u0001\u0001\u0000\u0000" +
                  "\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000" +
                  "\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000" +
                  "\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000" +
                  "\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000" +
                  "\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000" +
                  "\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000" +
                  "\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000" +
                  "\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001" +
                  "\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000" +
                  "\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000" +
                  "\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001" +
                  "\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000" +
                  "\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000" +
                  "\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?" +
                  "\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000" +
                  "\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000" +
                  "\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M" +
                  "\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000" +
                  "\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000" +
                  "\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[" +
                  "\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000" +
                  "\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000" +
                  "\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i" +
                  "\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000" +
                  "\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000" +
                  "\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w" +
                  "\u0001\u0000\u0000\u0000\u0001y\u0001\u0000\u0000\u0000\u0003{\u0001\u0000" +
                  "\u0000\u0000\u0005\u0080\u0001\u0000\u0000\u0000\u0007\u0085\u0001\u0000" +
                  "\u0000\u0000\t\u0089\u0001\u0000\u0000\u0000\u000b\u008d\u0001\u0000\u0000" +
                  "\u0000\r\u0093\u0001\u0000\u0000\u0000\u000f\u0098\u0001\u0000\u0000\u0000" +
                  "\u0011\u009d\u0001\u0000\u0000\u0000\u0013\u00a1\u0001\u0000\u0000\u0000" +
                  "\u0015\u00a7\u0001\u0000\u0000\u0000\u0017\u00ad\u0001\u0000\u0000\u0000" +
                  "\u0019\u00b6\u0001\u0000\u0000\u0000\u001b\u00bb\u0001\u0000\u0000\u0000" +
                  "\u001d\u00c1\u0001\u0000\u0000\u0000\u001f\u00c4\u0001\u0000\u0000\u0000" +
                  "!\u00c9\u0001\u0000\u0000\u0000#\u00d0\u0001\u0000\u0000\u0000%\u00d7" +
                  "\u0001\u0000\u0000\u0000\'\u00e5\u0001\u0000\u0000\u0000)\u00e7\u0001" +
                  "\u0000\u0000\u0000+\u00e9\u0001\u0000\u0000\u0000-\u00eb\u0001\u0000\u0000" +
                  "\u0000/\u00ed\u0001\u0000\u0000\u00001\u00ef\u0001\u0000\u0000\u00003" +
                  "\u00f1\u0001\u0000\u0000\u00005\u00f3\u0001\u0000\u0000\u00007\u00f5\u0001" +
                  "\u0000\u0000\u00009\u00f7\u0001\u0000\u0000\u0000;\u00f9\u0001\u0000\u0000" +
                  "\u0000=\u00fb\u0001\u0000\u0000\u0000?\u00fd\u0001\u0000\u0000\u0000A" +
                  "\u00ff\u0001\u0000\u0000\u0000C\u0102\u0001\u0000\u0000\u0000E\u0104\u0001" +
                  "\u0000\u0000\u0000G\u0107\u0001\u0000\u0000\u0000I\u0109\u0001\u0000\u0000" +
                  "\u0000K\u010c\u0001\u0000\u0000\u0000M\u010f\u0001\u0000\u0000\u0000O" +
                  "\u0112\u0001\u0000\u0000\u0000Q\u0115\u0001\u0000\u0000\u0000S\u0118\u0001" +
                  "\u0000\u0000\u0000U\u011a\u0001\u0000\u0000\u0000W\u011d\u0001\u0000\u0000" +
                  "\u0000Y\u0120\u0001\u0000\u0000\u0000[\u0122\u0001\u0000\u0000\u0000]" +
                  "\u0124\u0001\u0000\u0000\u0000_\u0126\u0001\u0000\u0000\u0000a\u0128\u0001" +
                  "\u0000\u0000\u0000c\u012a\u0001\u0000\u0000\u0000e\u012d\u0001\u0000\u0000" +
                  "\u0000g\u0130\u0001\u0000\u0000\u0000i\u0132\u0001\u0000\u0000\u0000k" +
                  "\u0134\u0001\u0000\u0000\u0000m\u0136\u0001\u0000\u0000\u0000o\u0145\u0001" +
                  "\u0000\u0000\u0000q\u0147\u0001\u0000\u0000\u0000s\u014e\u0001\u0000\u0000" +
                  "\u0000u\u0152\u0001\u0000\u0000\u0000w\u0160\u0001\u0000\u0000\u0000y" +
                  "z\u0005,\u0000\u0000z\u0002\u0001\u0000\u0000\u0000{|\u0005v\u0000\u0000" +
                  "|}\u0005o\u0000\u0000}~\u0005i\u0000\u0000~\u007f\u0005d\u0000\u0000\u007f" +
                  "\u0004\u0001\u0000\u0000\u0000\u0080\u0081\u0005b\u0000\u0000\u0081\u0082" +
                  "\u0005o\u0000\u0000\u0082\u0083\u0005o\u0000\u0000\u0083\u0084\u0005l" +
                  "\u0000\u0000\u0084\u0006\u0001\u0000\u0000\u0000\u0085\u0086\u0005i\u0000" +
                  "\u0000\u0086\u0087\u0005n\u0000\u0000\u0087\u0088\u0005t\u0000\u0000\u0088" +
                  "\b\u0001\u0000\u0000\u0000\u0089\u008a\u0005n\u0000\u0000\u008a\u008b" +
                  "\u0005e\u0000\u0000\u008b\u008c\u0005w\u0000\u0000\u008c\n\u0001\u0000" +
                  "\u0000\u0000\u008d\u008e\u0005c\u0000\u0000\u008e\u008f\u0005l\u0000\u0000" +
                  "\u008f\u0090\u0005a\u0000\u0000\u0090\u0091\u0005s\u0000\u0000\u0091\u0092" +
                  "\u0005s\u0000\u0000\u0092\f\u0001\u0000\u0000\u0000\u0093\u0094\u0005" +
                  "n\u0000\u0000\u0094\u0095\u0005u\u0000\u0000\u0095\u0096\u0005l\u0000" +
                  "\u0000\u0096\u0097\u0005l\u0000\u0000\u0097\u000e\u0001\u0000\u0000\u0000" +
                  "\u0098\u0099\u0005t\u0000\u0000\u0099\u009a\u0005h\u0000\u0000\u009a\u009b" +
                  "\u0005i\u0000\u0000\u009b\u009c\u0005s\u0000\u0000\u009c\u0010\u0001\u0000" +
                  "\u0000\u0000\u009d\u009e\u0005f\u0000\u0000\u009e\u009f\u0005o\u0000\u0000" +
                  "\u009f\u00a0\u0005r\u0000\u0000\u00a0\u0012\u0001\u0000\u0000\u0000\u00a1" +
                  "\u00a2\u0005w\u0000\u0000\u00a2\u00a3\u0005h\u0000\u0000\u00a3\u00a4\u0005" +
                  "i\u0000\u0000\u00a4\u00a5\u0005l\u0000\u0000\u00a5\u00a6\u0005e\u0000" +
                  "\u0000\u00a6\u0014\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005b\u0000\u0000" +
                  "\u00a8\u00a9\u0005r\u0000\u0000\u00a9\u00aa\u0005e\u0000\u0000\u00aa\u00ab" +
                  "\u0005a\u0000\u0000\u00ab\u00ac\u0005k\u0000\u0000\u00ac\u0016\u0001\u0000" +
                  "\u0000\u0000\u00ad\u00ae\u0005c\u0000\u0000\u00ae\u00af\u0005o\u0000\u0000" +
                  "\u00af\u00b0\u0005n\u0000\u0000\u00b0\u00b1\u0005t\u0000\u0000\u00b1\u00b2" +
                  "\u0005i\u0000\u0000\u00b2\u00b3\u0005n\u0000\u0000\u00b3\u00b4\u0005u" +
                  "\u0000\u0000\u00b4\u00b5\u0005e\u0000\u0000\u00b5\u0018\u0001\u0000\u0000" +
                  "\u0000\u00b6\u00b7\u0005t\u0000\u0000\u00b7\u00b8\u0005r\u0000\u0000\u00b8" +
                  "\u00b9\u0005u\u0000\u0000\u00b9\u00ba\u0005e\u0000\u0000\u00ba\u001a\u0001" +
                  "\u0000\u0000\u0000\u00bb\u00bc\u0005f\u0000\u0000\u00bc\u00bd\u0005a\u0000" +
                  "\u0000\u00bd\u00be\u0005l\u0000\u0000\u00be\u00bf\u0005s\u0000\u0000\u00bf" +
                  "\u00c0\u0005e\u0000\u0000\u00c0\u001c\u0001\u0000\u0000\u0000\u00c1\u00c2" +
                  "\u0005i\u0000\u0000\u00c2\u00c3\u0005f\u0000\u0000\u00c3\u001e\u0001\u0000" +
                  "\u0000\u0000\u00c4\u00c5\u0005e\u0000\u0000\u00c5\u00c6\u0005l\u0000\u0000" +
                  "\u00c6\u00c7\u0005s\u0000\u0000\u00c7\u00c8\u0005e\u0000\u0000\u00c8 " +
                  "\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005r\u0000\u0000\u00ca\u00cb\u0005" +
                  "e\u0000\u0000\u00cb\u00cc\u0005t\u0000\u0000\u00cc\u00cd\u0005u\u0000" +
                  "\u0000\u00cd\u00ce\u0005r\u0000\u0000\u00ce\u00cf\u0005n\u0000\u0000\u00cf" +
                  "\"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005s\u0000\u0000\u00d1\u00d2" +
                  "\u0005t\u0000\u0000\u00d2\u00d3\u0005r\u0000\u0000\u00d3\u00d4\u0005i" +
                  "\u0000\u0000\u00d4\u00d5\u0005n\u0000\u0000\u00d5\u00d6\u0005g\u0000\u0000" +
                  "\u00d6$\u0001\u0000\u0000\u0000\u00d7\u00dc\u0005\"\u0000\u0000\u00d8" +
                  "\u00db\u0003\'\u0013\u0000\u00d9\u00db\t\u0000\u0000\u0000\u00da\u00d8" +
                  "\u0001\u0000\u0000\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00db\u00de" +
                  "\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dc\u00da" +
                  "\u0001\u0000\u0000\u0000\u00dd\u00df\u0001\u0000\u0000\u0000\u00de\u00dc" +
                  "\u0001\u0000\u0000\u0000\u00df\u00e0\u0005\"\u0000\u0000\u00e0&\u0001" +
                  "\u0000\u0000\u0000\u00e1\u00e2\u0005\\\u0000\u0000\u00e2\u00e6\u0005\"" +
                  "\u0000\u0000\u00e3\u00e4\u0005\\\u0000\u0000\u00e4\u00e6\u0005\\\u0000" +
                  "\u0000\u00e5\u00e1\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000" +
                  "\u0000\u00e6(\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005.\u0000\u0000\u00e8" +
                  "*\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005(\u0000\u0000\u00ea,\u0001" +
                  "\u0000\u0000\u0000\u00eb\u00ec\u0005)\u0000\u0000\u00ec.\u0001\u0000\u0000" +
                  "\u0000\u00ed\u00ee\u0005[\u0000\u0000\u00ee0\u0001\u0000\u0000\u0000\u00ef" +
                  "\u00f0\u0005]\u0000\u0000\u00f02\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005" +
                  "{\u0000\u0000\u00f24\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005}\u0000" +
                  "\u0000\u00f46\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005+\u0000\u0000\u00f6" +
                  "8\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005-\u0000\u0000\u00f8:\u0001" +
                  "\u0000\u0000\u0000\u00f9\u00fa\u0005*\u0000\u0000\u00fa<\u0001\u0000\u0000" +
                  "\u0000\u00fb\u00fc\u0005/\u0000\u0000\u00fc>\u0001\u0000\u0000\u0000\u00fd" +
                  "\u00fe\u0005%\u0000\u0000\u00fe@\u0001\u0000\u0000\u0000\u00ff\u0100\u0005" +
                  "-\u0000\u0000\u0100\u0101\u0005>\u0000\u0000\u0101B\u0001\u0000\u0000" +
                  "\u0000\u0102\u0103\u0005<\u0000\u0000\u0103D\u0001\u0000\u0000\u0000\u0104" +
                  "\u0105\u0005<\u0000\u0000\u0105\u0106\u0005=\u0000\u0000\u0106F\u0001" +
                  "\u0000\u0000\u0000\u0107\u0108\u0005>\u0000\u0000\u0108H\u0001\u0000\u0000" +
                  "\u0000\u0109\u010a\u0005>\u0000\u0000\u010a\u010b\u0005=\u0000\u0000\u010b" +
                  "J\u0001\u0000\u0000\u0000\u010c\u010d\u0005=\u0000\u0000\u010d\u010e\u0005" +
                  "=\u0000\u0000\u010eL\u0001\u0000\u0000\u0000\u010f\u0110\u0005!\u0000" +
                  "\u0000\u0110\u0111\u0005=\u0000\u0000\u0111N\u0001\u0000\u0000\u0000\u0112" +
                  "\u0113\u0005&\u0000\u0000\u0113\u0114\u0005&\u0000\u0000\u0114P\u0001" +
                  "\u0000\u0000\u0000\u0115\u0116\u0005|\u0000\u0000\u0116\u0117\u0005|\u0000" +
                  "\u0000\u0117R\u0001\u0000\u0000\u0000\u0118\u0119\u0005!\u0000\u0000\u0119" +
                  "T\u0001\u0000\u0000\u0000\u011a\u011b\u0005<\u0000\u0000\u011b\u011c\u0005" +
                  "<\u0000\u0000\u011cV\u0001\u0000\u0000\u0000\u011d\u011e\u0005>\u0000" +
                  "\u0000\u011e\u011f\u0005>\u0000\u0000\u011fX\u0001\u0000\u0000\u0000\u0120" +
                  "\u0121\u0005&\u0000\u0000\u0121Z\u0001\u0000\u0000\u0000\u0122\u0123\u0005" +
                  "|\u0000\u0000\u0123\\\u0001\u0000\u0000\u0000\u0124\u0125\u0005^\u0000" +
                  "\u0000\u0125^\u0001\u0000\u0000\u0000\u0126\u0127\u0005~\u0000\u0000\u0127" +
                  "`\u0001\u0000\u0000\u0000\u0128\u0129\u0005=\u0000\u0000\u0129b\u0001" +
                  "\u0000\u0000\u0000\u012a\u012b\u0005+\u0000\u0000\u012b\u012c\u0005+\u0000" +
                  "\u0000\u012cd\u0001\u0000\u0000\u0000\u012d\u012e\u0005-\u0000\u0000\u012e" +
                  "\u012f\u0005-\u0000\u0000\u012ff\u0001\u0000\u0000\u0000\u0130\u0131\u0005" +
                  ";\u0000\u0000\u0131h\u0001\u0000\u0000\u0000\u0132\u0133\u0005:\u0000" +
                  "\u0000\u0133j\u0001\u0000\u0000\u0000\u0134\u0135\u0005?\u0000\u0000\u0135" +
                  "l\u0001\u0000\u0000\u0000\u0136\u013a\u0007\u0000\u0000\u0000\u0137\u0139" +
                  "\u0007\u0001\u0000\u0000\u0138\u0137\u0001\u0000\u0000\u0000\u0139\u013c" +
                  "\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013a\u013b" +
                  "\u0001\u0000\u0000\u0000\u013bn\u0001\u0000\u0000\u0000\u013c\u013a\u0001" +
                  "\u0000\u0000\u0000\u013d\u0141\u0007\u0002\u0000\u0000\u013e\u0140\u0007" +
                  "\u0003\u0000\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u0140\u0143\u0001" +
                  "\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0141\u0142\u0001" +
                  "\u0000\u0000\u0000\u0142\u0146\u0001\u0000\u0000\u0000\u0143\u0141\u0001" +
                  "\u0000\u0000\u0000\u0144\u0146\u00050\u0000\u0000\u0145\u013d\u0001\u0000" +
                  "\u0000\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0146p\u0001\u0000\u0000" +
                  "\u0000\u0147\u0148\u0007\u0004\u0000\u0000\u0148\u0149\u0001\u0000\u0000" +
                  "\u0000\u0149\u014a\u00068\u0000\u0000\u014ar\u0001\u0000\u0000\u0000\u014b" +
                  "\u014c\u0005\r\u0000\u0000\u014c\u014f\u0005\n\u0000\u0000\u014d\u014f" +
                  "\u0005\n\u0000\u0000\u014e\u014b\u0001\u0000\u0000\u0000\u014e\u014d\u0001" +
                  "\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0006" +
                  "9\u0000\u0000\u0151t\u0001\u0000\u0000\u0000\u0152\u0153\u0005/\u0000" +
                  "\u0000\u0153\u0154\u0005*\u0000\u0000\u0154\u0158\u0001\u0000\u0000\u0000" +
                  "\u0155\u0157\t\u0000\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000\u0157" +
                  "\u015a\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0158" +
                  "\u0156\u0001\u0000\u0000\u0000\u0159\u015b\u0001\u0000\u0000\u0000\u015a" +
                  "\u0158\u0001\u0000\u0000\u0000\u015b\u015c\u0005*\u0000\u0000\u015c\u015d" +
                  "\u0005/\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0006" +
                  ":\u0000\u0000\u015fv\u0001\u0000\u0000\u0000\u0160\u0161\u0005/\u0000" +
                  "\u0000\u0161\u0162\u0005/\u0000\u0000\u0162\u0166\u0001\u0000\u0000\u0000" +
                  "\u0163\u0165\t\u0000\u0000\u0000\u0164\u0163\u0001\u0000\u0000\u0000\u0165" +
                  "\u0168\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0166" +
                  "\u0164\u0001\u0000\u0000\u0000\u0167\u016a\u0001\u0000\u0000\u0000\u0168" +
                  "\u0166\u0001\u0000\u0000\u0000\u0169\u016b\u0005\r\u0000\u0000\u016a\u0169" +
                  "\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u016c" +
                  "\u0001\u0000\u0000\u0000\u016c\u016d\u0005\n\u0000\u0000\u016d\u016e\u0001" +
                  "\u0000\u0000\u0000\u016e\u016f\u0006;\u0000\u0000\u016fx\u0001\u0000\u0000" +
                  "\u0000\u000b\u0000\u00da\u00dc\u00e5\u013a\u0141\u0145\u014e\u0158\u0166" +
                  "\u016a\u0001\u0006\u0000\u0000";
  public static final ATN _ATN =
          new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}