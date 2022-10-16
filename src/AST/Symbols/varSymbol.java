package AST.Symbols;

import IR.operand.operand;

import AST.Types.basicType;

public class varSymbol {
    public String name;
    public basicType type;
    public boolean isGlobalVar = false;
    public boolean isClassVal = false;
    public operand operand = null;

    public varSymbol(String name_in) {
        name = name_in;
    }

    public varSymbol(String name_in, basicType type_in) {
        name = name_in;
        type = type_in;
    }

}
