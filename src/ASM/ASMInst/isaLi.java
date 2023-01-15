package ASM.ASMInst;

import ASM.Value.*;

import java.util.HashSet;
import java.util.Collections;

public class isaLi extends isaInst {
    public Register rd;
    public imm imm;

    public isaLi(Register reg_in, imm imm_in) {
        rd = reg_in;
        imm = imm_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(rd));
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
        if (rd == old_reg)
            rd = new_reg;
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {

    }

    @Override
    public String intoString() {
        return "Li "+rd.intoString()+", "+imm.intoString();
    }
}
