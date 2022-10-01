package AST;

import Utility.Position;

abstract public class ExprNode extends ASTNode {

    ExprNode(Position pos) {
        super(pos);
    }

    boolean isAssignable() {
        return false;
    }



//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
