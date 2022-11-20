package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public abstract class instruction {
    public Block block;
    public register register;

    public instruction(Block block_in, register register_in) {
        block = block_in;
        register = register_in;
    }

    public abstract String intoString();

    public ArrayList<operand> getUsedOperand() {
        return new ArrayList<operand>();
    }

    public abstract void replace(operand old_in, operand new_in);
}
