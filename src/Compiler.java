import ASM.ASM;
import Backend.ASMBuilder;
import Backend.ASMPrinter;
import Backend.RegAllocator;
import Backend.builtInPrinter;
import Midend.IRBuilder;
import Midend.IRPrinter;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Compiler {
    public static void main(String[] args) throws Exception {

        InputStream input = System.in;

        String inputFile = "input.mx";
        String outputFile = "output.s";

//        InputStream input = new FileInputStream(inputFile);
        PrintStream output = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(output);
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
            Scope globalScope = new Scope(null);
            new symbolCollector(globalScope).visit(ASTTreeRoot);
            new typeCollector(globalScope).visit(ASTTreeRoot);
            globalScope.varMap.clear();
            new semanticChecker(globalScope).visit(ASTTreeRoot);
            IR MxIR = new IR();
            new IRBuilder(MxIR).visit(ASTTreeRoot);
            new IRBuilder(MxIR).run();
//            new IRPrinter(System.out, MxIR).print();
            ASM MxASM = new ASM();
            new ASMBuilder(MxIR, MxASM).run();
            new RegAllocator(MxASM).run();
            new ASMPrinter(MxASM, System.out).print();

            new builtInPrinter("builtin.s");
        } catch (basicError error) {
            System.err.println(error.intoString());
            throw error;
        }

    }

}