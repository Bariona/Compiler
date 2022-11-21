package middleend.hierarchy;

import middleend.irinst.GlobalDef;
import middleend.irtype.StructType;

import java.util.ArrayList;

public class IRModule {
  String identifier;
  public ArrayList<IRFunction> irFunctionList;
  public ArrayList<GlobalDef> globVarList;
  public ArrayList<StructType> structList;

  public IRModule(String id) {
    identifier = id;
    irFunctionList = new ArrayList<>();
    globVarList = new ArrayList<>();
    structList = new ArrayList<>();
  }

  public String getName() {
    return this.identifier;
  }

}
