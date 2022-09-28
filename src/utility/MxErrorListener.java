package utility;

import org.antlr.v4.runtime.*;
import java.util.*;

public class MxErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e
    )
    {
        List<String> stk = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stk);
        System.err.println("rule stack: " + stk);
        System.err.println("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + " : " + msg);
        // decide which kind of error to throw
        throw new SyntaxError(msg, new position(line, charPositionInLine));
    }
}
