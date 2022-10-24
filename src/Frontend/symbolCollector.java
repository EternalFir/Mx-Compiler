package Frontend;

import AST.Symbols.*;
import AST.Scope;
import AST.Types.*;
import AST.Nodes.*;
import IR.IR;
import IR.IRType.*;
import IR.operand.*;
import IR.Function;

public class symbolCollector implements ASTVisitor {
    Scope globalScope, currentScope;

    public symbolCollector(Scope scope_in) {
        globalScope = scope_in;
        globalScope.typeMap.put("bool", new primitiveType("bool"));
        globalScope.typeMap.put("int", new primitiveType("int"));
        globalScope.typeMap.put("string", new primitiveType("string"));
        globalScope.typeMap.put("void", new primitiveType("void"));
        globalScope.typeMap.put("null", new primitiveType("null"));
        IR ir = new IR();

        //builtInFunctions
        funcSymbol printFunc = new funcSymbol("print");
        printFunc.returnType = new primitiveType("void");
        printFunc.paramList.add(new varSymbol("string", new primitiveType("string")));
        globalScope.funcMap.put("print", printFunc);
        printFunc.function = new Function("__bulitIn__print");
        printFunc.function.retyrnType = ir.getType(printFunc.returnType);

        funcSymbol printlnFunc = new funcSymbol("println");
        printlnFunc.returnType = new primitiveType("void");
        printlnFunc.paramList.add(new varSymbol("string", new primitiveType("string")));
        globalScope.funcMap.put("println", printlnFunc);
        printlnFunc.function = new Function("__builtIn__println");
        printlnFunc.function.retyrnType = ir.getType(printlnFunc.returnType);

        funcSymbol printInt = new funcSymbol("printInt");
        printInt.returnType = new primitiveType("void");
        printInt.paramList.add(new varSymbol("number", new primitiveType("int")));
        globalScope.funcMap.put("printInt", printInt);
        printInt.function = new Function("__builtIn__printInt");
        printInt.function.retyrnType = ir.getType(printInt.returnType);

        funcSymbol printlnInt = new funcSymbol("printlnInt");
        printlnInt.returnType = new primitiveType("void");
        printlnInt.paramList.add(new varSymbol("number", new primitiveType("int")));
        globalScope.funcMap.put("printlnInt", printlnInt);
        printlnInt.function = new Function("__builtIn__printlnInt");
        printlnInt.function.retyrnType = ir.getType(printlnInt.returnType);

        funcSymbol getInt = new funcSymbol("getInt");
        getInt.returnType = new primitiveType("int");
        globalScope.funcMap.put("getInt", getInt);
        getInt.function = new Function("__bulitIn__getInt");
        getInt.function.retyrnType = ir.getType(getInt.returnType);

        funcSymbol getString = new funcSymbol("getString");
        getString.returnType = new primitiveType("string");
        globalScope.funcMap.put("getString", getString);
        getString.function = new Function("__builtIn__getString");
        getString.function.retyrnType = ir.getType(getString.returnType);

        funcSymbol toString = new funcSymbol("toString");
        toString.returnType = new primitiveType("string");
        toString.paramList.add(new varSymbol("i", new primitiveType("int")));
        globalScope.funcMap.put("toString", toString);
        toString.function = new Function("__builtIn__toString");
        toString.function.retyrnType = ir.getType(toString.returnType);
    }

    @Override
    public void visit(typeNode node) {

    }

    @Override
    public void visit(programNode node) {
        currentScope = globalScope;
        if (node.proBody != null) {
            for (ASTNode i : node.proBody) {
                i.accept(this);
            }
        }
    }

    @Override
    public void visit(funcDefNode node) {
        node.funcSymbol = new funcSymbol(node.name);
        currentScope.addFunction(node.name, node.funcSymbol, node.pos);
    }

    @Override
    public void visit(varDefSubNode node) {
        node.varSymbol=new varSymbol(node.name);
        currentScope.addVariable(node.name,node.varSymbol,node.pos);
    }

    @Override
    public void visit(varDefNode node) {

    }

    @Override
    public void visit(classDefNode node) {
        currentScope = new Scope(currentScope);
        classType x = new classType(node.name);
        if (node.varList != null) {
            for (varDefSubNode i : node.varList) {
                i.accept(this);
            }
        }
        if (node.funcList != null) {
            for (funcDefNode i : node.funcList) {
                i.accept(this);
            }
        }
        if (node.constructor != null) {
            x.constructor = new funcSymbol(node.constructor.name);
            node.constructor.funcSymbol = x.constructor;
        }
        x.varMap = currentScope.varMap;
        x.funcMap = currentScope.funcMap;
        currentScope = currentScope.parentScope;
        currentScope.addType(node.name, x, node.pos);
        node.classType = x;
        node.classType.classIrType = new classIRType((node.name));
    }

    @Override
    public void visit(lambdaDefNode node) {

    }

    @Override
    public void visit(codeBlockNode node) {

    }

    @Override
    public void visit(ifSentNode node) {

    }

    @Override
    public void visit(forSentNode node) {

    }

    @Override
    public void visit(whileSentNode node) {

    }

    @Override
    public void visit(returnSentNode node) {

    }

    @Override
    public void visit(breakSentNode node) {

    }

    @Override
    public void visit(continueSentNode node) {

    }

    @Override
    public void visit(expressionOnlySentNode node) {

    }

    @Override
    public void visit(emptySentNode node) {

    }

    @Override
    public void visit(preNumberExpNode node) {

    }

    @Override
    public void visit(binaryExpNode node) {

    }

    @Override
    public void visit(memberExpNode node) {

    }

    @Override
    public void visit(newExpNode node) {

    }

    @Override
    public void visit(backselfExpNode node) {

    }

    @Override
    public void visit(frontselfExpNode node) {

    }

    @Override
    public void visit(funcExpNode node) {

    }

    @Override
    public void visit(preLogicExpNode node) {

    }

    @Override
    public void visit(arrayExpNode node) {

    }

    @Override
    public void visit(expressionGroupNode node) {

    }

    @Override
    public void visit(boolLiteralNode node) {

    }

    @Override
    public void visit(intLiteralNode node) {

    }

    @Override
    public void visit(stringLiteralNode node) {

    }

    @Override
    public void visit(nullLiteralNode node) {

    }

    @Override
    public void visit(thisExpNode node) {

    }

    @Override
    public void visit(varExpNode node) {

    }

    @Override
    public void visit(mainNode node) {

    }
}
