package AST.Nodes;

import Utility.position;

public class arrayExpNode extends ExpressionNode {
    public ExpressionNode base,index;

    public arrayExpNode(position pos_in,ExpressionNode base_in,ExpressionNode index_in){
        super(pos_in);
        base=base_in;
        index=index_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
