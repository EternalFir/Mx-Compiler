package AST.Nodes;

import AST.Symbols.varSymbol;

import Utility.position;

public class varDefSubNode extends SentenceNode {
    public String name;
    public typeNode type;
    public ExpressionNode expression;
    public varSymbol varSymbol;

    public varDefSubNode(position pos_in,String name_in,ExpressionNode expression_in){
        super(pos_in);
        name=name_in;
        expression=expression_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
