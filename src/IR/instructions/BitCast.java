package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class BitCast extends instruction {
    public operand value;

    public BitCast(Block block_in, operand register_in, operand value_in) {
        super(block_in, (register) register_in);
        value = value_in;
    }

    @Override
    public String intoString() {
        return register.intoString() + " = bitcast " + value.irType.intoString() + " " + value.intoString() + " to " + register.irType.intoString();
    }

    @Override
    public void replace(operand old_in, operand new_in) {
        if (value == old_in)
            value = new_in;
    }

    @Override
    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(value);
        return out;
    }
}
