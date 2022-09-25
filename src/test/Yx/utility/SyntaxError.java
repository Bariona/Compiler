package utility;

public class SyntaxError extends Error {
    public SyntaxError(String msg, position pos) {
        super("syntax error", msg, pos);
    }
}
