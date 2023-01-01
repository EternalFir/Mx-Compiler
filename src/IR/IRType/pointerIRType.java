package IR.IRType;

import IR.operand.operand;
import IR.operand.Null;

public class pointerIRType extends basicIRType {
    public basicIRType ptrType;

    public pointerIRType(basicIRType pointerType_in) {
        ptrType = pointerType_in;
    }

    public int size(){
        return 32;
    }

    @Override
    public String intoString() {
        return ptrType.intoString() + "*";
    }

    @Override
    public boolean sameType(basicIRType t) {
        if (t instanceof pointerIRType) {
            return ((((pointerIRType) t).ptrType instanceof voidIRType) || ((pointerIRType) t).ptrType.sameType(ptrType));
        } else {
            return false;
        }
    }

    public operand getInit(){
        return new Null();
    }
}
