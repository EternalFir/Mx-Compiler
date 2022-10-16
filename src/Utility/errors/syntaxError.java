package Utility.errors;

import Utility.position;

public class syntaxError extends basicError {
    public syntaxError(String str_in,position pos_in) {
        super("SyntaxError: "+str_in,pos_in);
    }
}
