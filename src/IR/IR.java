package IR;

import AST.Types.*;
import IR.operand.*;
import IR.IRType.*;

import java.util.HashMap;

public class IR {
    public HashMap<String, Function> func = new HashMap<>();
    public HashMap<String, register> var = new HashMap<>();
    public HashMap<String, classIRType> classes = new HashMap<>();
    public HashMap<String, constString> constString = new HashMap<>();
    public Function mallocFunc;
    public Block destBlock;

    public IR() {
        func.put("__init", new Function("__init"));
        destBlock = func.get("__init").beginBlock;
        mallocFunc = new Function("__builtIn__malloc");
        mallocFunc.returnType = new pointerIRType(new intIRType(8));
    }

    public basicIRType getType(basicType type_in) {
        if (type_in instanceof arrayType) {
            basicIRType t = getType(((arrayType) type_in).atomType);
            for (int i = 0; i < ((arrayType) type_in).dimen; i++) {
                t = new pointerIRType(t);
            }
            return t;
        }
        if (type_in instanceof classType) {
            return new pointerIRType(((classType) type_in).classIrType);
        }
        if (type_in instanceof primitiveType) {
            if (type_in.isBool())
                return new boolIRType();
            if (type_in.isInt())
                return new intIRType(32);
            if (type_in.isString())
                return new stringIRType();
            if (type_in.isVoid())
                return new voidIRType();
            if (type_in.isNull())
                return new voidIRType();
        }
        return new voidIRType();
    }
}
