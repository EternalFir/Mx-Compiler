package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class Jump extends instruction {

    public Block destination;

    public Jump(Block block_in, Block dest_in) {
        super(block_in, null);
        destination = dest_in;
    }

    public String intoString() {
        return "br label " + destination.intoString();
    }

    public void replace(operand old_in, operand new_in) {
    }
}
