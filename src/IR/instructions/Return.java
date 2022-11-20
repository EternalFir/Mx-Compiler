package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;


public class Return extends instruction {
    public operand value;

    public Return(Block block_in, operand value_in) {
        super(block_in, null);
        value = value_in;
    }

    public String intoString() {
        if (value instanceof constVoid)
            return "ret void";
        else
            return "ret " + value.irType.intoString() + " " + value.intoString();
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
