package Frontend;

import AST.Nodes.*;
import AST.Symbols.*;
import AST.Types.*;
import AST.Scope;
import IR.Function;
import IR.IR;

import Utility.errors.semanticError;

import java.util.ArrayList;

public class semanticChecker implements ASTVisitor {
    public Scope globalScope, currentScope;
    public basicType currentReturnType;
    public classType currentClass;
    public boolean returnDone;
    public ArrayList<SentenceNode> loops = new ArrayList<>();
    public int loopDepth = 0;
    IR ir = new IR();

    public semanticChecker(Scope scope_in) {
        globalScope = scope_in;
    }

    @Override
    public void visit(typeNode node) {

    }

    @Override
    public void visit(programNode node) {
        funcSymbol mainFunc = globalScope.getFunction("main", node.pos, false);
        if (!mainFunc.returnType.isInt())
            throw new semanticError("main() needs to return int.", node.pos);
        if (mainFunc.paramList != null && mainFunc.paramList.size() != 0)
            throw new semanticError("main() can't have any parameter.", node.pos);
        currentScope = globalScope;
        if (node.proBody != null) {
            for (ASTNode i : node.proBody) {
                i.accept(this);
            }
        }
    }

    @Override
    public void visit(funcDefNode node) {
        if (node.type != null)
            currentReturnType = globalScope.getType(node.type);
        else
            currentReturnType = new primitiveType("void");
        node.returnType = currentReturnType;
        node.funcSymbol.function = new Function(node.name);
        node.funcSymbol.function.retyrnType = ir.getType(node.returnType);
        if (currentClass != null)
            node.funcSymbol.function.classFunc = true;
        returnDone = false;
        currentScope = new Scope(currentScope);
        if (node.paramList != null) {
            for (varDefSubNode i : node.paramList) {
                i.varSymbol = new varSymbol(i.name, globalScope.getType(i.type));
                currentScope.addVariable(i.name, i.varSymbol, i.pos);
            }
        }
        node.codeBlock.accept(this);
        currentScope = currentScope.parentScope;
        if (node.name.equals("main"))
            returnDone = true;
        if (node.type != null && !node.type.basicTypeName.equals("void") && !returnDone)
            throw new semanticError("no return sentence.", node.pos);
    }

    @Override
    public void visit(varDefSubNode node) {
        basicType varType = globalScope.getType(node.type);
        if (varType.isVoid())
            throw new semanticError("void variable", node.pos);
        if (node.expression != null) {
            node.expression.accept(this);
            if (!node.expression.type.sameType(varType))
                throw new semanticError("wrong variable init", node.pos);
        }
        node.varSymbol = new varSymbol(node.name, varType);
        if (currentScope == globalScope) {
            node.varSymbol.isGlobalVar = true;
        }
        currentScope.addVariable(node.name, node.varSymbol, node.pos);
    }

