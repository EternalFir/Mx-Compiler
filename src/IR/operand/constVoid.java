package IR.operand;


public class constVoid extends operand{
    public constVoid(){
        super(null);
    }

    public String intoString(){
        return "void";
    }

    public boolean sameOperand(operand o){
        return o instanceof constVoid;
    }
}
