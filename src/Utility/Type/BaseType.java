package Utility.Type;

import java.util.HashMap;

public class BaseType {
    public enum Type {
        VOID, BOOL, INT, STRING, CLASS, NULL, NEW
    }

    public Type type;
    public HashMap<String, Type> members = null;

}
