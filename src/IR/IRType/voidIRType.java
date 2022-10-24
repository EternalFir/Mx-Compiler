package IR.IRType;

import IR.operand.*;
import Utility.errors.innerError;

public class voidIRType extends basicIRType {
    public voidIRType() {

    }

    public int size(){
        throw new innerError("want void size");
    }

    @Override
    public String intoString() {
        return "void";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof voidIRType);
    }

    public operand getInit(){
        return new constVoid();
    }
}
