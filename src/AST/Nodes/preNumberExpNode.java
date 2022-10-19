package AST.Nodes;

import Utility.position;

public class preNumberExpNode extends ExpressionNode{
    public String op;
    public ExpressionNode exp;

    public preNumberExpNode(position pos_in,String op_in,ExpressionNode exp_in){
        super(pos_in);
        op=op_in;
        exp=exp_in;
    }

    public preNumberExpNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
