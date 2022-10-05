package AST.Expression;

import AST.*;
import Utility.Error.SyntaxError;
import Utility.Position;
import Utility.Type.BaseType;

public class binaryExprNode extends ExprNode {
  public enum binaryOpType {
    Arithmetic, Compare, Equal
//    Mul, Div, Mod,
//    Add, Sub,
//    LeftShift, RightShift,
//    And, Xor, Or,
//    LogicAnd, LogicOr,
//    Equal, Neq,
//    Greater, Less, Geq, Leq
  }

  public ExprNode lhs, rhs;
  public String opCode;
  public binaryOpType opType;

  void match(String symbol) {
    switch(symbol) {
      case "+", "-", "*", "/", "%" :
      case "^", "|", "&" :
      case "<<", ">>" :
        opType = binaryOpType.Arithmetic;
        restype = lhs.restype;
        break;
      case "<", ">", "<=", ">=" :
        opType = binaryOpType.Compare;
        restype.type = BaseType.BultinType.BOOL;
        break;
      case "==", "!=" :
        opType = binaryOpType.Equal;
        restype.type = BaseType.BultinType.BOOL;
        break;
      default :
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

  public binaryExprNode(ExprNode lhs, ExprNode rhs, String opCode, Position pos) {
    super(pos);
    this.lhs = lhs;
    this.rhs = rhs;
    this.opCode = opCode;
    match(opCode);
//    System.out.println(opType);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
