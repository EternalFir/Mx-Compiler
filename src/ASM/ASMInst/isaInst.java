package ASM.ASMInst;

import ASM.Value.Register;

import java.util.HashSet;

public abstract class isaInst {
    public abstract HashSet<Register> getDefinition();

    public abstract HashSet<Register> getUsage();

    public abstract void replaceDef(Register old_reg, Register new_reg);

    public abstract void replaceUsage(Register old_reg, Register new_reg);

    public abstract String intoString();
}
