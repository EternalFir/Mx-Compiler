package IR.IRType;

import IR.operand.operand;

public class intIRType extends basicIRType{
    public int size;

    public intIRType (int size_in){
        size=size_in;
    }

    @Override
    public String intoString() {
        return "int"+size;
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof intIRType);
    }
}
