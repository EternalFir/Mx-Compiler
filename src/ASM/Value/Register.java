package ASM.Value;

public class Register extends data{
    public String name;

    public boolean isPhy = true; // for physical register


//    public register(String name_in) {
//        name = name_in;
//    }

    public Register(String name_in, boolean phy_in) { // phy or vir needs to be decided when created
        super(0);
        name = name_in;
        isPhy = phy_in;
    }

    public String intoString() {
        return name;
    }
}
