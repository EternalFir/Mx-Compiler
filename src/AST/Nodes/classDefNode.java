package AST.Nodes;

import AST.Types.classType;

import Utility.position;

import java.util.ArrayList;

public class classDefNode extends DefNode{
    public String name;
    public ArrayList<varDefSubNode> varList =new ArrayList<>();
    public ArrayList<funcDefNode> funcList = new ArrayList<>();
    public funcDefNode constructor = null;
    public classType classType=null;

    public classDefNode(position pos_in , String name_in){
        super(pos_in);
        name=name_in;
    }

    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
