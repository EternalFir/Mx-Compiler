package IR.IRType;

import IR.IRType.basicIRType;
import IR.operand.constBool;
import IR.operand.operand;

public class boolIRType extends basicIRType {
    public boolIRType() {

    }

    public int size() {
        return 32;
    }

    @Override
    public String intoString() {
        return "i1";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof boolIRType);
    }

    public operand getInit() {
        return new constBool(false);
    }

}
