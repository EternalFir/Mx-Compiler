package AST.Nodes;

import Utility.position;

public class arrayExpNode extends ExpressionNode {
    public ExpressionNode master,index;

    public arrayExpNode(position pos_in,ExpressionNode master_in,ExpressionNode index_in){
        super(pos_in);
        master=master_in;
        index=index_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
