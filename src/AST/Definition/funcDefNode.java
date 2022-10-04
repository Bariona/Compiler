package AST.Definition;

import AST.*;
import Utility.Position;
import Utility.Type.BaseType;
import Utility.Type.FuncType;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class funcDefNode extends DefNode {
  public FuncType type;
  public String name;
  public ArrayList<Pair<BaseType, String>> parameterList;

  public funcDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
    parameterList = new ArrayList<>();
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
