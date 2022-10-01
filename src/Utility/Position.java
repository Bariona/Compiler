package Utility;

public class Position {
    private final int line;
    private final int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public String toString() {
        return line + ":" + column;
    }
}
