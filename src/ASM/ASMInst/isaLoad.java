package ASM.ASMInst;

import ASM.Value.*;

import java.util.Collections;
import java.util.HashSet;

public class isaLoad extends isaInst{
    public Register register;
    public Register address;
    public imm offset;
    public int size;

    public isaLoad(Register reg_in, Register addr_in, imm offset_in, int size_in){
        register=reg_in;
        address=addr_in;
        offset=offset_in;
        size=size_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>(Collections.singletonList(register));
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>(Collections.singletonList(address));
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
        if(register==old_reg)
            register=new_reg;
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {
        if(address==old_reg)
            address=new_reg;
    }

    @Override
    public String intoString() {
        return "lw "+register.intoString()+", "+offset.intoString()+"("+address.intoString()+")";
    }
}
