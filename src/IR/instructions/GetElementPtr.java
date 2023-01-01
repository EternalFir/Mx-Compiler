package IR.instructions;

import IR.Block;
import IR.operand.*;
import IR.IRType.pointerIRType;

import java.util.ArrayList;

public class GetElementPtr extends instruction {
    public operand base, index;
    public constInt offset = null;

    public GetElementPtr(Block block_in, operand register_in, operand base_in, operand index_in) {
        super(block_in, (register) register_in);
        base = base_in;
        index = index_in;
    }

    public GetElementPtr(Block block_in, operand register_in, operand base_in, operand index_in, constInt offset_in) {
        super(block_in, (register) register_in);
        base = base_in;
        index = index_in;
        offset = offset_in;
    }

    public String intoString() {
        return register.intoString() + " = getelementptr inbounds " + ((pointerIRType) base.irType).ptrType.intoString() + ", " + base.irType.intoString() + " " + base.intoString() + ", " + index.irType.intoString() + " " + index.intoString() + (offset != null ? ", " + offset.irType.intoString() + " " + offset.intoString() : "");
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(base);
        out.add(index);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (base == old_in)
            base = new_in;
        if (index == old_in)
            index = new_in;
    }

}
