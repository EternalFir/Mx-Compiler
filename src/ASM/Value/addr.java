package ASM.Value;

public class addr extends imm {
    public String name;

    public addr(int value_in, String name_in) {
        super(value_in);
        name = name_in;
    }

    public String intoString() {
        StringBuilder ans = new StringBuilder("%");
        if (value > 0)
            ans.append("hi");
        else
            ans.append("lo");
        ans.append("(");
        ans.append(name);
        ans.append(")");
        return ans.toString();
    }

}
