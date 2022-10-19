package AST.Nodes;

import Utility.position;

import java.util.Set;

public class typeNode extends ASTNode{
    public String basicTypeName;
    public int dimension;

    public typeNode(position pos_in, String typeName_in,int dim_in){
        super(pos_in);
        basicTypeName=typeName_in;
        dimension=dim_in;
    }

    public typeNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
