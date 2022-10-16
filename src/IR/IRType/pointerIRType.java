package IR.IRType;

import IR.operand.operand;

public class pointerIRType extends basicIRType {
    basicIRType ptrType;

    public pointerIRType(basicIRType pointerType_in) {
        ptrType = pointerType_in;
    }

    @Override
    public String intoString() {
        return ptrType.intoString() + "*";
    }

    @Override
    public boolean sameType(basicIRType t) {
        if (t instanceof pointerIRType) {
            return (((pointerIRType) t).ptrType instanceof voidIRType) || ((pointerIRType) t).ptrType instanceof pointerIRType;
        } else {
            return false;
        }
    }
}
