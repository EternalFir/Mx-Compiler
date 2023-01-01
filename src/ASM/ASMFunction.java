package ASM;

import ASM.Value.*;

import java.util.ArrayList;

public class ASMFunction {
    public String name;
    public ArrayList<Register> paramList = new ArrayList<>();
    public ASMBlock beginBlock = null;
    public ASMBlock endBlock = null;
    public ArrayList<ASMBlock> sonBlocks = new ArrayList<>();
    public ArrayList<Register> calleeList = new ArrayList<>();
    public Register raReg = null;

    public ASMFunction(String name_in) {
        name = name_in;
    }

    public String intoString() {
        return name;
    }
}
