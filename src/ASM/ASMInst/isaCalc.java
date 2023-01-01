package ASM.ASMInst;

import ASM.Value.*;

import java.util.Collections;
import java.util.HashSet;

public class isaCalc extends isaInst {
    public String op;
    public Register rd;
    public data rs1, rs2;

    public isaCalc(String op_in, Register rd_in, data rs1_in, data rs2_in) {
        op = op_in;
        rd = rd_in;
        rs1 = rs1_in;
        rs2 = rs2_in;
    }

    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(rd)); // set length when define
    }

    public HashSet<Register> getUsage() {
        HashSet<Register> out = new HashSet<>();
        if (rs1 instanceof Register)
            out.add((Register) rs1);
        if (rs2 instanceof Register)
            out.add((Register) rs2);
        return out;
    }

    public void replaceDef(Register old_reg, Register new_reg) {
        if (rd == old_reg)
            rd = new_reg;
    }

    public void replaceUsage(Register old_in, Register new_in) {
        if (rs1 == old_in)
            rs1 = new_in;
        if (rs2 == old_in)
            rs2 = new_in;
    }

    public String intoString() {
        return op + " " + rd.intoString() + ", " + rs1.intoString() + ", " + rs2.intoString();
    }

}
