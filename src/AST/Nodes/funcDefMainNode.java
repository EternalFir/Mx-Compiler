package AST.Nodes;

import AST.Types.basicType;
import AST.Symbols.funcSymbol;
import Utility.position;

import java.util.ArrayList;

public class funcDefMainNode extends DefNode{
    public String name;
    public typeNode type;
    public codeBlockNode codeBlock;
    public ArrayList<varDefNode> paramList;

    public basicType returnType = null;

    public funcDefMainNode(position pos_in, String name_in, typeNode type_in, codeBlockNode codeBlock_in, ArrayList<varDefNode> paramList_in) {
        super(pos_in);
        name = name_in;
        type = type_in;
        codeBlock = codeBlock_in;
        paramList = paramList_in;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
