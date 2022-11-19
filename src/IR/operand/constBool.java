package IR.operand;

import IR.IRType.boolIRType;

public class constBool extends operand{
    public boolean value;
    public constBool(boolean value_in){
        super(new boolIRType());
        value=value_in;
    }

    public String intoString(){
        if(value)
            return "1";
        else
            return "0";
    }

    public boolean sameOperand(operand o){
        return (o instanceof constBool && ((constBool)o).value==value);
    }
}
