package IR.IRType;

import IR.IRType.basicIRType;
import IR.operand.operand;

public class boolIRType extends basicIRType{
    public boolIRType(){

    }



    @Override
    public String intoString() {
        return "bool";
    }

    @Override
    public boolean sameType(basicIRType t) {
        return (t instanceof boolIRType);
    }

}
