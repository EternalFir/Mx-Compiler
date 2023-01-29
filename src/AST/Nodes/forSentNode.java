package AST.Nodes;

import IR.Block;
import Utility.position;

public class forSentNode extends SentenceNode {
    public ExpressionNode init, cond, move;

    public varDefNode initDef;

    public SentenceNode repeSent;

    public Block destinationBlock;
    public Block moveBlock;

    public forSentNode(position pos_in, ExpressionNode init_in, ExpressionNode cond_in, ExpressionNode move_in, SentenceNode repe_in) {
        super(pos_in);
        init = init_in;
        cond = cond_in;
        repeSent = repe_in;
    }

    public forSentNode(position pos_in,varDefNode initDef_in, ExpressionNode cond_in, ExpressionNode move_in, SentenceNode repe_in){
        super(pos_in);
        initDef=initDef_in;
        cond=cond_in;
        move=move_in;
        repeSent=repe_in;
    }

    public forSentNode(position pos_in) {
        super(pos_in);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
