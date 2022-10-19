package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class programNode extends ASTNode{
    public ArrayList<ASTNode> proBody =new ArrayList<>();

    public programNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
