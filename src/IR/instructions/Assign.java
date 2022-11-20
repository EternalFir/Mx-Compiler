package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class Assign extends instruction {
    public operand value;

    public Assign(Block block_in, register register_in, operand value_in) {
        super(block_in, register_in);
        value = value_in;
    }

    public String intoString() {
        if (value.irType != null)
            return register.intoString() + " = " + value.irType.intoString() + " " + value.intoString();
        else
            return register.intoString() + " =  " + value.intoString();
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(value);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (value == old_in)
            value = new_in;
    }
}
