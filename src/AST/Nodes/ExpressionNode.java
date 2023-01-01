package AST.Nodes;

import AST.Types.basicType;
import IR.operand.operand;
import IR.Block;

import Utility.position;

public abstract class ExpressionNode extends ASTNode {
    public basicType type;
    public boolean isAssignable;

    public operand operand;

    public Block trueSent=null;
    public Block falseSent=null;

    public ExpressionNode(position pos_in) {
        super(pos_in);
    }

    public ExpressionNode(position pos_in, boolean assignable) {
        super(pos_in);
        isAssignable = assignable;
    }
}
