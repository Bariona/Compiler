package utility;

import utility.type.BaseType;
import utility.type.FuncType;
import utility.type.VarType;

public class StringDealer {

  static public FuncType matchFunction(String name) {
    FuncType ret = null;
    if (name.equals("length")) {
     ret = new FuncType(BaseType.BuiltinType.INT);

    } else if (name.equals("substring")) {
      ret = new FuncType(BaseType.BuiltinType.STRING);
      ret.paraListType.add(new VarType(BaseType.BuiltinType.INT)); // left
      ret.paraListType.add(new VarType(BaseType.BuiltinType.INT)); // right
    } else if (name.equals("parseInt")) {
      ret = new FuncType(BaseType.BuiltinType.INT);

    } else if (name.equals("ord")) {
      ret = new FuncType(BaseType.BuiltinType.INT);
      ret.paraListType.add(new VarType(BaseType.BuiltinType.INT)); // pos

    }
    return ret;
  }

}
