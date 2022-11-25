package utility.error;

import utility.Position;

public class IRBuildError extends Error {

  public IRBuildError(String msg, Position pos) {
    super("IR Builder error", msg, pos);
  }

}
