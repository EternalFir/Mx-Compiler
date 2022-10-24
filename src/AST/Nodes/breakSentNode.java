package AST.Nodes;

import Utility.position;

public class breakSentNode extends SentenceNode {
    public SentenceNode loop;

    public breakSentNode(position pos_in) {
        super(pos_in);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
