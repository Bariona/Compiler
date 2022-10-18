package ast.expression;

import ast.ASTVisitor;
import utility.Position;
import utility.error.SyntaxError;

public class BinaryExprNode extends ExprNode {
  public enum binaryOpType {
    Arithmetic, Compare, Equal, Logic
  }

  public ExprNode lhs, rhs;
  public String opCode;
  public binaryOpType opType;

  void match(String symbol) {
    opType = switch (symbol) {
      case "+", "-", "*", "/", "%", "^", "|", "&", "<<", ">>" -> binaryOpType.Arithmetic;
      case "<", ">", "<=", ">=" -> binaryOpType.Compare;
      case "==", "!=" -> binaryOpType.Equal;
      case "&&", "||" -> binaryOpType.Logic;
      default -> {
        System.out.println(symbol);
        throw new SyntaxError("Miss operator", this.pos);
      }
    };
  }

  public BinaryExprNode(ExprNode lhs, ExprNode rhs, String opCode, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    this.opCode = opCode;
    match(opCode);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
