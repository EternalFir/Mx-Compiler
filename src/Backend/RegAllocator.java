package Backend;

import ASM.*;
import ASM.ASMInst.*;
import ASM.Value.*;

import java.util.*;


public class RegAllocator {

    public static class ASMEdge { // should be static to judge if existed. !!!
        Register u, v;

        public ASMEdge(Register start, Register end) {
            u = start;
            v = end;
        }

        @Override
        public int hashCode() {
            return u.hashCode()*v.hashCode();
        }

        public boolean equals(Object obj) {
            return (obj instanceof ASMEdge && ((ASMEdge) obj).u == u && ((ASMEdge) obj).v == v);
        }
    }
    ASM asm;
    int stackNum = 0;
    int calleePC = 0;
    int usablePRegNum = 28; // number of allocatable physical registers;
    public ASMFunction funcNow;
    public HashSet<Register> allPReg;
    public HashSet<Register> usablePReg;
    public HashSet<Register> allRegContain;
    public HashSet<Register> allVRegContain;
    public HashMap<Register, Register> regAlias;
    public HashMap<Register, HashSet<isaMv>> regRelatedMv;
    public HashSet<isaMv> reachedMv;
    public HashSet<isaMv> activeMv;
    public HashMap<Register, HashSet<Register>> regRelatedReg; // regs that the reg points to, only for VReg
    public HashSet<ASMEdge> edges;
    public HashSet<Register> readyRegReached;
    public HashSet<Register> waitRegReached;
    public HashSet<Register> spilledRegReached;
    public HashSet<Register> mergedReg;
    public HashSet<Register> spilledReg;
    public HashSet<Register> coloredReg;
    public HashSet<Register> needKeepReg; // regs can't be moved to memory
    public Stack<Register> toColorReg;


    // 应该放在较后面
    public HashMap<ASMBlock, HashSet<Register>> activeInBlocks = null; // for regs which is active across program points
    public HashMap<ASMBlock, HashSet<Register>> activeAfterBlocks = null;

    public RegAllocator(ASM asm_in) {
        asm = asm_in;
    }

    public void Init() {
        allPReg = new HashSet<>();
        usablePReg = new HashSet<>();
        allRegContain = new HashSet<>();
        allVRegContain = new HashSet<>();
        regAlias = new HashMap<>();
        regRelatedMv = new HashMap<>();
        reachedMv = new HashSet<>();
        activeMv = new HashSet<>();
        regRelatedReg = new HashMap<>();
        edges = new HashSet<>();
        readyRegReached = new HashSet<>();
        waitRegReached = new HashSet<>();
        spilledRegReached = new HashSet<>();
        mergedReg = new HashSet<>();
        spilledReg = new HashSet<>();
        coloredReg = new HashSet<>();
        needKeepReg = new HashSet<>();
        toColorReg = new Stack<>();
        activeInBlocks = new HashMap<>();
        activeAfterBlocks = new HashMap<>();
    }

    public Register getAlia(Register reg) {
        if (mergedReg.contains(reg))
            return getAlia(regAlias.get(reg));
        else return reg;
    }

    public HashSet<isaMv> getRegMV(Register register) {
        HashSet<isaMv> out = new HashSet<>();
        out.addAll(activeMv);
        out.addAll(reachedMv);
        out.retainAll(regRelatedMv.get(register));
        return out;
    }

    public void collectReg() {
        allPReg.addAll(asm.getAllPhyReg());
        usablePReg.addAll(asm.getUsablePReg());
        for (ASMBlock block : funcNow.sonBlocks) {
            for (isaInst inst : block.insts) {
                allRegContain.addAll(inst.getUsage());
                allRegContain.addAll(inst.getDefinition());
            }
        }
        allVRegContain.addAll(allRegContain);
        allVRegContain.removeAll(allPReg);
    }

