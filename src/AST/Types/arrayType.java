package AST.Types;

public class arrayType extends basicType {
    public basicType atomType;
    public int dimen;

    public arrayType(basicType atomType_in, int dim_in) {
        atomType = atomType_in;
        dimen = dim_in;
    }

    @Override
    public boolean sameType(basicType t) {
        return t.isNull() || ((t instanceof arrayType) && (this.atomType.sameType(((arrayType) t).atomType) && this.dimen == ((arrayType) t).dimen));
    }
}
