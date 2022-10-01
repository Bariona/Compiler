package AST;

import Utility.Position;

abstract public class ASTNode {
    public Position pos;
    ASTNode(Position pos) {
        this.pos = pos;
    }
    abstract public void accept(ASTvisitor visitor);
}
