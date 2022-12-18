package frontend.ast.astnode.expression;

import frontend.ast.ASTVisitor;
import frontend.ast.astnode.statement.SuiteStmtNode;
import utility.Position;
import utility.info.FuncInfo;
import utility.scope.FuncScope;
import utility.type.BaseType;
import utility.type.VarType;

import java.util.ArrayList;

public class LambdaExprNode extends ExprNode {
  public FuncInfo info; // parameterList's information(name, type etc)
  public SuiteStmtNode suite;
  public ArrayList<ExprNode> argumentList;
  public FuncScope scope;

  public LambdaExprNode(SuiteStmtNode suite, Position pos) {
    super(pos);
    this.info = new FuncInfo("Lambda", new VarType(BaseType.BuiltinType.NULL), this.pos);
    this.suite = suite;
    this.argumentList = new ArrayList<>();
    this.scope = new FuncScope(this.info);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
