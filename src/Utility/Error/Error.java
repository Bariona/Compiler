package Utility.Error;

import Utility.Position;

public abstract class Error extends RuntimeException {
    final String error_type, detail_info;
    final Position pos;
    public Error(String error_type, String detail_info, Position pos) {
        this.error_type = error_type;
        this.detail_info = detail_info;
        this.pos = pos;
    }

    public String toString() {
        return pos.toString() + " : " + error_type + " : " + detail_info;
    }
}
