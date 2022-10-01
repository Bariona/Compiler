package AST;

import Utility.Position;

public class varExprNode extends ExprNode {
    String name;
    varExprNode(String name, Position pos) {
        super(pos);
        this.name = name;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    boolean isAssignable() {
        return true;
    }
}