    public void initSet() {
        for (Register reg : allRegContain) {
            regAlias.put(reg, reg);
            regRelatedReg.put(reg, new HashSet<>());
            regRelatedMv.put(reg, new HashSet<>());
        }
        for (Register reg : allPReg) {
            reg.objectReg = reg;
            reg.degree = Integer.MAX_VALUE;
        }
        for (ASMBlock block : funcNow.sonBlocks) {
            for (isaInst inst : block.insts) {
                HashSet<Register> temp = inst.getDefinition();
                for (Register reg : temp) {
                    reg.weight = reg.weight + Math.pow(10.0, block.loopDepth);
                }
                temp = inst.getUsage();
                for (Register reg : temp) {
                    reg.weight = reg.weight + Math.pow(10.0, block.loopDepth);
                }
            }
        }
    }

    public void addEdge(Register u, Register v) {

//        System.out.println("addEdge");

        if (u != v && !edges.contains(new ASMEdge(u, v))) {

//            System.out.println("u: "+u.intoString() +" v: "+v.intoString());

            edges.add(new ASMEdge(u, v));
            edges.add(new ASMEdge(v, u));
            // add degree
            if (!allPReg.contains(u)) {
                regRelatedReg.get(u).add(v);
                u.degree++;
            }
            if (!allPReg.contains(v)) {
                regRelatedReg.get(v).add(u);
                v.degree++;
            }
        }
    }

    public void livenessAnalysis() {
        HashMap<ASMBlock, HashSet<Register>> definedInBlocks = new HashMap<>();
        HashMap<ASMBlock, HashSet<Register>> usedInBlocks = new HashMap<>();
        for (ASMBlock block : funcNow.sonBlocks) {
            HashSet<Register> definedInBlockNow = new HashSet<>();
            HashSet<Register> usedInBlockNow = new HashSet<>();
            for (isaInst inst : block.insts) {
                HashSet<Register> used = inst.getUsage();
                used.removeAll(definedInBlockNow);
                usedInBlockNow.addAll(used);
                definedInBlockNow.addAll(inst.getDefinition());
            }
            usedInBlocks.put(block, usedInBlockNow);
            definedInBlocks.put(block, definedInBlockNow);
            activeInBlocks.put(block, new HashSet<>());
            activeAfterBlocks.put(block, new HashSet<>());
        }
//        LinkedList<ASMBlock> queue = new LinkedList<>();
        Queue<ASMBlock> queue = new LinkedList<>();
        HashSet<ASMBlock> contains = new HashSet<>();
        // get independent program points;
        for (ASMBlock block : funcNow.sonBlocks) {
            if (block.blockAfter.size() == 0) {
                queue.add(block);
                contains.add(block);
            }
        }
        // we have:
        // active= used \cup (activeAfter \ defined)
        // activeAfter = \cup son.active
        while (queue.size() != 0) {
            ASMBlock block = queue.poll();
            contains.remove(block);
            HashSet<Register> activeAfterBlockNow = new HashSet<>();
            if (!block.blockAfter.isEmpty()) {
                for (ASMBlock sonBlock : block.blockAfter) {
                    activeAfterBlockNow.addAll(activeInBlocks.get(sonBlock));
                }
            }

//            if(block.blockAfter.size()!=0)
//                System.out.println("not empty");


            activeAfterBlocks.replace(block, activeAfterBlockNow);
            HashSet<Register> activeInBlockNow = new HashSet<>();
            activeInBlockNow.addAll(activeAfterBlockNow);
            activeInBlockNow.removeAll(definedInBlocks.get(block));
            activeInBlockNow.addAll(usedInBlocks.get(block));
            if (!activeInBlockNow.equals(activeInBlocks.get(block))) { // changed
                activeInBlocks.replace(block, activeInBlockNow);
                for (ASMBlock prevBlock : block.blockBefore) {
                    if (!contains.contains(prevBlock)) {
                        contains.add(prevBlock);
                        queue.add(prevBlock);
                    }
                }
            }
        }
        for (ASMBlock block : funcNow.sonBlocks) {
            HashSet<Register> activeNow = new HashSet<>();
            activeNow.addAll(activeAfterBlocks.get(block));
            for (int i = block.insts.size() - 1; i >= 0; i--) { //块内逆向
                isaInst inst = block.insts.get(i);
                if (inst instanceof isaMv) {
                    reachedMv.add((isaMv) inst);
                    activeNow.remove(((isaMv) inst).rs);
                    regRelatedMv.get(((isaMv) inst).rd).add((isaMv) inst);
                    regRelatedMv.get(((isaMv) inst).rs).add((isaMv) inst);
                }
                activeNow.addAll(inst.getDefinition());
                for (Register reg1 : inst.getDefinition()) {
                    for (Register reg2 : activeNow) {
                        addEdge(reg1, reg2);
                    }
                }
                activeNow.removeAll(inst.getDefinition());
                activeNow.addAll(inst.getUsage());
            }
        }
    }

