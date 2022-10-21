package AST.Nodes;

import Utility.position;

public class intLiteralNode extends ExpressionNode{
    public int value;

    public intLiteralNode(position pos_in,int value_in){
        super(pos_in);
        value=value_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
