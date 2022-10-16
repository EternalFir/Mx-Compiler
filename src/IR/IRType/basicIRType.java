package IR.IRType;

import IR.operand.operand;

import java.util.stream.Stream;

public abstract class basicIRType {
    public basicIRType() {

    }

//    public abstract int size();

    public abstract String intoString();

    public abstract boolean sameType(basicIRType t);

//    public abstract operand getInit();

}
