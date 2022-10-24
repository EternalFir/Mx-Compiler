package IR.IRType;

import IR.IRType.basicIRType;
import IR.operand.constBool;
import IR.operand.operand;

public class boolIRType extends basicIRType {
    public boolIRType() {

    }

    public int size() {
        return 1;
    }

    @Override
    public String intoString() {
        return "bool";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof boolIRType);
    }

    public operand getInit() {
        return new constBool(false);
    }

}
