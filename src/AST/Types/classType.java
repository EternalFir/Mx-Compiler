package AST.Types;

import IR.IRType.classIRType;
import AST.Symbols.*;
import java.util.HashMap;

public class classType extends basicType{
    public String name;
    public HashMap<String,varSymbol> varMap=new HashMap<>();
    public HashMap<String,funcSymbol> funcMap=new HashMap<>();
    public funcSymbol constructor=null;
    public classIRType classIrType=null;

    public classType(String name_in){
        name=name_in;
    }

    public boolean sameType(basicType t){
        return t.isNull()||((t instanceof classType)&&(name.equals(((classType)t).name)));
    }



}
