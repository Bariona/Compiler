package AST;

import Utility.Position;

public class cmpExprNode extends ExprNode {
    public ExprNode lhs, rhs;
    public enum cmpOpType {
        Less, Leq, Greater, Geq, Equal, NotEqual
    }
    public cmpOpType opCode;

    cmpExprNode(ExprNode lhs, ExprNode rhs, cmpOpType opCode, Position pos) {
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
