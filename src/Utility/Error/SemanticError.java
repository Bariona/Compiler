package Utility.Error;

import Utility.Position;

public class SemanticError extends Error {

  public SemanticError(String msg, Position pos) {
    super("Semantic error", msg, pos);
  }
}
