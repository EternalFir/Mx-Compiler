package Backend;


import IR.IR;
import IR.Function;
import IR.Block;
import IR.instructions.*;
import IR.operand.operand;
import IR.operand.register;
import IR.operand.*;
import IR.IRType.*;
import ASM.*;
import ASM.ASMInst.*;
import ASM.Value.*;

import Utility.errors.innerError;

import java.util.HashMap;
import java.util.HashSet;


public class ASMBuilder {
    public IR ir;
    public ASM asm;

    public HashMap<Function, ASMFunction> functionMap = new HashMap<>();
    public HashMap<operand, Register> regMap = new HashMap<>();
    public ASMFunction functionNow = null;
    public ASMBlock blockNow = null;
    public HashMap<Block, ASMBlock> blockMap = new HashMap<>();

    public ASMBuilder(IR ir_in, ASM asm_in) {
        ir = ir_in;
        asm = asm_in;
    }

    public void assign(Register register, operand operand) {
        if (operand instanceof register) {
            blockNow.addInst(new isaMv(register, getReg(operand)));
        } else {
            int value = 0;
            if (operand instanceof constBool) {
                if (((constBool) operand).value)
                    value = 1;
                else
                    value = 0;
            }
            if (operand instanceof constInt)
                value = ((constInt) operand).value;
            if (operand instanceof constString) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaLui(tempReg, new addr(1, ((constString) operand).name)));
                blockNow.addInst(new isaCalc("addi", register, tempReg, new addr(0, ((constString) operand).name)));
                return;
            }
            blockNow.addInst(new isaLi(register, new imm(value)));
        }
    }

    public Register getReg(operand operand) {
        if (operand instanceof register) {
            if (((register) operand).isGlobal) {
                throw new innerError("getReg");
            } else {
                if (regMap.get(operand) == null) // first time
                    regMap.put(operand, new Register(((register) operand).name, false));
                return regMap.get(operand);
            }
        } else {
            int value = 0;
            if (operand instanceof constBool) {
                if (((constBool) operand).value)
                    value = 1;
                else
                    value = 0;
            }
            if (operand instanceof constInt)
                value = ((constInt) operand).value;
            if (operand instanceof constString) {
                Register tempReg1 = new Register("tmp", false);
                Register tempReg2 = new Register("tmp", false);
                blockNow.addInst(new isaLui(tempReg1, new addr(1, ((constString) operand).name)));
                blockNow.addInst(new isaCalc("addi", tempReg2, tempReg1, new addr(0, ((constString) operand).name)));
                return tempReg2;
            }
            Register tempReg = new Register("tmp", false);
            blockNow.addInst(new isaLi(tempReg, new imm(value)));
            return tempReg;
        }
    }


    public ASMBlock getBlock(Block block) {
        if (blockMap.get(block) == null) // first time
            blockMap.put(block, new ASMBlock(block.loopDepth));
        return blockMap.get(block);
    }

    public ASMFunction getFunction(Function function) {
        if (functionMap.get(function) == null) // first time
            functionMap.put(function, new ASMFunction(function.name));
        return functionMap.get(function);
    }

    public void runInst(instruction inst) {
        if (inst instanceof Assign) {
            assign(getReg(inst.register), ((Assign) inst).value);
        } else if (inst instanceof Binary) {
            String op;
            Register rd = getReg(inst.register);
            Register rs1;
            data rs2;
            // set op
            if (((Binary) inst).op.equals("sdiv")) { // /
                op = "div";
            } else if (((Binary) inst).op.equals("srem")) { // %
                op = "rem";
            } else if (((Binary) inst).op.equals("shl")) { // <<
                op = "sll";
            } else if (((Binary) inst).op.equals("ashr")) { // >>
                op = "sra";
            } else {
                op = ((Binary) inst).op;
            }
            // set V
            if (op.equals("mul") || op.equals("div") || op.equals("rem")) {
                rs1 = getReg(((Binary) inst).lhs);
                rs2 = getReg(((Binary) inst).rhs);
            } else {
                if (((Binary) inst).rhs instanceof constInt) {
                    if (canInImm(((constInt) ((Binary) inst).rhs).value)) { // 能直接放进imm
                        rs1 = getReg(((Binary) inst).lhs);
                        rs2 = new imm(((constInt) ((Binary) inst).rhs).value);
                        if (op.equals("sub")) {
                            op = "add";
                            rs2.value = -rs2.value;


//                            System.out.println("dbg: sub");
//                            System.out.println(inst.intoString());
                        }
                        op = op + "i";
                    } else {
                        rs1 = getReg(((Binary) inst).lhs);
                        rs2 = new Register("tmp", false);
                        blockNow.addInst(new isaLi((Register) rs2, new imm(((constInt) ((Binary) inst).rhs).value)));
                    }
                } else if (((Binary) inst).lhs instanceof constInt) {
                    if (!(op.equals("sub") || op.equals("sll") || op.equals("sra"))) { // 可换位
                        if (canInImm(((constInt) ((Binary) inst).lhs).value)) {
                            rs1 = getReg(((Binary) inst).rhs);
                            rs2 = new imm(((constInt) ((Binary) inst).lhs).value);
                            op = op + "i";
                        } else {
                            rs1 = getReg(((Binary) inst).rhs);
                            rs2 = new Register("tmp", false);
                            blockNow.addInst(new isaLi((Register) rs2, new imm(((constInt) ((Binary) inst).lhs).value)));
                        }
                    } else {
                        rs1 = getReg(((Binary) inst).lhs);
                        rs2 = getReg(((Binary) inst).rhs);
                    }
                } else {
                    rs1 = getReg(((Binary) inst).lhs);
                    rs2 = getReg(((Binary) inst).rhs);
                }
            }
            blockNow.addInst(new isaCalc(op, rd, rs1, rs2));
        } else if (inst instanceof BitCast) {
            blockNow.addInst(new isaMv(getReg(inst.register), getReg(((BitCast) inst).value)));
        } else if (inst instanceof Branch) {
            String op;
            Icmp temp = null;
            for (instruction inst_in : inst.block.inst) {
                if (inst_in instanceof Icmp && inst_in.register == ((Branch) inst).condition)
                    temp = (Icmp) inst_in;
            }
            if (temp != null) {
                if (temp.op.equals("slt"))
                    op = "blt";
                else if (temp.op.equals("sgt"))
                    op = "bgt";
                else if (temp.op.equals("sle"))
                    op = "ble";
                else if (temp.op.equals("sge"))
                    op = "bge";
                else if (temp.op.equals("eq"))
                    op = "beq";
                else if (temp.op.equals("ne"))
                    op = "bne";
                else
                    throw new innerError("runInst: isaBranch");
                blockNow.addInst(new isaBranch(op, getReg(temp.lhs), getReg(temp.rhs), getBlock(((Branch) inst).trueBlock)));
                blockNow.addInst(new isaJump(getBlock(((Branch) inst).falseBlock)));
            } else {
                blockNow.addInst(new isaBranch("bne", getReg(((Branch) inst).condition), asm.getPhyReg(0), getBlock(((Branch) inst).trueBlock)));
                blockNow.addInst(new isaJump(getBlock(((Branch) inst).falseBlock)));
            }
        } else if (inst instanceof Call) {
            if (((Call) inst).params.size() <= 8) { // 8个reg够用
                for (int i = 0; i < ((Call) inst).params.size(); i++) {
                    blockNow.addInst(new isaMv(asm.getPhyReg(10 + i), getReg(((Call) inst).params.get(i))));
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    blockNow.addInst(new isaMv(asm.getPhyReg(10 + i), getReg(((Call) inst).params.get(i))));
                }
                for (int i=8;i<((Call) inst).params.size();i++){
                    blockNow.addInst(new isaStore(asm.getPhyReg("sp"),getReg(((Call) inst).params.get(i)),new imm((i-((Call) inst).params.size())*4),4));
                }
            }
            blockNow.addInst(new isaCall(getFunction(((Call) inst).function),asm));
            if(inst.register!= null) // has return value
                blockNow.addInst(new isaMv(getReg(inst.register),asm.getPhyReg(10)));
        } else if (inst instanceof GetElementPtr) {
            if (((GetElementPtr) inst).base instanceof Null) // 空对象
                return;
            Register base = getReg(((GetElementPtr) inst).base);
            basicIRType type = ((pointerIRType) ((GetElementPtr) inst).base.irType).ptrType;
            int offset = 0;
            if (((GetElementPtr) inst).offset != null) {
                offset = ((classIRType) type).getOffset(((GetElementPtr) inst).offset.value) / 8; // bite into Byte
            }
            if (((GetElementPtr) inst).index instanceof constInt) { // 常数下标
                int imm = ((constInt) ((GetElementPtr) inst).index).value * type.size() / 8 + offset;
                blockNow.addInst(new isaCalc("addi", getReg(inst.register), base, new imm(imm)));
            } else { // 变量下标等
                Register temp = new Register("tmp", false);
                int size = type.size() / 8;
                int length = 31 - Integer.numberOfLeadingZeros(size);
                if (1 << (length) == size) { // 刚好对齐
                    blockNow.addInst(new isaCalc("slli", temp, getReg(((GetElementPtr) inst).index), new imm(length)));
                } else {
                    blockNow.addInst(new isaCalc("mul", temp, getReg(((GetElementPtr) inst).index), getReg(new constInt(size, 32))));
                }
                if (offset == 0) {
                    blockNow.addInst(new isaCalc("add", getReg(inst.register), base, temp));
                } else {
                    Register temp1 = new Register("tmp", false);
                    blockNow.addInst(new isaCalc("add", temp1, base, temp));
                    blockNow.addInst(new isaCalc("addi", getReg(inst.register), temp1, new imm(offset)));
                }
            }
        } else if (inst instanceof Icmp) {
            String op = ((Icmp) inst).op;

//            System.out.println("Icmp in");
//            System.out.println(((Icmp) inst).lhs.intoString());
//            System.out.println(((Icmp) inst).rhs.intoString());


            if (op.equals("slt")) {
                blockNow.addInst(new isaCalc("slt", getReg(inst.register), getReg(((Icmp) inst).lhs), getReg(((Icmp) inst).rhs)));
            } else if (op.equals("sgt")) {
                blockNow.addInst(new isaCalc("slt", getReg(inst.register), getReg(((Icmp) inst).rhs), getReg(((Icmp) inst).lhs)));
            } else if (op.equals("sle")) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaCalc("slt", tempReg, getReg(((Icmp) inst).rhs), getReg(((Icmp) inst).lhs)));
                blockNow.addInst(new isaCalc("xori", getReg(inst.register), tempReg, new imm(1)));
            } else if (op.equals("sge")) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaCalc("slt", tempReg, getReg(((Icmp) inst).lhs), getReg(((Icmp) inst).rhs)));
                blockNow.addInst(new isaCalc("xori", getReg(inst.register), tempReg, new imm(1)));
            } else if (op.equals("eq")) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaCalc("xor", tempReg, getReg(((Icmp) inst).lhs), getReg(((Icmp) inst).rhs)));
                blockNow.addInst(new isaCalc("sltiu", getReg(inst.register), tempReg, new imm(1)));
            } else if (op.equals("ne")) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaCalc("xor", tempReg, getReg(((Icmp) inst).lhs), getReg(((Icmp) inst).rhs)));
                blockNow.addInst(new isaCalc("sltiu", getReg(inst.register), asm.getPhyReg(0), tempReg)); // 直接和 x0 比较即可
            }
        } else if (inst instanceof Jump) {
            blockNow.addInst(new isaJump(getBlock(((Jump) inst).destination)));
        } else if (inst instanceof Phi) {

//            System.out.println(inst.intoString());

            throw new innerError("runInst: Phi"); // 理论上所有phi都已经被消掉了啊
        } else if (inst instanceof Return) {
            if (((Return) inst).value != null)
                assign(asm.getPhyReg(10), ((Return) inst).value);
            int count = 0;
            for (Register reg : asm.getPSaveReg()) {
                blockNow.addInst(new isaMv(reg, functionNow.calleeList.get(count)));
                count++;
            }

//            System.out.println(count);

            blockNow.addInst(new isaMv(asm.getPhyReg(1), functionNow.raReg)); // 放入 return address
            blockNow.addInst(new isaRet(asm));
            functionNow.endBlock = blockNow;
        } else if (inst instanceof Store) {
            if (((Store) inst).address instanceof register && ((register) ((Store) inst).address).isGlobal) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaLui(tempReg, new addr(1, ((register) ((Store) inst).address).name)));
                blockNow.addInst(new isaStore(getReg(((Store) inst).value), tempReg, new addr(0, ((register) ((Store) inst).address).name), 4));
            } else {
                blockNow.addInst(new isaStore(getReg(((Store) inst).value), getReg(((Store) inst).address), new imm(0), 4));
            }
        } else if (inst instanceof Load) {
            if (((Load) inst).address instanceof register && ((register) ((Load) inst).address).isGlobal) {
                Register tempReg = new Register("tmp", false);
                blockNow.addInst(new isaLui(tempReg, new addr(1, ((register) ((Load) inst).address).name)));
                blockNow.addInst(new isaLoad(getReg(inst.register), tempReg, new addr(0, ((register) ((Load) inst).address).name), 4)); // 一次 load 4 Byte
            } else {
                blockNow.addInst(new isaLoad(getReg(inst.register), getReg(((Load) inst).address), new imm(0), 4));
            }
        } else {
            throw new innerError("runInst: error IR inst type in.");
        }
    }

    public void instCut() {
        boolean updated = true;
        while (updated) {
            int num = 0;

            updated = false;
            HashSet<Register> visited = new HashSet<>();
            visited.add(asm.getPhyReg(10));
            for (ASMBlock b : functionNow.sonBlocks) {
                for (isaInst i : b.insts) {
                    visited.addAll(i.getUsage());
                }
            }
            for (ASMBlock block : functionNow.sonBlocks) {
                for (int i = 0; i < block.insts.size(); i++) {
                    isaInst inst = block.insts.get(i);
                    if ((inst instanceof isaLi && !visited.contains(((isaLi) inst).rd)) ||
                            (inst instanceof isaLui && !visited.contains(((isaLui) inst).rd)) ||
                            (inst instanceof isaLoad && !visited.contains(((isaLoad) inst).register)) ||
                            (inst instanceof isaCalc && !visited.contains(((isaCalc) inst).rd))) {
                        block.popInst(inst);
                        i--;
                        updated = true;
                    }
                }
            }

//            System.out.println(num);
        }
    }

    public void runBlock(Block block) {
        blockNow = getBlock(block);
        block.name = "." + functionNow.name + "." + block.name;
        for (Block b : block.prevBlocks) {
            blockNow.blockBefore.add(getBlock(b));
        }
        for (Block b : block.nextBlocks) {
            blockNow.blockAfter.add(getBlock(b));
        }
        functionNow.sonBlocks.add(blockNow);
        for (instruction inst : block.inst) {
            runInst(inst);
        }
        blockNow = null;
    }

    public void runFunc(Function function) {
        regMap = new HashMap<>();
        blockMap = new HashMap<>();
        functionNow = getFunction(function);
        functionNow.beginBlock = getBlock(function.beginBlock);
        blockNow = functionNow.beginBlock;
        asm.functions.put(functionNow.name, functionNow);

//        for (register reg : function.params) {
//            System.out.println(reg.intoString());
//        }
//

        for (register reg : function.params) {
            functionNow.paramList.add(getReg(reg));
        }
        for (Register reg : asm.getPSaveReg()) {
            Register tempReg = new Register("tmp", false);
            functionNow.calleeList.add(tempReg);
            blockNow.addInst(new isaMv(tempReg, reg));
        }
        Register raReg = new Register("tmp", false);
        functionNow.raReg = raReg;
        blockNow.addInst(new isaMv(raReg, asm.getPhyReg("ra")));
        if (functionNow.paramList.size() > 8) { // 参数过多，reg 不够用

//            System.out.println("params too many");

            for (int i = 0; i < 8; i++) {
                blockNow.addInst(new isaMv(functionNow.paramList.get(i), asm.getPhyReg(10 + i)));
            }
            for (int i = 8; i < functionNow.paramList.size(); i++) {
                blockNow.addInst(new isaLoad(functionNow.paramList.get(i), asm.getPhyReg(2), new imm((i - 8) * 4, true), 4));
            }
        } else {
            for (int i = 0; i < functionNow.paramList.size(); i++) {
                blockNow.addInst(new isaMv(functionNow.paramList.get(i), asm.getPhyReg(10 + i)));
            }
        }
        for (Block block : function.blocks) {
            runBlock(block);
        }
        instCut();
        functionNow = null;
    }

    public boolean canInImm(int value) {
        return value >= -2048 && value <= 2047;
    }

    public void run() {
        asm.globalVariables = ir.var;
        asm.constStr = ir.constString;
        ir.func.forEach((name, func) -> runFunc(func));
    }

}
