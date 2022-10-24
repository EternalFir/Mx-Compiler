package IR.operand;

import IR.IRType.stringIRType;

import java.security.PublicKey;

public class constString extends operand{
    public String name;
    public String surfaceValue;
    public String innerValue;

    public constString(String name_in,String value_in){
        super(new stringIRType());
        name=name_in;
        surfaceValue=value_in;
        innerValue=surfaceValue.replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\\"", "\"");
    }


    public String intoString(){
        return surfaceValue;
    }

    public boolean sameOperand(operand o){
        return (o instanceof constString &&((constString)o).surfaceValue.equals(surfaceValue));
    }
}
