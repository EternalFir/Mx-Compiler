package Utility.errors;

import Utility.position;

public class semanticError extends basicError {
    public semanticError(String str_in, position pos_in) {
        super("SemanticError: " + str_in, pos_in);
    }

}
