package utility.error;

import utility.Position;

public class UndefinedError extends Error {

  public UndefinedError(String msg, Position pos) {
    super("Undefined Behavior", msg, pos);
  }
}