    public void setReachReg() {
        for (Register reg : allVRegContain) {
            if (reg.degree >= usablePRegNum) {
                spilledRegReached.add(reg);
            } else if (!getRegMV(reg).isEmpty()) {
                waitRegReached.add(reg);
            } else {
                readyRegReached.add(reg);
            }
        }
    }

    public void readyReg(Register reg) {
        if (!allPReg.contains(reg)) { // be virtual
            if (reg.degree < usablePRegNum && getRegMV(reg).isEmpty()) {
                waitRegReached.remove(reg);
                readyRegReached.add(reg);
            }
        }
    }

    public boolean included(ArrayList<Register> list, Register objReg) {
        for (Register reg : list) {
            if (reg.degree >= usablePRegNum && !allPReg.contains(reg) && !edges.contains(new ASMEdge(reg, objReg)))
                return false;
        }
        return true;
    }

    public HashSet<Register> getVailedLinkedReg(Register register) {
        HashSet<Register> out = new HashSet<>();
        out.addAll(regRelatedReg.get(register));
        HashSet<Register> remove = new HashSet<>();
        remove.addAll(toColorReg);
        remove.addAll(mergedReg);
        out.removeAll(remove);
        return out;
    }

    public void reachMv(HashSet<Register> startRegs) {
        for (Register reg : startRegs) {
            HashSet<isaMv> related = getRegMV(reg);
            for (isaMv inst : related) {
                if (activeMv.contains(inst)) {
                    activeMv.remove(inst);
                    reachedMv.add(inst);
                }
            }
        }
    }

    public void merge(Register x, Register y) {
        if (waitRegReached.contains(y))
            waitRegReached.remove(y);
        else
            spilledRegReached.remove(y);
        mergedReg.add(y);
        regAlias.put(y, x);
        regRelatedMv.get(x).addAll(regRelatedMv.get(y));
        reachMv(new HashSet<>(Collections.singletonList(y)));
        HashSet<Register> vailed = getVailedLinkedReg(y);
        for (Register reg : vailed) {
            addEdge(reg, x);
            if (reg.degree == usablePRegNum) {
                HashSet<Register> linked = getVailedLinkedReg(reg);
                linked.add(reg);
                reachMv(linked);
                spilledRegReached.remove(reg);
                if (getRegMV(reg).isEmpty()) {
                    readyRegReached.add(reg);
                } else {
                    waitRegReached.add(reg);
                }
            }
            reg.degree--;
        }
        if (x.degree >= usablePRegNum && waitRegReached.contains(x)) {
            waitRegReached.remove(x);
            spilledRegReached.add(x);
        }
    }

    public int getSpilled(ArrayList<Register> registers, ArrayList<Register> in) {
        registers.removeAll(in);
        registers.addAll(in);
        int ans = 0;
        for (Register reg : registers) {
            if (reg.degree >= usablePRegNum)
                ans++;
        }
        return ans;
    }

    public void disactiveMv(Register reg) {
        for (isaMv inst : getRegMV(reg)) {
            Register rd = inst.rd;
            Register rs = inst.rs;
            Register temp;
            if (getAlia(reg) == getAlia(rs))
                temp = getAlia(rd);
            else
                temp = getAlia(rs);
            activeMv.remove(inst);
            if (getRegMV(temp).isEmpty() && temp.degree < usablePRegNum) {
                waitRegReached.remove(temp);
                readyRegReached.add(temp);
            }
        }
    }

