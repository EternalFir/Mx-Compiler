package AST.Nodes;

import Utility.position;

public class continueSentNode extends SentenceNode{
    public SentenceNode loop;

    public continueSentNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
