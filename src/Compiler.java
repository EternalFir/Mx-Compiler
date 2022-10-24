import Utility.errorListener;
import Utility.errors.*;
import Utility.position;
import Frontend.*;
import IR.*;
import AST.Nodes.*;
import AST.Symbols.*;
import AST.Scope;
import AST.Types.*;

import antlr.MxLexer;
import antlr.MxParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;

public class Compiler {
    public static void main(String[] args) throws Exception {
        InputStream input = System.in;
        try {
            programNode ASTTreeRoot;
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new errorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new errorListener());
            ParseTree parserTreeRoot = parser.program();
            ASTBuilder ASTBuilder = new ASTBuilder();
            ASTTreeRoot = (programNode) ASTBuilder.visit(parserTreeRoot);
            Scope globalScope=new Scope(null);
            new symbolCollector(globalScope).visit(ASTTreeRoot);
            new typeCollector(globalScope).visit(ASTTreeRoot);
            globalScope.varMap.clear();
            new semanticChecker(globalScope).visit(ASTTreeRoot);


        } catch (basicError error) {
            System.err.println(error.intoString());
            throw new RuntimeException();
        }

    }

}