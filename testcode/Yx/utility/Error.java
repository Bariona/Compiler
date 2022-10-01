package utility;

public abstract class Error extends RuntimeException {
  final String error_type, detail_info;
  final position pos;

  public Error(String error_type, String detail_info, position pos) {
    this.error_type = error_type;
    this.detail_info = detail_info;
    this.pos = pos;
  }

  public String toString() {
    return pos.toString() + " : " + error_type + " : " + detail_info;
  }
}
