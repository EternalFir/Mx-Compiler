package AST.Nodes;

import AST.Symbols.varSymbol;
import Utility.position;

public class varExpNode extends ExpressionNode{
    public String name;
    public varSymbol varSymbol;

    public varExpNode(position pos_in, String name_in ){
        super(pos_in);
        name=name_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
