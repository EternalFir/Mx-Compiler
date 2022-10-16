package IR.operand;

import IR.IRType.basicIRType;

import java.util.Optional;

public abstract class operand {
    public basicIRType irType;

    public operand(basicIRType type_in){
        irType=type_in;
    }

    public abstract String intoString();

    public abstract boolean sameOperand(operand o);
}
