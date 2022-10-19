package AST.Nodes;

import Utility.position;

public class binaryExpNode extends ExpressionNode {
    public ExpressionNode lexp,rexp;
    public String op;

    public binaryExpNode(position pos_in,String op_in,ExpressionNode lexp_in, ExpressionNode rexp_in){
        super(pos_in);
        op=op_in;
        lexp=lexp_in;
        rexp=rexp_in;
    }

    public binaryExpNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
