package IR.instructions;

import IR.*;
import IR.operand.*;
import IR.instructions.*;
import IR.IRType.*;

import java.util.ArrayList;

public class Call extends instruction {
    public Function function;
    public ArrayList<operand> params = new ArrayList<>();

    public Call(Block block_in, register register_in, Function func_in) {
        super(block_in, register_in);
        function = func_in;
    }

    @Override
    public ArrayList<operand> getUsedOperand() {
        return params;
    }

    public String intoString() {
        StringBuilder out = new StringBuilder();
        if (!(function.returnType instanceof voidIRType))
            out.append(register.intoString()).append(" = ");
        out.append("call ");
        out.append(function.returnType.intoString());
        out.append(" ");
        out.append(function.intoString());
        out.append("(");
        for (operand i : params) {
            out.append(i.irType.intoString()).append(" ").append(i.intoString()).append(", ");
        }
        if (params.size() != 0) {
            out.deleteCharAt(out.length() - 1);
            out.deleteCharAt(out.length() - 1);
        }
        out.append(")");
        return out.toString();
    }

    @Override
    public void replace(operand old_in, operand new_in) {
        for (int i = 0; i < params.size(); i++) {
            if (params.get(i) == old_in)
                params.set(i, new_in);
        }
    }
}
