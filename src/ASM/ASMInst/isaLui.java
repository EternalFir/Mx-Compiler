package ASM.ASMInst;

import ASM.Value.*;

import java.util.HashSet;
import java.util.Collections;


public class isaLui extends isaInst {
    public Register register;
    public imm imm;

    public isaLui(Register reg_in, imm imm_in) {
        register = reg_in;
        imm = imm_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(register));
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
        if (register == old_reg)
            register = new_reg;
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {

    }

    @Override
    public String intoString() {
        return "lui " + register.intoString() + ", " + imm.intoString();
    }
}
