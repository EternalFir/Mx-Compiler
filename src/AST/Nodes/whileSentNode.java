package AST.Nodes;

import IR.Block;
import Utility.position;

public class whileSentNode extends SentenceNode {
    public ExpressionNode cond;
    public SentenceNode repeSent;

    public Block destinationBlock;
    public Block conditionBlock;

    public whileSentNode(position pos_in,ExpressionNode cond_in,SentenceNode repe_in){
        super(pos_in);
        cond=cond_in;
        repeSent=repe_in;
    }

    public whileSentNode(position pos_in){
        super(pos_in);
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
