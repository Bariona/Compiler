package utility.error;

import utility.Position;

public class SemanticError extends Error {

  public SemanticError(String msg, Position pos) {
    super("Semantic error", msg, pos);
  }

}
