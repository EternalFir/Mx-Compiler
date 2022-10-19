package AST.Nodes;

import AST.Types.basicType;
import AST.Symbols.funcSymbol;
import Utility.position;

import java.util.ArrayList;

public class funcDefNode extends DefNode {
    public String name;
    public typeNode type;
    public codeBlockNode codeBlock;
    public ArrayList<varDefSubNode> paramList;
    public funcSymbol funcSymbol = null;
    public basicType returnType = null;

    public funcDefNode(position pos_in, String name_in, typeNode type_in, codeBlockNode codeBlock_in, ArrayList<varDefSubNode> paramList_in) {
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
