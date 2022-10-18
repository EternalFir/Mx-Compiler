package AST.Nodes;

import AST.Types.basicType;


import Utility.position;

public abstract class ExpressionNode extends ASTNode{
    public basicType type;
    public boolean isAssignable;

    public ExpressionNode(position pos_in){
        super(pos_in);
    }

    public ExpressionNode(position pos_in,boolean assignable){
        super(pos_in);
        isAssignable=assignable;
    }
}
