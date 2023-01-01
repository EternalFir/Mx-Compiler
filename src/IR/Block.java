package IR;

import IR.instructions.*;
import IR.operand.*;

import Utility.errors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Block {
    public String name = null;
    public ArrayList<instruction> inst = new ArrayList<>();
    public ArrayList<Block> prevBlocks = new ArrayList<>();
    public ArrayList<Block> nextBlocks = new ArrayList<>();
    public LinkedHashMap<register, operand> regValues = new LinkedHashMap<>();
    public Phi branchFrom = null;
    public int loopDepth = 0;
    public boolean is_end = false;

    public Block(int depth_in) {
        loopDepth = depth_in;
    }

    public void addInst(instruction new_inst) {
        inst.add(new_inst);
    }

    public void popInst(int index) {
        inst.remove(index);
    }

    public void popInst(instruction inst_in) {
        for (int i = 0; i < inst.size(); i++) {
            if (inst.get(i) == inst_in) {
                inst.remove(i);
                break;
            }
        }
    }

    public void addFrontInst(instruction new_inst) {
        inst.add(0, new_inst);
    }

    public void addBaskInst(instruction new_inst) {
        inst.add(inst.size() - 1, new_inst);
    }

    public instruction getInst(int index) {
        return inst.get(index);
    }

    public instruction getLastInst() {
        if (inst.size() >= 2)
            return inst.get(inst.size() - 2);
        return null;
    }

    public void addEndInst(instruction inst_in) {
        if (is_end)
            return;
        inst.add(inst_in);
        if (inst_in instanceof Jump) {
            nextBlocks.add(((Jump) inst_in).destination);
            ((Jump) inst_in).destination.prevBlocks.add(this);
        } else if (inst_in instanceof Branch) {
            nextBlocks.add(((Branch) inst_in).trueBlock);
            nextBlocks.add(((Branch) inst_in).falseBlock);
            ((Branch) inst_in).trueBlock.prevBlocks.add(this);
            ((Branch) inst_in).falseBlock.prevBlocks.add(this);
        }
        is_end = true;
    }

    public void popEndInst() {
        if (!is_end)
            return;
        instruction removed = inst.get(inst.size() - 1);
        if (removed instanceof Jump) {
            nextBlocks.remove(((Jump) removed).destination);
            ((Jump) removed).destination.prevBlocks.remove(this);
        } else if (removed instanceof Branch) {
            nextBlocks.remove(((Branch) removed).trueBlock);
            nextBlocks.remove(((Branch) removed).falseBlock);
            ((Branch) removed).trueBlock.prevBlocks.remove(this);
            ((Branch) removed).falseBlock.prevBlocks.remove(this);
        }
        inst.remove(inst.size() - 1);
        is_end = false;
    }

    public instruction getEndInst() {
        if (is_end && inst.size() > 0)
            return inst.get(inst.size() - 1);
        return null;
    }

    public String intoString() {
        return "%" + name;
    }

    public void replaceNextBlock(Block old_block, Block new_Block) {
        for (instruction i : inst) {
            if (i instanceof Jump) {
                if (((Jump) i).destination == old_block)
                    ((Jump) i).destination = new_Block;
            } else if (i instanceof Branch) {
                if (((Branch) i).trueBlock == old_block)
                    ((Branch) i).trueBlock = new_Block;
                if (((Branch) i).falseBlock == old_block)
                    ((Branch) i).falseBlock = new_Block;
            }
        }
    }

    public void replacePrevBlock(Block old_block, Block new_block) {
        for (instruction i : inst) {
            if (i instanceof Phi) {
                for (int j = 0; j < ((Phi) i).blocks.size(); j++) {
                    if (((Phi) i).blocks.get(j) == old_block)
                        ((Phi) i).blocks.set(j, new_block);
                }
            }
        }
    }
}
