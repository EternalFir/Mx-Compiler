package IR.instructions;

import IR.Block;
import IR.operand.*;

import java.util.ArrayList;

public class Icmp extends instruction {
    public operand lhs, rhs;
    public String op;

    public Icmp(Block block_in, register register_in, operand lhs_in, operand rhs_in, String op_in) {
        super(block_in, register_in);
        lhs = lhs_in;
        rhs = rhs_in;
        op = op_in;
    }

    public String intoString() {
        return register.intoString() + " = icmp " + op + " " + lhs.irType.intoString() + " " + lhs.intoString() + ", " + rhs.intoString();
    }

    public ArrayList<operand> getUsedOperand() {
        ArrayList<operand> out = new ArrayList<>();
        out.add(lhs);
        out.add(rhs);
        return out;
    }

    public void replace(operand old_in, operand new_in) {
        if (lhs == old_in)
            lhs = new_in;
        if (rhs == old_in)
            rhs = new_in;
    }

}
