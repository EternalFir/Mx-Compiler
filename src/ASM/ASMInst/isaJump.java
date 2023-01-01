package ASM.ASMInst;
import ASM.ASMBlock;
import ASM.Value.*;

import java.util.HashSet;

public class isaJump extends isaInst{
    public ASMBlock destination;

    public isaJump(ASMBlock dest_in){
        destination=dest_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>();
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {
    }

    @Override
    public String intoString() {
        return "jump "+ destination.intoString();
    }
}
