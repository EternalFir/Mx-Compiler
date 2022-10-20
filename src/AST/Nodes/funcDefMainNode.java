package AST.Nodes;

import AST.Types.basicType;
import AST.Symbols.funcSymbol;
import Utility.position;

import java.util.ArrayList;

public class funcDefMainNode extends DefNode{
    public String name;
    public codeBlockNode codeBlock;

    public basicType returnType = null;

    public funcDefMainNode(position pos_in, String name_in,codeBlockNode codeBlock_in) {
        super(pos_in);
        name = name_in;
        codeBlock = codeBlock_in;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
