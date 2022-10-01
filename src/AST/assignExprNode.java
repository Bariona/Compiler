package AST;

import Utility.Position;

public class assignExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    assignExprNode(ExprNode lhs, ExprNode rhs, Position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