    public void intoColorReady() {
        boolean flag = true;
        int dbg_cnt = 0;

        while (flag) {
            flag = false;
            if (!readyRegReached.isEmpty()) {

//                System.out.println("case 1 "+dbg_cnt);


                flag = true;
                Register intoStack = readyRegReached.iterator().next();
                readyRegReached.remove(intoStack);
                toColorReg.push(intoStack);
                HashSet<Register> vailed = getVailedLinkedReg(intoStack);
                for (Register reg : vailed) {
                    if (reg.degree == usablePRegNum) {
                        HashSet<Register> linked = getVailedLinkedReg(reg);
                        linked.add(reg);
                        reachMv(linked);
                        spilledRegReached.remove(reg);
                        if (getRegMV(reg).isEmpty()) {
                            readyRegReached.add(reg);
                        } else {
                            waitRegReached.add(reg);
                        }
                    }
                    reg.degree--;
                }
            } else if (!reachedMv.isEmpty()) {

//                System.out.println("case 2 "+dbg_cnt);

//                String dbg_mergedRegName="j_5";

                flag = true;
                isaMv merged = reachedMv.iterator().next();
                reachedMv.remove(merged);
                Register rdExact = getAlia(merged.rd);
                Register rsExact = getAlia(merged.rs);
                if (allPReg.contains(rsExact)) {
                    Register temp = rdExact;
                    rdExact = rsExact;
                    rsExact = temp;
                }
                if (rsExact == rdExact) {
                    readyReg(rdExact);
                } else if (allPReg.contains(rsExact) || edges.contains(new ASMEdge(rdExact, rsExact))) {
                    readyReg(rdExact);
                    readyReg(rsExact);
                } else if ((allPReg.contains(rdExact) && included(new ArrayList<>(getVailedLinkedReg(rsExact)), rdExact)) || (!allPReg.contains(rdExact) && (getSpilled(new ArrayList<>(getVailedLinkedReg(rdExact)), new ArrayList<>(getVailedLinkedReg(rsExact))) < usablePRegNum))) {
                    merge(rdExact, rsExact);
                    readyReg(rdExact);
                } else {
                    activeMv.add(merged);
                }
            } else if (!waitRegReached.isEmpty()) {

//                System.out.println("case 3 "+dbg_cnt);

                flag = true;
                Register ready = waitRegReached.iterator().next();
                waitRegReached.remove(ready);
                readyRegReached.add(ready);
                disactiveMv(ready);
            } else if (!spilledRegReached.isEmpty()) {

//                System.out.println("case 4 "+dbg_cnt);

                flag = true;
                Register selected = null;
                double min = Double.POSITIVE_INFINITY;
                for (Register reg : spilledRegReached) {
                    if (!allPReg.contains(reg) && !needKeepReg.contains(reg)) {
                        if (((double) reg.weight / reg.degree) < min) {
                            selected = reg;
                            min = (double) reg.weight / reg.degree;
                        }
                    }
                }
                spilledRegReached.remove(selected);
                readyRegReached.add(selected);
                disactiveMv(selected);
            }


            dbg_cnt++;
        }

//        System.out.println(dbg_cnt);
    }

