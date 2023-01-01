package Backend;

import ASM.*;
import ASM.ASMInst.*;
import ASM.Value.*;
import IR.instructions.Binary;

import java.util.ArrayList;
import java.util.LinkedList;


public class RegAllocator {
    ASM asm;
    int stackNum=0;
    int vRegBeginPos;
    ArrayList<isaInst> workList=null;


    public RegAllocator(ASM asm_in) {
        asm = asm_in;
    }

    public void allocaVReg(Register objectReg, Register src) {
        if (!src.isPhy) {
            int offset = stackNum;
            stackNum++;
            if (offset < (1 << 11)) {
                workList.add(new isaLoad(objectReg, src, new imm(offset), 4));
            } else {
                workList.add(new isaLi(asm.getPhyReg("t2"), new imm(offset)));
//                workList.add(new isaCalc("add", objectReg, new imm(objectReg.value), new imm(asm.getPhyReg("t2").value)));
                workList.add(new isaStore(objectReg, asm.getPhyReg("t2"), new imm(offset), 4));
            }
        }
    }

    public void runBlock(ASMBlock block) {
        workList = new ArrayList<isaInst>();
        for (isaInst inst : block.insts) {
            Register regNow = null;
            if (inst instanceof isaBranch) {
                regNow = ((isaBranch) inst).rs1;
                if (regNow != null && !regNow.isPhy) {
                    allocaVReg(asm.getPhyReg("t1"), regNow);
                }
                regNow = ((isaBranch) inst).rs2;
                if (regNow != null && !regNow.isPhy) {
                    allocaVReg(asm.getPhyReg("t2"), regNow);
                }
            } else if (inst instanceof isaCalc) {
                regNow = ((isaCalc) inst).rd;
                if (regNow != null && !regNow.isPhy) {
                    allocaVReg(asm.getPhyReg("t0"), regNow);
                }
            } else if(inst instanceof isaLi){
                regNow=((isaLi)inst).register;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t0"),regNow);
                }
            }else if(inst instanceof isaLoad){
                regNow=((isaLoad)inst).address;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t1"),regNow);
                }
                regNow=((isaLoad)inst).register;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t0"),regNow);
                }
            }else if(inst instanceof  isaLui){
                regNow=((isaLui)inst).register;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t0"),regNow);
                }
            }else if(inst instanceof  isaMv){
                regNow=((isaMv)inst).src;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t1"),regNow);
                }
                regNow=((isaMv)inst).register;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t0"),regNow);
                }
            }else if(inst instanceof isaStore){
                regNow= ((isaStore)inst).value;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t1"),regNow);
                }
                regNow=((isaStore)inst).address;
                if(regNow != null && !regNow.isPhy){
                    allocaVReg(asm.getPhyReg("t0"),regNow);
                }
            }
        }
        block.insts=workList;
    }

    public void runFunction(ASMFunction func){
        vRegBeginPos=stackNum;
        for (ASMBlock block : func.sonBlocks){
            runBlock(block);
        }
    }

    public void run(){
        asm.functions.forEach((name,func)->runFunction(func));
    }

}
