package ASM.ASMInst;

import ASM.ASM;
import ASM.Value.Register;

import java.util.HashSet;
import java.util.Collections;

public class isaRet extends isaInst {
    public ASM asm;

    public isaRet(ASM asm_in) {
        asm = asm_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>(Collections.singletonList(asm.getPhyReg("ra")));
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {

    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {

    }

    @Override
    public String intoString() {
        return "ret";
    }
}
