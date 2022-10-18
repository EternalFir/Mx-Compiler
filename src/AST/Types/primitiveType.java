package AST.Types;

public class primitiveType extends basicType{
    public String name;

    public primitiveType(String name_in){
        name=name_in;
    }

    @Override
    public boolean sameType(basicType t) {
        return (this.isNull() && (t instanceof arrayType || t instanceof classType)) || ((t instanceof primitiveType) && (this.name.equals(((primitiveType) t).name)));
    }

    @Override
    public boolean isBool() {
        return name.equals("bool");
    }

    @Override
    public boolean isInt() {
        return name.equals("int");
    }

    @Override
    public boolean isString() {
        return name.equals("string");
    }

    @Override
    public boolean isVoid() {
        return name.equals("void");
    }

    @Override
    public boolean isNull() {
        return name.equals("null");
    }
}