    @Override
    public void visit(varDefNode node) {
        node.varDefList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(classDefNode node) {
        currentClass = (classType) globalScope.typeMap.get(node.name);
        currentScope = new Scope(currentScope);
        currentClass.varMap.forEach((key, value) -> currentScope.addVariable(key, value, node.pos));
        currentClass.funcMap.forEach((key, value) -> currentScope.addFunction(key, value, node.pos));
        node.funcList.forEach(func -> func.accept(this));
        if (node.constructor != null) {
            if (!node.constructor.name.equals(node.name))
                throw new semanticError("mismatch constructor name", node.pos);
            node.constructor.accept(this);
            node.classType.classIrType.constructor = node.constructor.funcSymbol.function;
        }
        currentScope = currentScope.parentScope;
        currentClass = null;
    }

    @Override
    public void visit(lambdaDefNode node) {

    }

    @Override
    public void visit(codeBlockNode node) {
        if (node.sentencesList != null) {
            for (SentenceNode i : node.sentencesList) {
                if (i instanceof codeBlockNode) {
                    currentScope = new Scope(currentScope);
                    i.accept(this);
                    currentScope = currentScope.parentScope;
                } else {
                    i.accept(this);
                }
            }
        }
    }

    @Override
    public void visit(ifSentNode node) {
        node.cond.accept(this);
        if (!node.cond.type.isBool())
            throw new semanticError("condition type error", node.pos);
        currentScope = new Scope(currentScope);
        node.trueSent.accept(this);
        currentScope = currentScope.parentScope;
        if (node.falseSent != null) {
            currentScope = new Scope(currentScope);
            node.falseSent.accept(this);
            currentScope = currentScope.parentScope;
        }
    }

    @Override
    public void visit(forSentNode node) {
        if (node.init != null)
            node.init.accept(this);
        if (node.cond != null)
            node.cond.accept(this);
        if (node.cond != null && !node.cond.type.isBool())
            throw new semanticError("condition type error", node.pos);
        if (node.move != null)
            node.move.accept(this);
        loopDepth++;
        loops.add(node);
        currentScope = new Scope(currentScope);
        node.repeSent.accept(this);
        currentScope = currentScope.parentScope;
        loopDepth--;
        loops.remove(loopDepth);
    }

    @Override
    public void visit(whileSentNode node) {
        node.cond.accept(this);
        if (!node.cond.type.isBool())
            throw new semanticError("condition type error", node.pos);
        loopDepth++;
        loops.add(node);
        currentScope = new Scope(currentScope);
        node.repeSent.accept(this);
        currentScope = currentScope.parentScope;
        loopDepth--;
        loops.remove(loopDepth);
    }

    @Override
    public void visit(returnSentNode node) {
        returnDone = true;
        if (node.value != null) {
            node.value.accept(this);
            if (!node.value.type.sameType(currentReturnType))
                throw new semanticError("return type error", node.pos);
        } else {
            if (!currentReturnType.isVoid())
                throw new semanticError("return type error", node.pos);
        }
    }

    @Override
    public void visit(breakSentNode node) {
        if (loopDepth == 0) {
            throw new semanticError("break out of a loop", node.pos);
        }
        node.loop = loops.get(loopDepth - 1);
    }

    @Override
    public void visit(continueSentNode node) {
        if (loopDepth == 0)
            throw new semanticError("continue out of a loop", node.pos);
        node.loop = loops.get(loopDepth - 1);
    }

    @Override
    public void visit(expressionOnlySentNode node) {
        node.expression.accept(this);
    }

    @Override
    public void visit(emptySentNode node) {
    }

    @Override
    public void visit(preNumberExpNode node) {
        node.exp.accept(this);
        if (!node.exp.type.isInt()) {
            throw new semanticError("require int type", node.pos);
        }
        node.type = node.exp.type;
    }

    @Override
    public void visit(binaryExpNode node) {
        node.lexp.accept(this);
        node.rexp.accept(this);
        switch (node.op) {
            case "*":
            case "/":
            case "%":
            case "-":
            case "<<":
            case ">>":
            case "&":
            case "^":
            case "|":
                if(!(node.lexp.type.isInt() && node.rexp.type.isInt()))
                    throw new semanticError("require int type",node.pos);
                node.type=new primitiveType("int");
                break;
            case "+":
                if(!((node.lexp.type.isInt()&& node.rexp.type.isInt())||(node.lexp.type.isString()&& node.rexp.type.isString())))
                    throw new semanticError("require int or string type",node.pos);
                node.type=node.lexp.type;
                break;
            case "<":
            case ">":
            case "<=":
            case ">=":
                if(!((node.lexp.type.isInt()&& node.rexp.type.isInt())||(node.lexp.type.isString()&& node.rexp.type.isString())))
                    throw new semanticError("require int or string type",node.pos);
                node.type=new primitiveType("bool");
                break;
            case "&&":
            case "||":
                if(!(node.lexp.type.isBool()&& node.rexp.type.isBool()))
                    throw new semanticError("require bool type",node.pos);
                node.type=new primitiveType("bool");
                break;
            case "==":
            case "!=":
                if(!node.lexp.type.sameType(node.rexp.type))
                    throw new semanticError("require same type",node.pos);
                node.type=new primitiveType("bool");
                break;
            case "=":
                if(!node.lexp.type.sameType(node.rexp.type))
                    throw new semanticError("require same type",node.pos);
                if(!node.lexp.isAssignable)
                    throw new semanticError("not assignable",node.pos);
                node.type=node.lexp.type;
                node.isAssignable=true;
                break;
            default:
                break;
        }
    }

    @Override
    public void visit(memberExpNode node) {
        node.caller.accept(this);

        // builtInMethods
        if(node.caller.type instanceof arrayType && node.isFunc && node.callee.equals("size")){
            funcSymbol sizeFunc = new funcSymbol("size");
            sizeFunc.returnType=new primitiveType("int");
            node.type=sizeFunc;
            return;
        }
        if(node.caller.type.isString() && node.isFunc && node.callee.equals("length")){
            funcSymbol lengthFunc=new funcSymbol("length");
            lengthFunc.returnType = new primitiveType("int");
            node.type=lengthFunc;
            lengthFunc.function=new Function("__builtIn__stringLength");
            lengthFunc.function.retyrnType=ir.getType(lengthFunc.returnType);
            return;
        }
        if(node.caller.type.isString() && node.isFunc && node.callee.equals("substring")){
            funcSymbol substringFunc= new funcSymbol("substring");
            substringFunc.returnType=new primitiveType("string");
            substringFunc.paramList.add(new varSymbol("left",new primitiveType("int")));
            substringFunc.paramList.add(new varSymbol("right",new primitiveType("int")));
            node.type=substringFunc;
            substringFunc.function=new Function("__builtIn__substring");
            substringFunc.function.retyrnType=ir.getType(substringFunc.returnType);
            return;
        }
        if(node.caller.type.isString() && node.isFunc && node.callee.equals("parseInt")){
            funcSymbol parseIntFunc= new funcSymbol("parseInt");
            parseIntFunc.returnType=new primitiveType("int");
            node.type=parseIntFunc;
            parseIntFunc.function=new Function("__builtIn__parseInt");
            parseIntFunc.function.retyrnType=ir.getType(parseIntFunc.returnType);
            return;
        }
        if(node.caller.type.isString() && node.isFunc&& node.callee.equals("ord")){
            funcSymbol ordFunc=new funcSymbol("ord");
            ordFunc.returnType=new primitiveType("int");
            ordFunc.paramList.add(new varSymbol("pos",new primitiveType("int")));
            node.type=ordFunc;
            ordFunc.function=new Function("__builtIn__stringOrd");
            ordFunc.function.retyrnType=ir.getType(ordFunc.returnType);
            return;
        }

        if (!(node.caller.type instanceof classType))
            throw new semanticError("not a class", node.pos);
        classType classType = (classType) node.caller.type;
        if (node.isFunc) {
            if (classType.funcMap.containsKey(node.callee))
                node.type = classType.funcMap.get(node.callee);
            else
                throw new semanticError("no such symbol", node.pos);
        } else {
            if (classType.varMap.containsKey(node.callee))
                node.type = classType.varMap.get(node.callee).type;
            else
                throw new semanticError("no such symbol", node.pos);

            node.isAssignable=true;
        }
    }

    @Override
    public void visit(newExpNode node) {
        if (node.expList != null) {
            for (ExpressionNode i : node.expList) {
                i.accept(this);
                if (!i.type.isInt())
                    throw new semanticError("not int", i.pos);
            }
        }
        node.type = globalScope.getType(node.typeNode);
    }

    @Override
    public void visit(backselfExpNode node) {
        node.exp.accept(this);
        if (!node.exp.type.isInt())
            throw new semanticError("require int type", node.pos);
        if (!node.exp.isAssignable)
            throw new semanticError("not assignable", node.pos);
        node.type = node.exp.type;

        node.isAssignable=false;
    }

    @Override
    public void visit(frontselfExpNode node) {
        node.exp.accept(this);
        if (!node.exp.type.isInt())
            throw new semanticError("require int type", node.pos);
        if (!node.exp.isAssignable)
            throw new semanticError("not assignable", node.pos);
        node.isAssignable=true;
        node.type = node.exp.type;
    }

    @Override
    public void visit(funcExpNode node) {
        if (node.master instanceof varExpNode)
            node.master.type = currentScope.getFunction(((varExpNode) node.master).name, node.pos, true);
        else
            node.master.accept(this);
        if (!(node.master.type instanceof funcSymbol))
            throw new semanticError("not a function", node.pos);
        funcSymbol func = (funcSymbol) node.master.type;
        node.expList.forEach(x -> x.accept(this));
        if (func.paramList.size() != node.expList.size())
            throw new semanticError("paramenter size not match", node.pos);
        for (int i = 0; i < func.paramList.size(); i++) {
            if (!func.paramList.get(i).type.sameType(node.expList.get(i).type))
                throw new semanticError("parameter type error", node.pos);
        }
        node.type = func.returnType;
    }

    @Override
    public void visit(preLogicExpNode node) {
        node.exp.accept(this);
        if (node.op.equals("!")) {
            if (!node.exp.type.isBool())
                throw new semanticError("require bool type", node.pos);
        } else if (node.op.equals("~")) {
            if (!node.exp.type.isInt())
                throw new semanticError("require int type", node.pos);
        }
        node.type = node.exp.type;
    }

    @Override
    public void visit(arrayExpNode node) {
        node.master.accept(this);
        node.index.accept(this);
        if (!(node.master.type instanceof arrayType))
            throw new semanticError("not an array type", node.pos);
        if (!node.index.type.isInt())
            throw new semanticError("index not an int", node.pos);
        arrayType arrayType = (arrayType) node.master.type;
        if (arrayType.dimen == 1)
            node.type = arrayType.atomType;
        else
            node.type = new arrayType(arrayType.atomType, arrayType.dimen - 1);

        node.isAssignable=true;
    }

    @Override
    public void visit(expressionGroupNode node) {

    }

    @Override
    public void visit(boolLiteralNode node) {
        node.type=new primitiveType("bool");
    }

    @Override
    public void visit(intLiteralNode node) {
        node.type=new primitiveType("int");
    }

    @Override
    public void visit(stringLiteralNode node) {
        node.type=new primitiveType("string");
    }

    @Override
    public void visit(nullLiteralNode node) {
        node.type=new primitiveType("null");
    }

    @Override
    public void visit(thisExpNode node) {
        if(currentClass !=null)
            node.type=currentClass;
        else
            throw new semanticError("not in class",node.pos);
    }

    @Override
    public void visit(varExpNode node) {
        node.type = currentScope.getVariable(node.name, node.pos, true).type;
        node.varSymbol = currentScope.getVariable(node.name, node.pos, true);
        node.isAssignable=true;
    }

//    @Override
//    public void visit(mainNode node) {
//
//    }
}
