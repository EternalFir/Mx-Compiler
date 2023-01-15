package Backend;

import ASM.*;
import ASM.ASMInst.isaInst;
import IR.operand.*;

import java.io.PrintStream;

public class ASMPrinter {

    public ASM asm;
    public PrintStream printer;

    public ASMPrinter(ASM asm_in, PrintStream ptr_in){
        asm = asm_in;
        printer=ptr_in;
    }

    public void printGlobalVariable(register reg){
        printer.println("\t.globl\t"+reg.name);
        printer.println("\t.type\t" + reg.name + ", @object");
        printer.println(reg.name + ":");
        printer.println("\t.zero\t4");
        printer.println("\t.size\t" + reg.name + ", 4");
    }

    public void printConstString(constString str){
        printer.println(str.name + ":");
        printer.println("\t.string\t\"" + str.surfaceValue + "\"");
    }

    public void printBlock(ASMBlock block) {
        printer.println(block.name + ":");
        for (isaInst inst : block.insts){
            printer.println("\t"+inst.intoString());
        }
    }

    public void printFunc(ASMFunction func) {
        printer.println("\t.globl\t" + func.toString());
        printer.println("\t.type\t" + func.toString() + ", @function");
        printer.println(func.name + ":");
        for (ASMBlock block : func.sonBlocks){
            printBlock(block);
        }
        printer.println("\t.size\t" + func.toString() + ", .-" + func.toString());
    }

    public void print(){
        printer.println("\t.text"); // code part
        asm.functions.forEach((name,func)->printFunc(func));
        printer.println("\t.section\t.bss");
        asm.globalVariables.forEach((name,var)->printGlobalVariable(var));
        printer.println("\t.section\t.constString");
        asm.constStr.forEach((name,str)->printConstString(str));
    }
}
