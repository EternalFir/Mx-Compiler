package Utility;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class position {
    private int row, col;

    public position(int r, int c) {
        row = r;
        col = c;
    }

    public position(Token token) {
        this.row = token.getLine();
        this.col = token.getCharPositionInLine();
    }

    public position(TerminalNode terminalNode) {
        this(terminalNode.getSymbol());
    }

    public position(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public int row() {
        return row;
    }

    public int column() {
        return col;
    }

    public String intostring() {
        return row + "," + col;
    }

}