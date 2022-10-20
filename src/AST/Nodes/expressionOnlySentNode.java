package AST.Nodes;

import Utility.position;

public class expressionOnlySentNode extends SentenceNode{
    public ExpressionNode expression;

    public expressionOnlySentNode(position pos_in,ExpressionNode exp_in){
        super(pos_in);
        expression=exp_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
