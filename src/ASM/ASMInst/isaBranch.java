package ASM.ASMInst;

import ASM.Value.Register;
import ASM.ASMBlock;

import Utility.errors.*;

import java.util.Arrays;
import java.util.HashSet;

public class isaBranch extends isaInst {
    public String op;
    public Register rs1, rs2;
    public ASMBlock destination;

    public isaBranch(String op_in, Register rs1_in, Register rs2_in, ASMBlock dest_in) {
        op = op_in;
        rs1 = rs1_in;
        rs2 = rs2_in;
        destination = dest_in;
    }

    public HashSet<Register> getDefinition() {
        return new HashSet<>();
    }

    public HashSet<Register> getUsage() {
        return new HashSet<>(Arrays.asList(rs1, rs2));
    }

    public void replaceDef(Register old_reg, Register new_reg) {
        throw new innerError("Invalid call: isaBranch.replaceDef");
    }

    public void replaceUsage(Register old_in, Register new_in) {
        if (rs1 == old_in)
            rs1 = new_in;
        if (rs2 == old_in)
            rs2 = new_in;
    }

    public String intoString() {
        return op + " " + rs1.intoString() + ", " + rs2.intoString() + ", " + destination.intoString();
    }
}
