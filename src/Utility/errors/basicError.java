package Utility.errors;

import Utility.position;

public abstract class  basicError extends RuntimeException{
    private String str;
    private position pos;

    public basicError(String str_in, position pos_in) {
        str = str_in;
        pos = pos_in;
    }

    public String intoString() {
        return str + " at: " + pos.intostring();
    }
}
