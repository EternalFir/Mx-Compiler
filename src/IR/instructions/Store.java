package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;


public class Store extends instruction {
    public operand address;
    public operand value;

    public Store(Block block_in, operand address_in, operand value_in) {
        super(block_in, null);
        address = address_in;
        value = value_in;
    }

    public String intoString() {
        return "store " + value.irType.intoString() + " " + value.intoString() + ", " + address.irType.intoString() + " " + address.intoString();
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(address);
        out.add(value);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (address == old_in)
            address = new_in;
        if (value == old_in)
            value = new_in;
    }
}
