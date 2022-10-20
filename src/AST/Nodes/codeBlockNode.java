package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class codeBlockNode extends ASTNode{
    public ArrayList<SentenceNode> sentencesList=new ArrayList<>();

    public codeBlockNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
