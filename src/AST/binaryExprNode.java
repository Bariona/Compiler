package AST;

import Utility.Position;

public class binaryExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public enum binaryOpType {
        Mul, Div, Mod,
        Add, Sub,
        LeftShift, RightShift,
        And, Xor, Or
    }
    public binaryOpType opCode;

    binaryExprNode(ExprNode lhs, ExprNode rhs, binaryOpType opCode, Position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
