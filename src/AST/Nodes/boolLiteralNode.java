package AST.Nodes;

import Utility.position;

public class boolLiteralNode extends ExpressionNode{
    public boolean value;

    public boolLiteralNode(position pos_in,boolean value_in){
        super(pos_in);
        value=value_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
