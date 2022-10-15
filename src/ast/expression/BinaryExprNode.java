package ast.expression;

import ast.ASTVisitor;
import utility.Position;
import utility.error.SyntaxError;
import utility.type.BaseType;

public class BinaryExprNode extends ExprNode {
  public enum binaryOpType {
    Arithmetic, Compare, Equal, Logic
  }

  public ExprNode lhs, rhs;
  public String opCode;
  public binaryOpType opType;

  void match(String symbol) {
    switch (symbol) {
      case "+", "-", "*", "/", "%":
      case "^", "|", "&":
      case "<<", ">>":
        opType = binaryOpType.Arithmetic;
//        restype = lhs.restype;
        break;
      case "<", ">", "<=", ">=":
        opType = binaryOpType.Compare;
//        exprType = BaseType.BuiltinType.BOOL;
        break;
      case "==", "!=":
        opType = binaryOpType.Equal;
//        exprType.builtinType = BaseType.BuiltinType.BOOL;
        break;
      case "&&", "||":
        opType = binaryOpType.Logic;
        break;
      default:
        System.out.println(symbol);
        throw new SyntaxError("Miss operator", this.pos);
//      case "+": return binaryOpType.Add;
//      case "-": return binaryOpType.Sub;
//      case "*": return binaryOpType.Mul;
//      case "/": return binaryOpType.Div;
//      case "%": return binaryOpType.Mod;
//      case "<<": return binaryOpType.LeftShift;
//      case ">>": return binaryOpType.RightShift;
//      case "&": return binaryOpType.And;
//      case "^": return binaryOpType.Xor;
//      case "|": return binaryOpType.Or;
    }
  }

  public BinaryExprNode(ExprNode lhs, ExprNode rhs, String opCode, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    this.opCode = opCode;
    match(opCode);
//    System.out.println(opType);
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
