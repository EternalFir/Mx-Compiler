package AST.Symbols;

import IR.Function;
import java.util.ArrayList;

import AST.Types.basicType;

public class funcSymbol extends basicType{
    public String name;
    public basicType returnType;
    public ArrayList<varSymbol> paramList =new ArrayList<>();

    public Function function=null;

    public funcSymbol(String name_in){
        name=name_in;
    }
}
