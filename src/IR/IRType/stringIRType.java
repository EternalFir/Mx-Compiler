package IR.IRType;

import IR.operand.operand;

public class stringIRType extends basicIRType {
    public stringIRType() {

    }

    @Override
    public String intoString() {
        return "string";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof stringIRType);
    }
}
