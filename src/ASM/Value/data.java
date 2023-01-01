package ASM.Value;

public abstract class data {
    public int value;

    public data(int value_in) {
        value = value_in;
    }

    public abstract String intoString();

}
