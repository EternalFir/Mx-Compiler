package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class funcExpNode extends ExpressionNode{
    public ExpressionNode master;
    public ArrayList<ExpressionNode> expList;

    public funcExpNode(position pos_in,ExpressionNode main_in,expressionGroupNode expList_in){
        super(pos_in);
        master=main_in;
        expList=expList_in.expList;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
