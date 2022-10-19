package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class progromNode extends ASTNode{
    public ArrayList<ASTNode> proBody =new ArrayList<>();

    public progromNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
