package AST.Types;

public abstract class basicType {
    public  boolean sameType(basicType t){
        return false;
    }

    public boolean isBool() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isVoid() {
        return false;
    }

    public boolean isNull() {
        return false;
    }
}
