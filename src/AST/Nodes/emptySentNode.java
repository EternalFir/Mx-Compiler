package AST.Nodes;

import Utility.position;

public class emptySentNode extends SentenceNode{
    public emptySentNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
