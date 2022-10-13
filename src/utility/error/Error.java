package utility.error;

import utility.Position;

public abstract class Error extends RuntimeException {
  final String errorType, detailInfo;
  final Position pos;

  public Error(String error_type, String detail_info, Position pos) {
    this.errorType = error_type;
    this.detailInfo = detail_info;
    this.pos = pos;
  }

  public String toString() {
    return pos.toString() + " : " + errorType + " : " + detailInfo;
  }
}
