package utility;

public class position {
    private final int line;
    private final int column;

    public position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public String toString() {
        return line + ":" + column;
    }
}
