package frontend.ir.hierarchy;

import frontend.ir.Value;
import frontend.ir.irtype.*;
import utility.Debugger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IRModule {
  public String identifier;
  public ArrayList<IRFunction> builtinFuncList = new ArrayList<>();
  public ArrayList<IRFunction> irFuncList = new ArrayList<>();
  public LinkedList<Value> globVarList = new LinkedList<>();

  public HashMap<String, StructType> structMap = new HashMap<>();
  private IRFunction MallocFunction;

  public ArrayList<IRFunction> strBuiltinFunction = new ArrayList<>();

  public IRModule(String identifier) {
    this.identifier = identifier;
    createStringType();
    setBuiltinFunction();
  }

  public IRFunction getStrFunc(String memberFunc) {
    for (var it : strBuiltinFunction)
      if (it.name.contains(memberFunc)) // can find a builtin function for string type
        return it;
    return null;
  }

  private void createStringType() {
    BoolType Bool = new BoolType();
    IntType Int = new IntType();
    PtrType Str = new PtrType(new IntType(8));
    Value This = new Value("this", Str);

    strBuiltinFunction.add(new IRFunction("_str_length", new FuncType(Int), This));
    strBuiltinFunction.add(new IRFunction("_str_substring", new FuncType(Str), This, new Value("left", Int), new Value("right", Int)));
    strBuiltinFunction.add(new IRFunction("_str_parseInt", new FuncType(Int), This));
    strBuiltinFunction.add(new IRFunction("_str_ord", new FuncType(Int), This, new Value("pos", Int)));

    Value s1 = new Value("str1", Str), s2 = new Value("str2", Str);
    strBuiltinFunction.add(new IRFunction("_str_add", new FuncType(Str), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_eq", new FuncType(Bool), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_neq", new FuncType(Bool), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_slt", new FuncType(Bool), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_sle", new FuncType(Bool), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_sgt", new FuncType(Bool), s1, s2));
    strBuiltinFunction.add(new IRFunction("_str_sge", new FuncType(Bool), s1, s2));
  }

  private void setBuiltinFunction() {
    IntType Int = new IntType();
    VoidType Void = new VoidType();
    PtrType i8Star = new PtrType(new IntType(8));
    // StructType i8Star = getStruct("string");

    MallocFunction = new IRFunction("malloc", new FuncType(i8Star), new Value("malloc.size", Int));
    MallocFunction.description = "This function will malloc %malloc.size bytes in heap";
    builtinFuncList.add(MallocFunction);

    builtinFuncList.add(new IRFunction("print", new FuncType(Void), new Value("str", i8Star)));
    builtinFuncList.add(new IRFunction("println", new FuncType(Void), new Value("str", i8Star)));
    builtinFuncList.add(new IRFunction("printInt", new FuncType(Void), new Value("n", Int)));
    builtinFuncList.add(new IRFunction("printlnInt", new FuncType(Void), new Value("n", Int)));
    builtinFuncList.add(new IRFunction("getString", new FuncType(i8Star)));
    builtinFuncList.add(new IRFunction("getInt", new FuncType(Int)));
    builtinFuncList.add(new IRFunction("toString", new FuncType(i8Star), new Value("n", Int)));
  }

  public void addGlobVariable(Value glob) {
    globVarList.add(glob);
  }

  public void addFunction(IRFunction func) {
    irFuncList.add(func);
  }

  public void addStruct(StructType struct) {
    structMap.put(struct.className, struct);
  }

  public StructType getStruct(String name) {
    StructType ret = structMap.get(name);
    if (ret == null)
      Debugger.error("no class named " + name);
    return ret;
  }


  public IRFunction MallocFunction() {
    return MallocFunction;
  }

  public String getName() {
    return identifier;
  }

}