    public void modifyFunc() {
        for (Register reg : spilledReg) {
            reg.offset = calleePC;
            calleePC += 4;
        }
        for (ASMBlock blockNow : funcNow.sonBlocks) {
            for (int i = 0; i < blockNow.insts.size(); i++) {
                isaInst instNow = blockNow.getInst(i);
                if (instNow instanceof isaMv && spilledReg.contains(((isaMv) instNow).rd) && spilledReg.contains(((isaMv) instNow).rs)) {
                    Register temp = new Register("tmp", false);
                    blockNow.insts.set(i, new isaLoad(temp, asm.getPhyReg(2), new imm(((isaMv) instNow).rs.offset), 4));
                    blockNow.insts.add(i + 1, new isaStore(asm.getPhyReg(2), temp, new imm(((isaMv) instNow).rd.offset), 4));
                    i++;
                } else {
                    for (Register reg : instNow.getUsage()) {
                        if (spilledReg.contains(reg)) {
                            if (instNow instanceof isaMv) {
                                blockNow.insts.set(i, new isaLoad(((isaMv) instNow).rd, asm.getPhyReg(2), new imm(reg.offset), 4));
                            } else {
                                Register temp = new Register("tmp", false);
                                needKeepReg.add(temp);
                                instNow.replaceUsage(reg, temp);
                                blockNow.insts.add(i, new isaLoad(temp, asm.getPhyReg(2), new imm(reg.offset), 4));
                                i++;
                            }
                        }
                    }
                    for (Register reg : instNow.getDefinition()) {
                        if (spilledReg.contains(reg)) {
                            if (instNow instanceof isaMv) {
                                blockNow.insts.set(i, new isaStore(asm.getPhyReg(2), ((isaMv) instNow).rs, new imm(reg.offset), 4));
                            } else {
                                Register temp = new Register("tmp", false);
                                needKeepReg.add(temp);
                                instNow.replaceDef(reg, temp);
                                blockNow.insts.add(i + 1, new isaStore(asm.getPhyReg(2), temp, new imm(reg.offset), 4));
                                i++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void color() { // coloring attempt
        while (!toColorReg.isEmpty()) {
            Register object = toColorReg.pop();
            ArrayList<Register> usablePReg = new ArrayList<>(asm.getUsablePReg());
            HashSet<Register> colored = new HashSet<>(allPReg);
            colored.addAll(coloredReg);
            HashSet<Register> children = regRelatedReg.get(object);
            for (Register reg : children) {
                if (colored.contains(getAlia(reg)))
                    usablePReg.remove(getAlia(reg).objectReg);
            }
            if (usablePReg.isEmpty()) {
                spilledReg.add(object);
            } else {
                coloredReg.add(object);
                object.objectReg = usablePReg.get(0);
            }
        }
        for (Register reg : mergedReg) {
            reg.objectReg = getAlia(reg).objectReg;
        }
    }

    public void MvCut() {
        for (ASMBlock block : funcNow.sonBlocks) {
            for (int i = 0; i < block.insts.size(); i++) {
                isaInst inst = block.insts.get(i);
                if (inst instanceof isaMv && ((isaMv) inst).rd.objectReg == ((isaMv) inst).rs.objectReg) { // useless isaMv
                    block.insts.remove(i);
                    i--;
                }
            }
        }
    }

    public void mergeBlock() {
        for (int i = 0; i < funcNow.sonBlocks.size(); i++) {
            ASMBlock blockNow = funcNow.sonBlocks.get(i);
            if (blockNow.blockBefore.size() == 1) {
                ASMBlock blockBefore = blockNow.blockBefore.get(0);
                if (blockBefore.getLastInst() instanceof isaJump && ((isaJump) blockBefore.getLastInst()).destination == blockNow) { // as link list
                    blockBefore.blockAfter = blockNow.blockAfter;
                    blockBefore.popLastInst();
                    blockBefore.insts.addAll(blockNow.insts);
                    for (ASMBlock block : blockBefore.blockAfter) {
                        for (int j = 0; j < block.blockBefore.size(); j++) {
                            if (block.blockBefore.get(j) == blockNow) {
                                block.blockBefore.set(j, blockBefore);
                            }
                        }
                    }
                    funcNow.sonBlocks.remove(i);
                    i--;
                }
            }
        }
    }

    public void runFunction(ASMFunction function) {
        funcNow = function;
        Init();
        collectReg();
        initSet();
        livenessAnalysis();
        setReachReg();
        intoColorReady();
        color();
        if (!spilledReg.isEmpty()) { // coloring failed
            modifyFunc();
            runFunction(function);
        } else {
            // add sp set insts at head and tail
            int spValue;
            if (funcNow.paramList.size() >= 8) {
                spValue = calleePC + (funcNow.paramList.size() - 8) * 4;
            } else {
                spValue = calleePC;
            }
            if (spValue > 0) {
                funcNow.beginBlock.addInst(new isaCalc("addi", asm.getPhyReg("sp"), asm.getPhyReg("sp"), new imm(-spValue)), 0);
                funcNow.endBlock.addLastInst(new isaCalc("addi", asm.getPhyReg("sp"), asm.getPhyReg("sp"), new imm(spValue)));
            }
            for (isaInst inst : funcNow.beginBlock.insts) { // load inst 的 imm 也要做出相应更改
                if (inst instanceof isaLoad && ((isaLoad) inst).offset.isParamImm) {
                    ((isaLoad) inst).offset.value += calleePC;
                }
            }
            MvCut();
            mergeBlock();
        }
        funcNow = null;
    }

    public void run() {
        asm.functions.forEach((name, func) -> {
            calleePC = 0;
            runFunction(func);
        });

    }
}
