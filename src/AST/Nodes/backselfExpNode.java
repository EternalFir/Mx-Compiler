package AST.Nodes;

import Utility.position;

public class backselfExpNode extends ExpressionNode{
    public ExpressionNode exp;
    public String op;

    public backselfExpNode(position pos_in,ExpressionNode exp_in,String op_in){
        super(pos_in);
        exp=exp_in;
        op=op_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
