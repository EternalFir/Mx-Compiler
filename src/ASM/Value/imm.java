package ASM.Value;

public class imm extends data {

    public boolean isParamImm = false;

    public imm(int value_in) {
        super(value_in);
    }

    public imm(int value_in, boolean isParam_in) {
        super(value_in);
        isParamImm = isParam_in;
    }

    public String intoString() {
        return ""+value;
    }
}
