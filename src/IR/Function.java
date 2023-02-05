package IR;

import IR.operand.operand;
import IR.operand.register;
import IR.IRType.*;
import IR.instructions.Return;

import java.util.ArrayList;
import java.util.HashSet;

public class Function {
    public String name;
    public Block beginBlock = new Block(0);

    public ArrayList<Block> blocks = new ArrayList<>();
    public ArrayList<register> params = new ArrayList<>();
    public HashSet<register> vars = new HashSet<>();
    public basicIRType returnType = new voidIRType();
    public ArrayList<Return> returnInsts = new ArrayList<>();
    public boolean classFunc = false;
    public operand classPtr = null;

    public Function(String name_in) {
        name = name_in;
    }

    public String intoString() {
        return "@" + name;
    }

    public int getInstCount() {
        int sum = 0;
        for (Block block : blocks) {
            sum += block.getInstCount();
        }
        return sum;
    }
}
