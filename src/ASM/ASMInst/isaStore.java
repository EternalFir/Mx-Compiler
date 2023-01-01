package ASM.ASMInst;

import ASM.Value.*;

import java.util.HashSet;
import java.util.Arrays;

public class isaStore extends isaInst {
    public Register address;
    public Register value;
    public imm offset;
    public int size;

    public isaStore(Register addr_in, Register value_in, imm offset_in, int size_in) {
        address = addr_in;
        value = value_in;
        offset = offset_in;
        size = size_in;
    }

    @Override
    public HashSet<Register> getDefinition() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Register> getUsage() {
        return new HashSet<>(Arrays.asList(value, address));
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {
    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {
        if (address == old_reg)
            address = new_reg;
        if (value == old_reg)
            value = new_reg;
    }

    @Override
    public String intoString() {
        return "sw " + value.intoString() + ", " + offset.intoString() + "(" + address.intoString() + ")";
    }
}
