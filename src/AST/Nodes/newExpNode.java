package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class newExpNode extends ExpressionNode{
    public typeNode typeNode;
    public ArrayList<ExpressionNode> expList;

    public newExpNode(position pos_in,typeNode type_in,int dim_in,ArrayList<ExpressionNode> expList_in){
        super(pos_in);
        typeNode=type_in;
        typeNode.dimension=dim_in;
        expList=expList_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
