package IR.IRType;

import IR.operand.operand;

public class voidIRType extends basicIRType {
    public voidIRType() {

    }

    @Override
    public String intoString() {
        return "void";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof voidIRType);
    }
}
