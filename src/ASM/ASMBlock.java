package ASM;

import ASM.ASMInst.isaInst;

import java.util.ArrayList;

import Utility.errors.innerError;

public class ASMBlock {
    public String name;
    public int loopDepth;
    public ArrayList<isaInst> insts = new ArrayList<>();
    public ArrayList<ASMBlock> blockBefore = new ArrayList<>();
    public ArrayList<ASMBlock> blockAfter = new ArrayList<>();

    public ASMBlock(int depth_in) {
        loopDepth = depth_in;
    }

    public void addInst(isaInst inst_in) {
        insts.add(inst_in);
    }

    public void addInst(isaInst inst_in, int index) {
        insts.add(index, inst_in);
    }

    public void popInst(int index) {
        insts.remove(index);
    }

    public void popInst(isaInst inst_in) {
        for (int i = 0; i < insts.size(); i++) {
            if (insts.get(i) == inst_in) {
                insts.remove(i);
                return;
            }
        }
    }

    public isaInst getInst(int index) {
        if (index < 0 || index >= insts.size())
            throw new innerError("index out of range");
        return insts.get(index);
    }

    public void addLastInst(isaInst inst_in) {
        insts.add(insts.size() - 1, inst_in);
    }

    public void popLastInst() {
        insts.remove(insts.size() - 1);
    }

    public isaInst getLastInst() {
        return insts.get(insts.size() - 1);
    }

    public String intoString() {
        return name;
    }
}
