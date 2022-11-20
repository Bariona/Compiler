package utility;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Position {
  private final int line;
  private final int column;

  public Position(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public Position(Token token) {
    this.line = token.getLine();
    this.column = token.getCharPositionInLine();
  }

  public Position(ParserRuleContext ctx) {
    this(ctx.getStart());
  }

  public String toString() {
    return line + ":" + column;
  }
}
