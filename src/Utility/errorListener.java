package Utility;

import Utility.errors.syntaxError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class errorListener extends BaseErrorListener {
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int row, int col, String message, RecognitionException exc) {
        throw new syntaxError(message, new position(row, col));
    }
}
