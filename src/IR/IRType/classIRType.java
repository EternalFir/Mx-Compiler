package IR.IRType;

import IR.operand.*;
import IR.Function;

import java.util.ArrayList;
import java.util.Objects;

public class classIRType extends basicIRType {
    public String name;
    public int size = 0;
    public ArrayList<register> var = new ArrayList<>();
    public ArrayList<Function> func = new ArrayList<>();
    public Function constructor = null;


    public classIRType(String name_in) {
        name = name_in;
    }

    public void addVar(register v_in) {
        var.add(v_in);
        size += v_in.irType.size();
    }

    public int size() {
        return size;
    }

    public int getID(String name_in){
        for(int i =0;i<var.size();i++)
            if(var.get(i).name.equals(name+"."+name_in))
                return i;
        return -1;
    }

    public int getOffset(int id_in) {
        int out = 0;
        for (int i = 0; i < id_in; i++)
            out += var.get(i).irType.size();
        return out;
    }

    public register getVarRegister(String name_in){
        for (register r : var){
            if(r.name.equals(name+"."+name_in))
                return r;
        }
        return null;
    }

    public String intoString() {
        return "%struct." + name;
    }

    public boolean sameType(basicIRType t) {
        return (t instanceof classIRType && (Objects.equals(((classIRType) t).name, name)));
    }

    public operand getInit() {
        return new Null();
    }
}
