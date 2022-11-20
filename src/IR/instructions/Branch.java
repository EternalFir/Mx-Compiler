package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class Branch extends instruction {
    public operand condition;

    public Block trueBlock, falseBlock;

    public Branch(Block block_in, operand cond_in, Block true_in, Block false_in) {
        super(block_in, null);
        condition = cond_in;
        trueBlock = true_in;
        falseBlock = false_in;
    }

    public String intoString() {
        return "br " + condition.irType.intoString() + " " + condition.intoString() + ", label " + trueBlock.intoString() + ", label " + falseBlock.intoString();
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(condition);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (condition == old_in)
            condition = new_in;
    }
}
