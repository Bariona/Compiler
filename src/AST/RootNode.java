package AST;

import Utility.Position;

public class RootNode extends ASTNode {
    RootNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
