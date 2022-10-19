package AST.Nodes;

import Utility.position;

public class returnSentNode extends SentenceNode{
    public ExpressionNode value;

    public returnSentNode(position pos_in,ExpressionNode value_in){
        super(pos_in);
        value=value_in;
    }

    public returnSentNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
