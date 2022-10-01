package AST;

import Utility.Position;

public class IfStmtNode extends StmtNode {
    ExprNode condition;
    StmtNode thenStmt, elseStmt;

    public IfStmtNode(ExprNode condition, StmtNode thenStmt, StmtNode elseStmt, Position pos) {
        super(pos);
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}
