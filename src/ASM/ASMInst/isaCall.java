package ASM.ASMInst;

import ASM.*;
import ASM.Value.*;

import java.util.HashSet;

public class isaCall extends isaInst{
    public ASMFunction function;
    public ASM asm;

    public isaCall(ASMFunction func_in, ASM asm_in){
        function=func_in;
        asm=asm_in;
    }

    public HashSet<Register> getDefinition(){
        return new HashSet<>(asm.getTempFuncArgumentReg());
    }

    @Override
    public HashSet<Register> getUsage() {
        HashSet<Register> out= new HashSet<>();
        if(function.paramList.size()>8){ //参数过多，reg不够用
            for(int i=0;i<8;i++){
                out.add(asm.getPhyReg(10+i));
            }
        } else {
            for (int i=0;i<function.paramList.size();i++){
                out.add(asm.getPhyReg(i+10));
            }
        }
        return out;
    }

    @Override
    public void replaceDef(Register old_reg, Register new_reg) {

    }

    @Override
    public void replaceUsage(Register old_reg, Register new_reg) {

    }

    @Override
    public String intoString() {
        return "call "+ function.intoString();
    }

}
