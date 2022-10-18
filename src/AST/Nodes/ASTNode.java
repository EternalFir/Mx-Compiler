package AST.Nodes;

import Utility.position;
import AST.Scope;
public abstract class ASTNode {
    public position pos;

    public ASTNode(position pos_in){
        pos=pos_in;
    }
    public abstract void accept(ASTVisitor visitor);
}
