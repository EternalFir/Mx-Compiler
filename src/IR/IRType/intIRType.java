package IR.IRType;

import IR.operand.operand;
import IR.operand.constInt;

public class intIRType extends basicIRType{
    public int size;

    public intIRType (int size_in){
        size=size_in;
    }

    public int size(){
        return size;
    }

    @Override
    public String intoString() {
        return "int"+size;
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof intIRType);
    }

    public operand getInit(){
        return new constInt(0,32);
    }
}
