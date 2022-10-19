package AST.Nodes;

import Utility.position;

import java.util.ArrayList;
public class expressionGroupNode extends ExpressionNode{
    public ArrayList<ExpressionNode> expList = new ArrayList<>();

    public expressionGroupNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
