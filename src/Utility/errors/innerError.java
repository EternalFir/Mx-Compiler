package Utility.errors;

import Utility.position;

public class innerError extends basicError{
    public innerError(String message_in){
        super("Inner Error:"+message_in,new position(-1,-1));
    }
}
