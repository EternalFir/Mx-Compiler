package IR.operand;

import IR.IRType.basicIRType;

public class Null extends operand {
    public Null() {
        super(null);
    }

    public Null(basicIRType type) {
        super(type);
    }

    public String intoString() {
        return "null";
    }

    public boolean sameOperand(operand o) {
        return o instanceof Null;
    }
}
