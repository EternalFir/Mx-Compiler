package Midend;

import IR.Block;
import IR.Function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class LinerTree {
    public Function currentFunction;
    public HashSet<Block> visitedBlocks = new HashSet<>();
    public ArrayList<Block> rBlocks = new ArrayList<>();
    public HashMap<Block, Integer> dfsNumber = new HashMap<>();
    public HashMap<Block, Block> sub = new HashMap<>();
    public HashMap<Block, ArrayList<Block>> subSon = new HashMap<>();
    public HashMap<Block, ArrayList<Block>> subP = new HashMap<>();
    public ArrayList<Block> rNodes = new ArrayList<>();
    public HashMap<Block, HashSet<Block>> subSonTree = new HashMap<>();


    public LinerTree(Function func_in) {
        currentFunction = func_in;
    }

    public void dfsCollect(Block block_in) {
        visitedBlocks.add(block_in);
        for (Block i : block_in.nextBlocks) {
            if (!visitedBlocks.contains(i))
                dfsCollect(i);
        }
        rBlocks.add(0, block_in);
    }

    public Block blockIntersection(Block a, Block b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        while (a != b) {
            while (dfsNumber.get(a) > dfsNumber.get(b))
                a = sub.get(a);
            while (dfsNumber.get(b) > dfsNumber.get(a))
                b = sub.get(b);
        }
        return a;
    }

    public void subTree() {
        int count = 0;
        dfsCollect(currentFunction.beginBlock);
        for (Block i : rBlocks) {
            dfsNumber.put(i, count);

//            System.out.println(dfsNumber.get(count));

            sub.put(i, null);
            subSon.put(i, new ArrayList<>());
            count++;
        }
        sub.replace(currentFunction.beginBlock, currentFunction.beginBlock);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Block i : rBlocks) {
                if (i != rBlocks.get(0)) {
                    Block newSub = null;
                    for (Block j : i.prevBlocks) {
                        if (sub.get(j) != null)
                            newSub = blockIntersection(newSub, j);
                    }
                    if (sub.get(i) != newSub) {
                        sub.replace(i, newSub);
                        changed = true;
                    }
                }
            }
        }
        sub.forEach((x, y) -> {
            if (y != null && x != y)
                subSon.get(y).add(x);
        });
    }

    public void subFrontier() {
        for (Block i : rBlocks) {
            subP.put(i, new ArrayList<>());
        }
        for (Block i : rBlocks) {
            if (i.prevBlocks.size() > 1) {
                for (Block j : i.prevBlocks) {
                    Block temp = j;
                    while (temp != sub.get(i)) {
                        subP.get(temp).add(i);
                        temp = sub.get(temp);
                    }
                }
            }
        }
    }

    public void dfsTree(Block node) {
        HashSet<Block> son = new HashSet<>();
        for (Block i : subSon.get(node)) {
            dfsTree(i);
            son.add(i);
            son.addAll(subSonTree.get(i));
        }
        rNodes.add(node);
        subSonTree.put(node, son);
    }

    public void run() {
        subTree();
        subFrontier();
        dfsTree(currentFunction.beginBlock);
    }
}
