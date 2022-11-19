package IR.IRType;

import IR.operand.Null;
import IR.operand.operand;

public class stringIRType extends basicIRType {
    public stringIRType() {

    }

    public int size(){
        return new pointerIRType(null).size();
    }

    @Override
    public String intoString() {
        return "i8*";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof stringIRType);
    }

    public operand getInit(){
        return new Null();
    }
}
