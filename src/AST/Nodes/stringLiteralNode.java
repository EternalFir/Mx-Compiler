package AST.Nodes;

import Utility.position;

public class stringLiteralNode extends ExpressionNode{
    public String value;

    public stringLiteralNode(position pos_in,String value_in){
        super(pos_in);
        value=value_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
