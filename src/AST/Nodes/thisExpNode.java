package AST.Nodes;

import Utility.position;

public class thisExpNode extends ExpressionNode{
    public thisExpNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
