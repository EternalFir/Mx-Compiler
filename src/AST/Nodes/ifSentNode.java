package AST.Nodes;

import Utility.position;

public class ifSentNode extends SentenceNode {
    public ExpressionNode cond;

    public SentenceNode trunSent, falseSent;

    public ifSentNode(position pos_in, ExpressionNode condition, SentenceNode trueChoice, SentenceNode falseChoice) {
        super(pos_in);
        cond = condition;
        trunSent = trueChoice;
        falseSent = falseChoice;
    }

    public ifSentNode(position pos_in) {
        super(pos_in);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
