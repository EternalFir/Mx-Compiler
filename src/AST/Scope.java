package AST;

import AST.Nodes.typeNode;
import AST.Symbols.*;
import AST.Types.basicType;

import Utility.errors.semanticError;
import Utility.position;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Scope {
    public Scope parentScope;
    public HashMap<String, basicType> typeMap = new HashMap<>();
    public HashMap<String, varSymbol> varMap = new HashMap<>();
    public HashMap<String, funcSymbol> funcMap = new HashMap<>();

    public Scope(Scope parent) {
        parentScope = parent;
    }

    public void clear() {
        typeMap.clear();
        varMap.clear();
        funcMap.clear();
    }


    public void addType(String name_in, basicType value_in, position pos_in) {
        if (typeMap.containsKey(name_in))
            throw new semanticError("type redefine", pos_in);
        else
            typeMap.put(name_in, value_in);
    }

    public boolean containType(String name_in, position pos_in, boolean ifLookUp) {
        if (typeMap.containsKey(name_in))
            return true;
        else if (ifLookUp && parentScope != null)
            return parentScope.containType(name_in, pos_in, ifLookUp);
        else return false;
    }

    public basicType getType(String name_in, position pos_in, boolean ifLookUp) {
        if (typeMap.containsKey(name_in))
            return typeMap.get(name_in);
        else if (ifLookUp && parentScope != null)
            return parentScope.getType(name_in, pos_in, ifLookUp);
        else
            throw new semanticError("type undefined", pos_in);
    }

    public void addVariable(String name_in, varSymbol value_in, position pos_in) {
        if (this.containType(name_in, pos_in, true))
            throw new semanticError("type name duplicated", pos_in);
        if (varMap.containsKey(name_in))
            throw new semanticError("variable redefine", pos_in);
        else
            varMap.put(name_in, value_in);
    }

    public boolean containVariable(String name_in, position pos_in, boolean ifLookUp) {
        if (varMap.containsKey(name_in))
            return true;
        else if (ifLookUp && parentScope != null)
            return parentScope.containVariable(name_in, pos_in, ifLookUp);
        else
            return false;
    }

    public varSymbol getVariable(String name_in, position pos_in, boolean ifLookUp) {
        if (varMap.containsKey(name_in))
            return varMap.get(name_in);
        else if (ifLookUp && parentScope != null)
            return parentScope.getVariable(name_in, pos_in, ifLookUp);
        else
            throw new semanticError("variable undefined", pos_in);
    }

    public void addFunction(String name_in, funcSymbol value_in, position pos_in) {
        if (this.containType(name_in, pos_in, true))
            throw new semanticError("type name duplicated", pos_in);
        else if (funcMap.containsKey(name_in))
            throw new semanticError("function redefine", pos_in);
        else
            funcMap.put(name_in, value_in);
    }

    public boolean containFunction(String name_in, position pos_in, boolean ifLookUp) {
        if (funcMap.containsKey(name_in))
            return true;
        else if (ifLookUp && parentScope != null)
            return parentScope.containFunction(name_in, pos_in, ifLookUp);
        else
            return false;
    }

    public funcSymbol getFunction(String name_in, position pos_in, boolean ifLookUp) {
        if (funcMap.containsKey(name_in))
            return funcMap.get(name_in);
        else if (ifLookUp && parentScope != null)
            return parentScope.getFunction(name_in, pos_in, ifLookUp);
        else
            throw new semanticError("function undefined", pos_in);
    }
}
