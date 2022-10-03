package AST;

import Utility.Position;

import java.util.ArrayList;

public class varDefNode extends DefNode {
//  public BaseType type;
//  String name; // 这里不应该加一个name, 应该是VarTerm (根据g4的规则来)
//  ExprNode init;
  public ArrayList<varSingleDefNode> varlist = new ArrayList<>();

  public varDefNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
