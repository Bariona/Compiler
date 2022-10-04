package Utility.Error;

import Utility.Position;

public class SyntaxError extends Error {

  public SyntaxError(String msg, Position pos) {
    super("Syntax error", msg, pos);
  }
}
