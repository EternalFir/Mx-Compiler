package IR.instructions;

// for SSA

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;


public class Phi extends instruction {
    public ArrayList<Block> blocks = new ArrayList<>();
    public ArrayList<operand> values = new ArrayList<>();
    public register phiRegister;
    public boolean isMain = false;

    public Phi(Block block_in, register register_in) {
        super(block_in, register_in);
        phiRegister = register_in;
    }

    public String intoString() {
        StringBuilder out = new StringBuilder(register.intoString() + " = phi " + register.irType.intoString() + " ");
        for (int i = 0; i < values.size(); i++) {
            out.append("[ ");
            out.append(values.get(i).intoString());
            out.append(", ");
            out.append(blocks.get(i).intoString());
            out.append(" ]");
            if (i != blocks.size() - 1)
                out.append(", ");
        }
        return out.toString();
    }

    public ArrayList<operand> getUsedOperand() {
        return values;
    }

    public void replace(operand old_in, operand new_in) {
        for (int i = 0; i < values.size(); ++i) {
            if (values.get(i) == old_in)
                values.set(i, new_in);
        }
    }

    public void add(Block block_in, operand value_in) {
        blocks.add(block_in);
        values.add(value_in);
    }
}
