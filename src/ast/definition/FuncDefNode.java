package ast.definition;

import ast.*;
import utility.Position;
import utility.Scope;
import utility.type.BaseType;
import utility.type.FuncType;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class FuncDefNode extends DefNode {
  public FuncType type;
  public String name;
  public ArrayList<Pair<BaseType, String>> parameterList;
  public StmtNode stmts;
  public Scope scope;

  public FuncDefNode(String name, Position pos) {
    super(pos);
    this.name = name;
    parameterList = new ArrayList<>();
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
