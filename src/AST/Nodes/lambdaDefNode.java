package AST.Nodes;

import AST.Types.basicType;
import AST.Symbols.funcSymbol;

import Utility.position;

import java.util.ArrayList;

public class lambdaDefNode extends ExpressionNode {
    public boolean ifAnd;
    public codeBlockNode codeBlock;
    public ArrayList<varDefSubNode> paramList;
    public ArrayList<ExpressionNode> expList;
    public funcSymbol funcSymbol = null;
    public basicType returnType = null;

    public lambdaDefNode(position pos_in, boolean ifAnd_in, codeBlockNode codeBlock_in, ArrayList<varDefSubNode> paramList_in,expressionGroupNode expList_in) {
        super(pos_in);
        ifAnd = ifAnd_in;
        codeBlock = codeBlock_in;
        paramList = paramList_in;
        expList=expList_in.expList;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
