package ASM;

import ASM.Value.Register;

import java.util.ArrayList;
import java.util.HashMap;

public class ASM {
    public String[] registerName = new String[]{"zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"};

    private int regSize = 32;
    public HashMap<String, ASMFunction> functions = new HashMap<String, ASMFunction>();
    public HashMap<String, IR.operand.register> globalVariables = new HashMap<>();
    public HashMap<String, IR.operand.constString> constStr = new HashMap<>();
    public HashMap<String, Register> namePhyRegMap = new HashMap<>();
    public HashMap<Integer, Register> IDPhyRegMap = new HashMap<>();

    public ASM() {
        Init();
    }

    private void Init() {
        for (int i = 0; i < regSize; i++) {
            Register r = new Register(registerName[i], true);
            namePhyRegMap.put(registerName[i], r);
            IDPhyRegMap.put(i, r);
        }
    }

    public Register getPhyReg(String name_in) {
        return namePhyRegMap.get(name_in);
    }

    public Register getPhyReg(int id_in) {
        return IDPhyRegMap.get(id_in);
    }

    public ArrayList<Register> getAllPhyReg() {
        ArrayList<Register> out = new ArrayList<>();
        for (int i = 0; i < regSize; i++) {
            out.add(getPhyReg(i));
        }
        return out;
    }

    public ArrayList<Register> getPSaveReg() {
        ArrayList<Register> out = new ArrayList<>();
        out.add(getPhyReg(8));
        out.add(getPhyReg(9));
        for (int i = 18; i <= 27; i++) {
            out.add(getPhyReg(i));
        }
        return out;
    }

    public ArrayList<Register> getTempFuncArgumentReg() {
        ArrayList<Register> out = new ArrayList<>();
        out.add(getPhyReg(0));
        for (int i = 5; i <= 7; i++) {
            out.add(getPhyReg(i));
        }
        for (int i = 10; i <= 17; i++) {
            out.add(getPhyReg(i));
        }
        for (int i = 28; i <= 31; i++) {
            out.add(getPhyReg(i));
        }
        return out;
    }


}
