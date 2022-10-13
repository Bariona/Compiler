package utility.error;

import utility.Position;

public class NameError extends Error {
  public NameError(String msg, Position pos) {
    super("Name error", msg, pos);
  }
}
