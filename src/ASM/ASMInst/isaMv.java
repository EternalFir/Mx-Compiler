package ASM.ASMInst;

import ASM.Value.*;

import java.util.HashSet;
import java.util.Collections;

public class isaMv extends isaInst {
    public Register rd;
    public Register rs;

    public isaMv(Register rd_in, Register rs_in) {
        rd = rd_in;
        rs = rs_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(rd));
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>(Collections.singletonList(rs));
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
        if (rd == old_reg)
            rd = new_reg;
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {
        if (rs == old_reg)
            rs = new_reg;
    }

    @Override
    public String intoString() {
        return "mv "+rd.intoString()+", "+rs.intoString();
    }
}
