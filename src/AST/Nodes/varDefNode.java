package AST.Nodes;

import Utility.position;

import java.util.ArrayList;

public class varDefNode extends SentenceNode {
    public ArrayList<varDefSubNode> varDefList = new ArrayList<>();

    public varDefNode(position pos_in) {
        super(pos_in);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
