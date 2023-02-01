package Midend;

import IR.Block;
import IR.IR;
import IR.Function;
import IR.IRType.*;
import IR.operand.*;
import IR.instructions.*;

import java.io.PrintStream;

public class IRPrinter {
    public PrintStream printer;
    public IR iR;

    public IRPrinter(PrintStream stream_in, IR ir_in) {
        printer = stream_in;
        iR = ir_in;
    }

    public void printGlobalVar(register reg_in) {
        printer.println(reg_in.intoString() + " = global " + ((pointerIRType) reg_in.irType).ptrType.intoString() + " zero initializer");
    }

    public void printConstString(constString str_in) {
        printer.println("@" + str_in.name + " = private unnamed_addr constant [" + (str_in.innerValue.length() + 1) + " x i8 c\"" + str_in.convert() + "\\0\",align 1");
    }

    public void printBlock(Block block_in) {
        printer.println(block_in.name + ":");
        for (instruction inst : block_in.inst) {
            printer.println("  " + inst.intoString());
        }
    }

    public void printFunction(Function func_in) {

//        printer.print(func_in.intoString());

        printer.print("define " + func_in.returnType.intoString() + " " + func_in.intoString() + "(");
        for (int i = 0; i < func_in.params.size(); i++) {
            printer.print(func_in.params.get(i).irType.intoString() + " " + func_in.params.get(i).intoString());
            if (i != func_in.params.size() - 1)
                printer.print(", ");
        }
        printer.println("){");
        func_in.blocks.forEach(this::printBlock);
        printer.println("}");
    }

    public void printClass(classIRType class_in) {
        printer.println(class_in.intoString() + " = type {");
        for (int i = 0; i < class_in.var.size(); i++) {
            printer.print(class_in.var.get(i).irType.intoString());
            if (i != class_in.var.size() - 1)
                printer.print(", ");
        }
        printer.println("}");
    }

    public void print() {
        printer.println("declare i8* @__builtin_malloc(i32)");
        printer.println("declare void @__builtin_print(i8*)");
        printer.println("declare void @__builtin_println(i8*)");
        printer.println("declare void @__builtin_printInt(i32)");
        printer.println("declare void @__builtin_printlnInt(i32)");
        printer.println("declare i8* @__builtin_getString()");
        printer.println("declare i32 @__builtin_getInt()");
        printer.println("declare i8* @__builtin_toString(i32)");
        printer.println("declare i32 @__builtin_str_length(i8*)");
        printer.println("declare i8* @__builtin_str_substring(i8*, i32, i32)");
        printer.println("declare i32 @__builtin_str_parseInt(i8*)");
        printer.println("declare i32 @__builtin_str_ord(i8*, i32)");
        printer.println("declare i8* @__builtin_str_add(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_lt(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_gt(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_le(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_ge(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_eq(i8*, i8*)");
        printer.println("declare i1 @__builtin_str_ne(i8*, i8*)");
        iR.classes.forEach((name, classType) -> printClass(classType));
        iR.globalVars.forEach((name, reg) -> printGlobalVar(reg));
        iR.constString.forEach((name, str) -> printConstString(str));
        iR.funcs.forEach((name, func) -> printFunction(func));
    }
}
