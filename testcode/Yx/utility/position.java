package utility;

public class position {
  private final int row;
  private final int col;

  public position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public String toString() {
    return row + ":" + col;
  }
}
