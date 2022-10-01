package AST;

import Utility.Position;

abstract public class StmtNode extends ASTNode {
    StmtNode(Position pos) {
        super(pos);
    }

//    @Override
//    public void accept(ASTvisitor visitor) {
//        visitor.visit(this);
//    }
}
