package AST.Nodes;

import Utility.position;

public class memberExpNode extends ExpressionNode {
    public ExpressionNode caller;
    public String callee;
    public boolean isFunc=false;

    public memberExpNode(position pos_in,ExpressionNode caller_in,String callee_in){
        super(pos_in);
        caller=caller_in;
        callee=callee_in;
    }

    public memberExpNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
