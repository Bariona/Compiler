package middleend.hierarchy;

import middleend.irinst.GlobalDef;
import middleend.irtype.*;

import java.util.ArrayList;

public class IRModule {
  String identifier;
  public ArrayList<IRFunction> irFunctionList = new ArrayList<>();
  public ArrayList<GlobalDef> globVarList = new ArrayList<>();
  public ArrayList<StructType> structList = new ArrayList<>();

  public IRModule(String identifier) {
    this.identifier = identifier;
    setBuiltinFunction();
  }

  private void setBuiltinFunction() {
    PtrType heapPtr = new PtrType(new IntType(8));
    irFunctionList.add(new IRFunction("malloc", new FuncType(heapPtr, new IntType())));

  }

  public void addGlobVariable(GlobalDef glob) {
    globVarList.add(glob);
  }

  public void addFunction(IRFunction func) {
    irFunctionList.add(func);
  }

  public void addStruct(StructType struct) {
    structList.add(struct);
  }


  public IRFunction MallocFunction() {
    return irFunctionList.get(0);
  }

  public String getName() {
    return this.identifier;
  }

}
