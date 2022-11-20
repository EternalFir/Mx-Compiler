package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class Load extends instruction {
    public operand address;

    public Load(Block block_in, register register_in, operand address_in) {
        super(block_in, register_in);
        address = address_in;
    }

    public String intoString() {
        return register.intoString() + " = load " + register.irType.intoString() + ", " + address.irType.intoString() + " " + address.intoString();
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(address);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (address == old_in)
            address = new_in;
    }
}
