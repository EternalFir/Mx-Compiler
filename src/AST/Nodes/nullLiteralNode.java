package AST.Nodes;

import Utility.position;

public class nullLiteralNode extends ExpressionNode{
    public nullLiteralNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
