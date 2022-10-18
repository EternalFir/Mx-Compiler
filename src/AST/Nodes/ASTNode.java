package AST.Nodes;

import Utility.position;
import AST.Scope;
public abstract class ASTNode {
    public position pos;
    public Scope myScope;
    public ASTNode(position pos_in,Scope scope_in){
        pos=pos_in;
        myScope=scope_in;
    }


    public abstract void accept(ASTVisitor visitor);
}
