package utility;

import org.antlr.v4.runtime.*;

import java.util.*;

import utility.error.SyntaxError;

// Limitation: can only detect  Building-ParserTree-process error AKA. syntax Error
public class MxErrorListener extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line, int charPositionInLine,
                          String msg,
                          RecognitionException e
  ) {
    List<String> stk = ((Parser) recognizer).getRuleInvocationStack();
    Collections.reverse(stk);
    System.err.println("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + " : " + msg);
    System.err.println("rule stack: " + stk);
    // decide which kind of error to throw
    throw new SyntaxError(msg, new Position(line, charPositionInLine));
  }
}
