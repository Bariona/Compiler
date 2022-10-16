package frontend;

import ast.expression.BinaryExprNode;
import ast.expression.BinaryExprNode.binaryOpType;
import ast.expression.UnaryExprNode;
import utility.error.SemanticError;
import utility.error.SyntaxError;
import utility.type.BaseType;

public class TypeChecker {
  static public void unaryCheck(UnaryExprNode node) {
    switch (node.opCode) {
      case "!":
        if (!BaseType.isBoolType(node.expression.exprType))
          throw new SemanticError("expect BOOL", node.pos);
        break;
      case "++", "--":
        if (!BaseType.isIntType(node.expression.exprType))
          throw new SemanticError("expect INT", node.pos);
        if (!node.expression.isAssignable())
          throw new SemanticError("expect variable", node.pos);
        break;
      case "+", "-", "~":
        if (!BaseType.isIntType(node.expression.exprType))
          throw new SemanticError("expect INT", node.pos);
        break;
      default:
        throw new SyntaxError("Unary undefined character", node.pos);
    }
  }

  static public void binaryCheck(BinaryExprNode node) {
    if (!node.lhs.exprType.isSame(node.rhs.exprType))
      throw new SemanticError("left/right ExprType not match", node.pos);
    boolean flag = false;
    if (BaseType.isStringType(node.lhs.exprType)) { // String Type
      if (node.opType == binaryOpType.Arithmetic && !node.opCode.equals("+"))
        flag = true;
      if (node.opType == binaryOpType.Logic)
        flag = true;
    }

    else { // Other Type: INT, BOOL, CLASS...
      if (node.opType == binaryOpType.Arithmetic) {
        if (!BaseType.isIntType(node.lhs.exprType)) // arithmetic: int
          flag = true;
      } else if (node.opType == binaryOpType.Logic) {
        if (!BaseType.isBoolType(node.lhs.exprType)) // logic: bool
          flag = true;
      } else if (node.opType == binaryOpType.Compare) { // compare: int
        if (!BaseType.isIntType(node.lhs.exprType))
          flag = true;
      } else {
        // equal
      }
    }
    if (flag) throw new SemanticError("type not correct", node.pos);
  }
}
