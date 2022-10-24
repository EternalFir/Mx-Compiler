package IR.operand;

import IR.IRType.intIRType;

public class constInt extends operand {
    public int value;

    public constInt(int value_in, int size_in) {
        super(new intIRType(size_in));
        value = value_in;
    }

    public String intoString() {
        return Integer.toString(value);
    }

    public boolean sameOperand(operand o){
        return (o instanceof constInt && ((constInt) o).value==value);
    }
}
