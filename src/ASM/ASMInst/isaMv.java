package ASM.ASMInst;

import ASM.Value.*;

import java.util.HashSet;
import java.util.Collections;

public class isaMv extends isaInst {
    public Register register;
    public Register src;

    public isaMv(Register reg_in, Register src_in) {
        register = reg_in;
        src = src_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(register));
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>(Collections.singletonList(src));
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
        if (register == old_reg)
            register = new_reg;
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {
        if (src == old_reg)
            src = new_reg;
    }

    @Override
    public String intoString() {
        return "mv "+register.intoString()+", "+src.intoString();
    }
}
