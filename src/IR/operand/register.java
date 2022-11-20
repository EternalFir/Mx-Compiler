package IR.operand;

import IR.instructions.instruction;

import IR.IRType.*;

import java.util.ArrayList;
import java.util.Stack;

public class register extends operand {
    public String name;
    public boolean isGlobal = false;
    public boolean isConstPtr = false;
    public ArrayList<instruction> assign = new ArrayList<>();
    public Stack<register> renamingStack = new Stack<>();
    public int renamingID = 0;
    public boolean used = false;

    public register(basicIRType type_in, String name_in) {
        super(type_in);
        name = name_in;
    }

    public register(basicIRType type_in, String name_in, boolean isGlobal_in, boolean isConst_in) {
        super(type_in);
        name = name_in;
        isGlobal = isGlobal_in;
        isConstPtr = isConst_in;
    }

    public String intoString() {
        if (isGlobal)
            return "@" + name; //global
        else
            return "%" + name; // private
    }

    @Override
    public boolean sameOperand(operand o) {
        return this == o;
    }
}
