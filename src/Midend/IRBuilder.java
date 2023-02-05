package Midend;

import AST.Nodes.ASTVisitor;
import IR.IR;
import IR.Block;
import IR.Function;
import IR.IRType.*;
import IR.operand.*;
import IR.instructions.*;

import AST.Scope;
import AST.Symbols.*;
import AST.Types.*;
import AST.Nodes.*;

import Utility.errors.*;

import java.awt.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;


public class IRBuilder implements ASTVisitor {
    public IR iR = null;
    public Block currentBlock = null;
    public Function currentFunction = null;
    public classIRType currentClass = null;
    public int loopDepth = 0;

    public LinerTree linerTree;

    public HashSet<Function> visited;
    public Stack<Function> visitStack;

    public HashMap<Function, ArrayList<Function>> funcLink;
    public HashMap<Function, ArrayList<Call>> callLink;
    public HashMap<Function, ArrayList<Function>> reFuncLink;
    public HashSet<Function> canInLine;
    public HashSet<Function> canNotInLine;

    int inlineBlockLimit=35;
    int inlineInstLimit=500;
    int inlineCount = 0;

    String preName;

    public HashMap<Block, Block> inlineBlocks;
    public HashMap<operand, operand> inlineValues;


    public IRBuilder(IR iR_in) {
        iR = iR_in;
    }

    public register getPtrContain(operand pointer_in) {
        register tmp = new register(((pointerIRType) pointer_in.irType).ptrType, "tmp.");
        currentBlock.addInst(new Load(currentBlock, tmp, pointer_in));
        return tmp;
    }

    public operand getReg(operand o_in) {
        if (o_in.irType instanceof pointerIRType)
            return getPtrContain(o_in);
        return o_in;
    }


    public void choseBranch(ExpressionNode node) {
        if (node.trueSent != null) {
            if (node.trueSent.branchFrom != null)
                node.trueSent.branchFrom.add(currentBlock, new constBool(true));
            if (node.falseSent.branchFrom != null)
                node.falseSent.branchFrom.add(currentBlock, new constBool(false));
            currentBlock.addEndInst(new Branch(currentBlock, getReg(node.operand), node.trueSent, node.falseSent));
        }
    }

    @Override
    public void visit(typeNode node) {

    }

    @Override
    public void visit(programNode node) {
        for (ASTNode i : node.proBody) {
            if (i instanceof classDefNode) {
                currentClass = ((classDefNode) i).classType.classIrType;
                for (varDefSubNode j : ((classDefNode) i).varList) {
                    j.accept(this);
                }
                currentClass = null;
            }
        }
        for (ASTNode i : node.proBody) {
            i.accept(this);
        }
    }

    @Override
    public void visit(funcDefNode node) {
        currentFunction = node.funcSymbol.function;
        if (currentClass != null) { //member function
            currentFunction.name = currentClass.name + "." + node.name;
            currentClass.func.add(currentFunction);
            currentFunction.classPtr = new register(new pointerIRType(currentClass), "this");
            currentFunction.params.add((register) currentFunction.classPtr);
        }
        iR.funcs.put(currentFunction.name, currentFunction);

//        System.out.println(currentFunction.beginBlock.intoString());

        currentBlock = currentFunction.beginBlock;
        if (currentFunction.name.equals("main")) {
            currentBlock.addInst(new Call(currentBlock, null, iR.funcs.get("__init"))); // put in global vars
        }
        for (varDefSubNode i : node.paramList) {
            i.varSymbol.operand = new register(iR.getType(i.varSymbol.type), i.name);
            currentFunction.params.add((register) i.varSymbol.operand);
        }
        node.codeBlock.accept(this);
        if (!currentBlock.is_end) {
            Return tempReturn;
            if (currentFunction.name.equals("main"))
                tempReturn = new Return(currentBlock, new constInt(0, 32));
            else if (currentFunction.returnType.sameType(new voidIRType()))
                tempReturn = new Return(currentBlock, new constVoid());
            else if (currentFunction.returnInsts.isEmpty()) {

//                System.out.println(currentFunction.name);
//                System.out.println(currentFunction.returnType.intoString());

                throw new innerError("no return sentence.");
            } else
                tempReturn = new Return(currentBlock, new constVoid());
            currentBlock.addEndInst(tempReturn);
            currentFunction.returnInsts.add(tempReturn);
        }
        if (currentFunction.returnInsts.size() > 1) { // multi-return cases
            Block sumUpBlock = new Block(loopDepth);
            Return tempReturn;
            if (!currentFunction.returnType.sameType(new voidIRType())) {
                register phiReg = new register(currentFunction.returnType, "tmp.");
                Phi phi = new Phi(sumUpBlock, phiReg);
                for (Return i : currentFunction.returnInsts) {
                    phi.add(i.block, ((Return) i.block.getEndInst()).value);
                }
                sumUpBlock.addInst(phi);
                tempReturn = new Return(sumUpBlock, phiReg);
            } else {
                tempReturn = new Return(sumUpBlock, new constVoid());
            }
            sumUpBlock.addEndInst(tempReturn);
            for (Return i : currentFunction.returnInsts) {
                i.block.popEndInst();
                i.block.addEndInst(new Jump(i.block, sumUpBlock));
            }
            currentFunction.returnInsts = new ArrayList<>(Collections.singletonList(tempReturn));
        }
        currentFunction = null;
    }

    @Override
    public void visit(varDefSubNode node) {
        basicIRType typeNow = iR.getType(node.varSymbol.type);
        if (node.varSymbol.isGlobalVar) {
            register reg = new register(new pointerIRType(typeNow), node.name);
            reg.isGlobal = true;
            reg.isConstPtr = true;
            node.varSymbol.operand = reg;
            iR.globalVars.put(node.name, reg);
            if (node.expression != null) {
                currentFunction = iR.funcs.get("__init");
                currentBlock = iR.destBlock;
                node.expression.accept(this);
                assignment(node.varSymbol.operand, node.expression.operand);
                iR.destBlock = currentBlock;
                currentBlock = null;
                currentFunction = null;
            }
        } else {
            node.varSymbol.operand = new register(typeNow, node.name);
            if (currentClass != null && currentFunction == null) { // vars in class but not in class function
                node.varSymbol.isClassVal = true;
                ((register) node.varSymbol.operand).name = currentClass.name + "." + node.name;
                currentClass.addVar((register) node.varSymbol.operand);
            } else {
                if (node.expression != null) {
                    node.expression.accept(this);
                    assignment(node.varSymbol.operand, node.expression.operand);
                }
            }
        }
    }

    @Override
    public void visit(varDefNode node) {
        for (varDefSubNode i : node.varDefList) {
            i.accept(this);
        }
    }

    @Override
    public void visit(classDefNode node) {
        currentClass = node.classType.classIrType;
        for (funcDefNode i : node.funcList) {
            i.accept(this);
        }
        if (node.constructor != null)
            node.constructor.accept(this);
        iR.classes.put(currentClass.name, currentClass);
        currentClass = null;
    }

    @Override
    public void visit(lambdaDefNode node) {
        throw new innerError("Undefined Behaviour: Lambda exp");
    }

    @Override
    public void visit(codeBlockNode node) {
        for (SentenceNode i : node.sentencesList) {
            i.accept(this);
            if (currentBlock.is_end) // cut all insts after return
                break;
        }
    }

    @Override
    public void visit(ifSentNode node) {
        Block destination = new Block(loopDepth);
        Block trueBlock = new Block(loopDepth);
        Block falseBlock = new Block(loopDepth);
        node.cond.trueSent = trueBlock;
        node.cond.falseSent = node.falseSent != null ? falseBlock : destination;
        node.cond.accept(this);
        currentBlock = trueBlock;
        node.trueSent.accept(this);
        currentBlock.addEndInst(new Jump(currentBlock, destination));
        if (node.falseSent != null) {
            currentBlock = falseBlock;
            node.falseSent.accept(this);
            currentBlock.addEndInst(new Jump(currentBlock, destination));
        }
        currentBlock = destination;
    }

    @Override
    public void visit(forSentNode node) {
        loopDepth++;
        Block conditionBlock = new Block(loopDepth);
        Block destinationBlock = new Block(loopDepth);
        Block moveBlock = new Block(loopDepth);
        Block repeatBlock = new Block(loopDepth);
        node.destinationBlock = destinationBlock;
        node.moveBlock = moveBlock;
        if (node.init != null) {
            node.init.accept(this);
            if (node.initDef != null)
                throw new innerError("Both init and initDef in for sentence.");
        }
        if (node.initDef != null) {
            node.initDef.accept(this);
            if (node.init != null)
                throw new innerError("Both init and initDef in for sentence.");
        }
        if (node.cond != null) {
            currentBlock.addEndInst(new Jump(currentBlock, conditionBlock));
            currentBlock = conditionBlock;
            node.cond.trueSent = repeatBlock;
            node.cond.falseSent = destinationBlock;
            node.cond.accept(this);
        } else {
            currentBlock.addEndInst(new Jump(currentBlock, repeatBlock));
            conditionBlock = repeatBlock;
        }
        currentBlock = repeatBlock;
        node.repeSent.accept(this);
        currentBlock.addEndInst((new Jump(currentBlock, moveBlock)));
        currentBlock = moveBlock;
        if (node.move != null)
            node.move.accept(this);
        currentBlock.addEndInst(new Jump(currentBlock, conditionBlock));
        currentBlock = destinationBlock;
        loopDepth--;
    }

    @Override
    public void visit(whileSentNode node) {
        loopDepth++;
        Block conditionBlock = new Block(loopDepth);
        Block repeatBlock = new Block(loopDepth);
        Block destinationBlock = new Block(loopDepth);
        node.destinationBlock = destinationBlock;
        node.conditionBlock = conditionBlock;
        currentBlock.addEndInst(new Jump(currentBlock, conditionBlock));
        currentBlock = conditionBlock;
        node.cond.trueSent = repeatBlock;
        node.cond.falseSent = destinationBlock;
        node.cond.accept(this);
        currentBlock = repeatBlock;
        node.repeSent.accept(this);
        currentBlock.addEndInst(new Jump(currentBlock, conditionBlock));
        currentBlock = destinationBlock;
        loopDepth--;
    }

    @Override
    public void visit(returnSentNode node) {
        Return ans;
        if (node.value != null) {
            node.value.accept(this);
            if (node.value.operand instanceof register && ((register) node.value.operand).isConstPtr) { //pointer case
                ans = new Return(currentBlock, getReg(node.value.operand));
            } else {
                ans = new Return(currentBlock, node.value.operand);
            }
            currentBlock.addEndInst(ans);
        } else {
            ans = new Return(currentBlock, new constVoid());
            currentBlock.addEndInst(ans);
        }
        currentFunction.returnInsts.add(ans);
    }

    @Override
    public void visit(breakSentNode node) {
        if (node.loop instanceof forSentNode) {
            currentBlock.addEndInst(new Jump(currentBlock, ((forSentNode) node.loop).destinationBlock));
        } else if (node.loop instanceof whileSentNode) {
            currentBlock.addEndInst(new Jump(currentBlock, ((whileSentNode) node.loop).destinationBlock));
        }
    }

    @Override
    public void visit(continueSentNode node) {
        if (node.loop instanceof forSentNode) {
            currentBlock.addEndInst(new Jump(currentBlock, ((forSentNode) node.loop).moveBlock));
        } else if (node.loop instanceof whileSentNode) {
            currentBlock.addEndInst(new Jump(currentBlock, ((whileSentNode) node.loop).conditionBlock));
        }
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
        operand expression = getReg(node.exp.operand);
        node.operand = new register(expression.irType, "tmp.");
        if (Objects.equals(node.op, "+")) {
            node.operand = expression;
        } else if (Objects.equals(node.op, "-")) {
            currentBlock.addInst(new Binary(currentBlock, (register) node.operand, new constInt(0, 32), expression, "sub"));
        }
        choseBranch(node);
    }

    @Override
    public void visit(binaryExpNode node) {
        String op;
        String strFuncName;
        operand lhs, rhs;
        switch (node.op) {
            case "*":
                op = "mul";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "/":
                op = "sdiv";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "%":
                op = "srem";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "+":
                op = "add";
                strFuncName = "__builtIn__stringAdd";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new stringIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                }
                break;
            case "-":
                op = "sub";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "<<":
                op = "shl";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case ">>":
                op = "ashr";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "<":
                op = "slt";
                strFuncName = "__builtIn__stringLt";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst(new Icmp(currentBlock, (register) node.operand, lhs, rhs, op));
                }
                choseBranch(node);
                break;
            case ">":
                op = "sgt";
                strFuncName = "__builtIn__strGt";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst(new Icmp(currentBlock, (register) node.operand, lhs, rhs, op));
                }
                choseBranch(node);
                break;
            case "<=":
                op = "sle";
                strFuncName = "__builtIn__stringLe";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst(new Icmp(currentBlock, (register) node.operand, lhs, rhs, op));
                }
                choseBranch(node);
                break;
            case ">=":
                op = "sge";
                strFuncName = "__builtIn__stringGe";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst(new Icmp(currentBlock, (register) node.operand, lhs, rhs, op));
                }
                choseBranch(node);
                break;
            case "==":
                op = "eq";
                strFuncName = "__builtIn__stringEq";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = node.lexp.operand;
                    if (lhs instanceof register && ((register) lhs).isConstPtr)
                        lhs = getReg(lhs);
                    rhs = node.rexp.operand;
                    if (rhs instanceof register && ((register) rhs).isConstPtr)
                        rhs = getReg(rhs);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst((new Icmp(currentBlock, (register) node.operand, lhs, rhs, op)));
                }
                choseBranch(node);
                break;
            case "!=":
                op = "ne";
                strFuncName = "__builtIn__stringNe";
                if (node.lexp.type.isString()) {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = getReg(node.lexp.operand);
                    rhs = getReg(node.rexp.operand);
                    node.operand = new register(lhs.irType, "tmp.");
                    Function func = new Function(strFuncName);
                    func.returnType = new boolIRType();
                    Call inst = new Call(currentBlock, (register) node.operand, func);
                    inst.params.add(lhs);
                    inst.params.add(rhs);
                    currentBlock.addInst(inst);
                } else {
                    node.lexp.accept(this);
                    node.rexp.accept(this);
                    lhs = node.lexp.operand;
                    if (lhs instanceof register && ((register) lhs).isConstPtr)
                        lhs = getReg(lhs);
                    rhs = node.rexp.operand;
                    if (rhs instanceof register && ((register) rhs).isConstPtr)
                        rhs = getReg(rhs);
                    node.operand = new register(new boolIRType(), "tmp.");
                    currentBlock.addInst((new Icmp(currentBlock, (register) node.operand, lhs, rhs, op)));
                }
                choseBranch(node);
                break;
            case "&&":
                if (node.trueSent != null) {
                    Block temp = new Block(loopDepth);
                    node.lexp.trueSent = temp;
                    node.lexp.falseSent = node.falseSent;
                    node.rexp.trueSent = node.trueSent;
                    node.rexp.falseSent = node.falseSent;
                    node.lexp.accept(this);
                    currentBlock = temp;
                    node.rexp.accept(this);
                } else {
                    Block temp = new Block(loopDepth);
                    Block destination = new Block(loopDepth);
                    node.operand = new register(new boolIRType(), "tmp.");
                    Phi phi = new Phi(destination, (register) node.operand);
                    destination.branchFrom = phi;
                    node.lexp.trueSent = temp;
                    node.lexp.falseSent = destination;
                    node.lexp.accept(this);
                    currentBlock = temp;
                    node.rexp.accept(this);
                    phi.add(currentBlock, node.rexp.operand);
                    currentBlock.addEndInst(new Jump(currentBlock, destination));
                    currentBlock = destination;
                    currentBlock.addInst(phi);
                }
                break;
            case "||":
                if (node.trueSent != null) {
                    Block temp = new Block(loopDepth);
                    node.lexp.trueSent = node.trueSent;
                    node.lexp.falseSent = temp;
                    node.rexp.trueSent = node.trueSent;
                    node.rexp.falseSent = node.falseSent;
                    node.lexp.accept(this);
                    currentBlock = temp;
                    node.rexp.accept(this);
                } else {
                    Block temp = new Block(loopDepth);
                    Block destination = new Block(loopDepth);
                    node.operand = new register(new boolIRType(), "tmp.");
                    node.lexp.trueSent = destination;
                    node.lexp.falseSent = temp;
                    node.lexp.accept(this);
                    Block lexpBlock = currentBlock;
                    currentBlock = temp;
                    node.rexp.accept(this);
                    Block rexpBlock = currentBlock;
                    currentBlock.addEndInst(new Jump(currentBlock, destination));
                    currentBlock = destination;
                    Phi phi = new Phi(currentBlock, (register) node.operand);
                    phi.add(lexpBlock, new constBool(true));
                    phi.add(rexpBlock, node.rexp.operand);
                    currentBlock.addInst(phi);
                }
                break;
            case "&":
                op = "and";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "^":
                op = "xor";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "|":
                op = "or";
                node.lexp.accept(this);
                node.rexp.accept(this);
                lhs = getReg(node.lexp.operand);
                rhs = getReg(node.rexp.operand);
                node.operand = new register(lhs.irType, "tmp.");
                currentBlock.addInst(new Binary(currentBlock, (register) node.operand, lhs, rhs, op));
                break;
            case "=":
                node.lexp.accept(this);
                node.rexp.accept(this);
                node.operand = node.lexp.operand;
                assignment(node.lexp.operand, node.rexp.operand);
                break;
            default:
                break;
        }
    }

    @Override
    public void visit(memberExpNode node) {
        node.caller.accept(this);
        if (node.isFunc) {
            node.operand = node.caller.operand;
        } else {
            operand temp = node.caller.operand;
            if (((register) temp).isConstPtr)
                temp = getReg(temp);
            classIRType classType = (classIRType) ((pointerIRType) temp.irType).ptrType;
            node.operand = new register(new pointerIRType(classType.getVarRegister(node.name).irType), "tmp.");
            int index = classType.getID(node.name);
            currentBlock.addInst(new GetElementPtr(currentBlock, node.operand, temp, new constInt(0, 32), new constInt(index, 32)));
            ((register) node.operand).isConstPtr = true;
        }
        choseBranch(node);
    }

    @Override
    public void visit(newExpNode node) {
        if (node.expList != null)
            for (ExpressionNode i : node.expList) {
                i.accept(this);
            }
        if (node.type instanceof arrayType) {
            node.operand = arrayCreation(0, node, iR.getType(node.type));
        } else {
            register mallocReg = new register(new pointerIRType(new intIRType(8)), "tmp.");
            classIRType classType = ((classType) node.type).classIrType;
            node.operand = new register(new pointerIRType(classType), "tmp.");
            Call inst = new Call(currentBlock, mallocReg, iR.mallocFunc);
            inst.params.add(new constInt(classType.size() / 8, 32));
            currentBlock.addInst(inst);
            currentBlock.addInst(new BitCast(currentBlock, node.operand, mallocReg));


//            for (instruction i: currentBlock.inst){
//                System.out.println(i.intoString());
//            }


            if (classType.constructor != null) {
                Call instC = new Call(currentBlock, null, classType.constructor);
                instC.params.add(node.operand);
                currentBlock.addInst(instC);
            }
        }
    }

    @Override
    public void visit(backselfExpNode node) {
        node.exp.accept(this);
        operand expression = getReg(node.exp.operand);
        node.operand = new register(expression.irType, "tmp.");
        assignment(node.operand, expression);
        register tempReg = new register(expression.irType, "tmp.");
        if (Objects.equals(node.op, "++"))
            currentBlock.addInst(new Binary(currentBlock, tempReg, expression, new constInt(1, 32), "add"));
        else if (Objects.equals(node.op, "--"))
            currentBlock.addInst(new Binary(currentBlock, tempReg, expression, new constInt(1, 32), "sub"));
        assignment(node.exp.operand, tempReg);
        choseBranch(node);
    }

    @Override
    public void visit(frontselfExpNode node) {
        node.exp.accept(this);
        operand expression = getReg(node.exp.operand);
        node.operand = new register(expression.irType, "tmp.");
        if (node.op.equals("++")) {
            currentBlock.addInst(new Binary(currentBlock, (register) node.operand, expression, new constInt(1, 32), "add"));
            assignment(node.exp.operand, node.operand);
        } else if (node.op.equals("--")) {
            currentBlock.addInst(new Binary(currentBlock, (register) node.operand, expression, new constInt(1, 32), "sub"));
            assignment(node.exp.operand, node.operand);
        }
        choseBranch(node);
    }

    @Override
    public void visit(funcExpNode node) {
        operand thisPtr = null;
        if (!(node.master instanceof varExpNode)) {
            node.master.accept(this);
            if (((register) node.master.operand).isConstPtr)
                thisPtr = getReg(node.master.operand);
            else
                thisPtr = node.master.operand;
        }
        if (node.master instanceof memberExpNode && ((memberExpNode) node.master).caller.type instanceof arrayType) {
            register sizeReg = new register(new pointerIRType(new intIRType(32)), "tmp.");
            register bitReg = new register(new pointerIRType(new intIRType(32)), "tmp.");
            node.operand = new register(new intIRType(32), "tmp.");
            currentBlock.addInst(new BitCast(currentBlock, bitReg, thisPtr));
            currentBlock.addInst(new GetElementPtr(currentBlock, sizeReg, bitReg, new constInt(-1, 32)));
            currentBlock.addInst(new Load(currentBlock, (register) node.operand, sizeReg));
            return;
        }
        Function func = ((funcSymbol) node.master.type).function;
        node.operand = new register(func.returnType, "tmp.");
        Call inst = new Call(currentBlock, (register) node.operand, func);
        if (thisPtr != null)
            inst.params.add(thisPtr);
        if (node.master instanceof varExpNode && func.classFunc)
            inst.params.add(currentFunction.classPtr);
        for (ExpressionNode i : node.expList) {
            i.accept(this);
            if (i.operand instanceof register && ((register) i.operand).isConstPtr)
                i.operand = getReg(i.operand);
            inst.params.add(i.operand);
        }
        currentBlock.addInst(inst);
        choseBranch(node);
    }

    @Override
    public void visit(preLogicExpNode node) {
        node.exp.accept(this);
        operand expression = getReg(node.exp.operand);
        node.operand = new register(expression.irType, "tmp.");
        if (Objects.equals(node.op, "!")) {
            currentBlock.addInst(new Binary(currentBlock, (register) node.operand, expression, new constBool(true), "xor"));
        } else if (Objects.equals(node.op, "~")) {
            currentBlock.addInst(new Binary(currentBlock, (register) node.operand, expression, new constInt(-1, 32), "xor"));
        }
        choseBranch(node);
    }

    @Override
    public void visit(arrayExpNode node) {
        node.master.accept(this);
        node.index.accept(this);
        if (((register) node.master.operand).isConstPtr)
            node.master.operand = getReg(node.master.operand);
        register tempPtr = new register(node.master.operand.irType, "tmp.");
        currentBlock.addInst(new GetElementPtr(currentBlock, tempPtr, node.master.operand, getReg(node.index.operand)));
        node.operand = tempPtr;
        ((register) node.operand).isConstPtr = true;
        choseBranch(node);
    }

    @Override
    public void visit(expressionGroupNode node) {

    }

    @Override
    public void visit(boolLiteralNode node) {
        node.operand = new constBool(node.value);
        choseBranch(node);
    }

    @Override
    public void visit(intLiteralNode node) {
        node.operand = new constInt(node.value, 32);
    }

    @Override
    public void visit(stringLiteralNode node) {
        String name = "const_str_" + iR.constString.size();
        String value = node.value.substring(1, node.value.length() - 1);
        node.operand = new constString(name, value);
        iR.constString.put(name, (constString) node.operand);
    }

    @Override
    public void visit(nullLiteralNode node) {
        node.operand = new Null();
    }

    @Override
    public void visit(thisExpNode node) {
        node.operand = currentFunction.classPtr;
    }

    @Override
    public void visit(varExpNode node) {
        if (node.varSymbol.isClassVal) {
            classIRType classIrType = (classIRType) ((pointerIRType) currentFunction.classPtr.irType).ptrType;
            node.operand = new register(new pointerIRType(classIrType.getVarRegister(node.name).irType), "tmp.");
            int index = classIrType.getID(node.name);
            currentBlock.addInst(new GetElementPtr(currentBlock, node.operand, currentFunction.classPtr, new constInt(0, 32), new constInt(index, 32)));
            ((register) node.operand).isConstPtr = true;
        } else {
            node.operand = node.varSymbol.operand;
        }
        choseBranch(node);
    }

    public void assignment(operand lhs, operand rhs) {
        if (rhs instanceof Null) {
            if (((pointerIRType) lhs.irType).ptrType instanceof pointerIRType)
                currentBlock.addInst(new Store(currentBlock, lhs, new Null(((pointerIRType) lhs.irType).ptrType)));
            else
                currentBlock.addInst((new Assign(currentBlock, lhs, rhs)));
        } else {
            if (lhs instanceof register) {
                if (lhs.irType instanceof pointerIRType && lhs.irType.sameType(rhs.irType)) { // assign between pointers
                    if (((register) lhs).isConstPtr) {
                        rhs = getReg(rhs);
                        currentBlock.addInst(new Store(currentBlock, lhs, rhs));
                    } else {
                        currentBlock.addInst(new Assign(currentBlock, lhs, rhs));
                    }
                } else if (lhs.irType instanceof pointerIRType && ((pointerIRType) lhs.irType).ptrType.sameType(rhs.irType)) { // assign between pointer and basic value
                    currentBlock.addInst(new Store(currentBlock, lhs, rhs));
                } else { // assign between basic types
                    rhs = getReg(rhs);
                    currentBlock.addInst(new Assign(currentBlock, lhs, rhs));
                }
            } else {
                throw new innerError("assignment error: lhs not a register.");
            }
        }
    }

    public register arrayCreation(int dimension, newExpNode node, basicIRType returnType) {
        basicIRType itemType = ((pointerIRType) returnType).ptrType;
        register dataSize = new register(new intIRType(32), "tmp.");
        register arraySize = new register(new intIRType(32), "tmp.");
        register sizeReg = new register(new pointerIRType(new intIRType(32)), "tmp.");
        register arrayReg = new register(new pointerIRType(new intIRType(32)), "tmp.");
        register arrayPtr = new register(returnType, "tmp.");
        register mallocReg = new register(new pointerIRType(new intIRType(8)), "tmp.");
        operand size = node.expList.get(dimension).operand;
        if (size instanceof register && ((register) size).isConstPtr)
            size = getReg(size);
        currentBlock.addInst(new Binary(currentBlock, dataSize, size, new constInt(itemType.size() / 8, 32), "mul"));
        currentBlock.addInst(new Binary(currentBlock, arraySize, dataSize, new constInt(4, 32), "add"));
        Call inst = new Call(currentBlock, mallocReg, iR.mallocFunc);
        inst.params.add(arraySize);
        currentBlock.addInst(inst);
        currentBlock.addInst(new BitCast(currentBlock, sizeReg, mallocReg));
        currentBlock.addInst(new Store(currentBlock, sizeReg, size));
        currentBlock.addInst(new GetElementPtr(currentBlock, arrayReg, sizeReg, new constInt(1, 32)));
        currentBlock.addInst(new BitCast(currentBlock, arrayPtr, arrayReg));
        if (dimension < node.expList.size() - 1) {
            loopDepth++;
            Block masterBlock = new Block(loopDepth);
            Block conditionBlock = new Block(loopDepth);
            Block destination = new Block(loopDepth);
            register iReg = new register(new intIRType(32), "i");
            currentBlock.addInst(new Assign(currentBlock, iReg, new constInt(0, 32)));
            currentBlock.addEndInst(new Jump(currentBlock, masterBlock));
            currentBlock = masterBlock;
            register iPtr = new register(returnType, "tmp.");
            currentBlock.addInst(new GetElementPtr(currentBlock, iPtr, arrayPtr, iReg));
            register item = arrayCreation(dimension + 1, node, itemType);
            currentBlock.addInst(new Store(currentBlock, iPtr, item));
            currentBlock.addEndInst(new Jump(currentBlock, conditionBlock));
            currentBlock = conditionBlock;
            currentBlock.addInst(new Binary(currentBlock, iReg, iReg, new constInt(1, 32), "add"));
            register cmp = new register(new boolIRType(), "tmp");
            currentBlock.addInst(new Icmp(currentBlock, cmp, iReg, size, "slt"));
            currentBlock.addEndInst(new Branch(currentBlock, cmp, masterBlock, destination));
            currentBlock = destination;
            loopDepth--;
        }
        return arrayPtr;
    }

    public void dfsBlock(Block node) {
        node.name = "block." + currentFunction.blocks.size();
        currentFunction.blocks.add(node);
        node.nextBlocks.forEach(i -> {
            if (!currentFunction.blocks.contains(i))
                dfsBlock(i);
        });
    }

    public void getPhi() {
        linerTree = new LinerTree(currentFunction);
        linerTree.run();
        for (register reg : currentFunction.vars) {
            HashSet<Block> contain = new HashSet<>();
            for (int i = 0; i < reg.assign.size(); i++) {
                instruction inst = reg.assign.get(i);
                for (Block k : linerTree.subP.get(inst.block)) {
                    if (!contain.contains(k)) {
                        Phi phi = new Phi(k, reg);
                        phi.isMain = true;
                        k.addFrontInst(phi);
                        reg.assign.add(phi);
                        contain.add(k);
                    }
                }
            }
        }
    }

    public void variablesDiffer() { //rename variables with same name.
        ArrayList<register> list = new ArrayList<>(currentFunction.vars);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).name.equals(list.get(i).name)) {
                    list.get(j).name = list.get(i).name + "_rename";

//                    System.out.println("variablesDiffer"+list.get(j).name);
                }
            }
        }
    }

    public void preHandle(Block node) {
        // cut blocks;
        dfsBlock(node);
        currentFunction.blocks.forEach(k -> {
            for (int i = 0; i < k.prevBlocks.size(); i++) { // some blocks may not reached
                if (k.prevBlocks.get(i).name == null) {
                    k.prevBlocks.remove(i);
                    i--;
                }
            }
        });
        //change related phi
        for (Block block : currentFunction.blocks) {
            for (instruction inst : block.inst) {
                if (inst instanceof Phi) {
                    for (int i = 0; i < ((Phi) inst).blocks.size(); i++) {
                        if (!currentFunction.blocks.contains(((Phi) inst).blocks.get(i))) {
                            ((Phi) inst).blocks.remove(i);
                            ((Phi) inst).values.remove(i);
                            i--;
                        }
                    }
                }
            }
        }
    }

    public void variableSet() {
        currentFunction.vars.forEach(var -> {
            var.renamingStack.push(new register(var.irType, var.name + "_" + (var.renamingID++)));
            for (int i = 0; i < currentFunction.params.size(); i++) {
                if (currentFunction.params.get(i) == var) {
                    var.renamingStack.push(new register(var.irType, var.name + "_" + (var.renamingID++)));
                    currentFunction.params.set(i, var.renamingStack.peek());
                }
            }
            variableRenaming(var, currentFunction.beginBlock);
        });
    }

    public void variableRenaming(register reg, Block node) {
        register oldTop = reg.renamingStack.peek(); //reserve begin peek
        for (instruction i : node.inst) {
            if (!(i instanceof Phi) || !((Phi) i).isMain) // not set in getPhi
                i.replace(reg, reg.renamingStack.peek());
            if (i.register != null && i.register == reg) {
                i.register = new register(reg.irType, reg.name + "_" + (reg.renamingID++));
                reg.renamingStack.push(i.register);
            }
        }
        for (Block i : node.nextBlocks) {
            for (instruction inst : i.inst) {
                if (inst instanceof Phi && ((Phi) inst).phiRegister == reg) {
                    if (reg.renamingStack.size() > 1)
                        ((Phi) inst).add(node, reg.renamingStack.peek());
                    else ((Phi) inst).add(node, reg.irType.getInit());
                }
            }
        }
        for (Block i : linerTree.subSon.get(node)) {
            variableRenaming(reg, i);
        }
        while (reg.renamingStack.peek() != oldTop)
            reg.renamingStack.pop();
    }

    public void phiSimplify() {
        // phi only once
        for (Block blk : currentFunction.blocks) {
            for (int i = 0; i < blk.inst.size(); i++) {
                instruction inst = blk.inst.get(i);
                if (inst instanceof Phi && ((Phi) inst).values.size() == 1) {
                    blk.inst.set(i, new Assign(blk, inst.register, ((Phi) inst).values.get(0)));
                }
            }
        }
        // phi in phi
        for (Block blk : currentFunction.blocks) {
            HashMap<register, Phi> phis = new HashMap<>();
            for (instruction i : blk.inst) {
                if (i instanceof Phi)
                    phis.put(i.register, (Phi) i);
            }
            for (instruction inst : blk.inst) {
                if (inst instanceof Phi) {
                    for (int i = 0; i < ((Phi) inst).values.size(); i++) {
                        if (((Phi) inst).values.get(i) instanceof register && phis.get((register) ((Phi) inst).values.get(i)) != null) {
                            Phi tempPhi = phis.get((register) ((Phi) inst).values.get(i));
                            for (int j = 0; j < tempPhi.values.size(); j++) {
                                if (tempPhi.blocks.get(j) == ((Phi) inst).blocks.get(i)) {
                                    ((Phi) inst).values.set(i, tempPhi.values.get(j));
                                }
                            }
                        }
                    }
                }
            }
        }
        // phi unused
        boolean condition = true;
        while (condition) {
            condition = false;
            for (Block blk : currentFunction.blocks) {
                for (instruction inst : blk.inst) {
                    ArrayList<operand> operands = inst.getUsedOperand();
                    for (operand o : operands) {
                        if (o instanceof register)
                            ((register) o).used = true;
                    }
                }
            }
            for (Block blk : currentFunction.blocks) {
                for (int i = 0; i < blk.inst.size(); i++) {
                    instruction inst = blk.inst.get(i);
                    if (inst instanceof Phi) {
                        if (!inst.register.used) {
                            blk.popInst(inst);
                            condition = true;
                            i--;
                        } else {
                            inst.register.used = false;
                        }
                    }
                }
            }
        }
    }

    public void getVariables() {
        int tempRegCnt = 0;
        currentFunction.vars.addAll(currentFunction.params);
        for (Block block : currentFunction.blocks) {
            for (instruction inst : block.inst) {
                if (inst.register != null) {
                    if (!inst.register.name.equals("tmp.")) {
                        currentFunction.vars.add(inst.register);
                        inst.register.assign.add(inst);
                    } else {
                        inst.register.name = "tmp." + tempRegCnt;
                        tempRegCnt++;
                    }
                }
            }
        }
    }

    public void inlineInit() {
        visited = new HashSet<>();
        visitStack = new Stack<>();
        funcLink = new HashMap<>();
        callLink = new HashMap<>();
        reFuncLink = new HashMap<>();
        canInLine = new HashSet<>();
        canNotInLine = new HashSet<>();
    }

    public void getCall() {
        iR.funcs.forEach((name, func) -> {
            funcLink.put(func, new ArrayList<>());
            callLink.put(func, new ArrayList<>());
            reFuncLink.put(func, new ArrayList<>());
        });
        iR.funcs.forEach((name, func) -> {
            for (Block block : func.blocks) {
                for (instruction inst : block.inst) {
                    if (inst instanceof Call && !(((Call) inst).function.name.startsWith("__builtIn__"))) {
                        funcLink.get(func).add(((Call) inst).function);
                        callLink.get(((Call) inst).function).add((Call) inst);
                        reFuncLink.get(((Call) inst).function).add(func);
                    }
                }
            }
        });
    }

    public void dfsInLineCheck(Function func) {
        boolean isRingCall = false; // in case of a loop call
        visited.add(func);
        visitStack.push(func);
        for (Function f : visitStack) {
            if (funcLink.get(func).contains(f))
                isRingCall = true;
            if (isRingCall)
                canNotInLine.add(f);
        }
        for (Function f : funcLink.get(func)) {
            if (!visited.contains(f))
                dfsInLineCheck(f);
        }
        visitStack.pop();
    }

    public Function inlineCollectFuncNow;

    public void dfsBlockInLine(Block block) {
        inlineCollectFuncNow.blocks.add(block);
        for (Block b : block.nextBlocks) {
            if (!inlineCollectFuncNow.blocks.contains(b))
                dfsBlockInLine(b);
        }
    }

    public void setCanInLine() {
        canNotInLine.add(iR.funcs.get("main"));
        dfsInLineCheck(iR.funcs.get("main"));
        iR.funcs.forEach((name, func) -> {
            if (!canNotInLine.contains(func)) {
                int instCount = func.getInstCount();
                if (instCount <=inlineInstLimit && func.blocks.size() <= inlineBlockLimit) {
                    canInLine.add(func);
                }
            }
        });
    }

    public HashSet<Function> alreadyInLine = new HashSet<>();

    public operand getValueNew(operand old) {
        if (old != null) {
            if (!inlineValues.containsKey(old)) {
                if (old instanceof register && !((register) old).isGlobal) {
                    inlineValues.put(old, new register(old.irType, preName + ((register) old).name, ((register) old).isGlobal, ((register) old).isConstPtr));
                } else {
                    inlineValues.put(old, old);
                }
            }
            return inlineValues.get(old);
        } else
            return null;
    }

    public void setNewBlock(Function func) {
        for (Block oldBlock : func.blocks) {
            Block newBlock = new Block(oldBlock.loopDepth);
            newBlock.name = preName + oldBlock.name;
            newBlock.is_end = oldBlock.is_end;
            inlineBlocks.put(oldBlock, newBlock);
        }
    }

    public void moveInLine(Call call, Function callerFunc) {
        inlineBlocks = new HashMap<>();
        inlineValues = new HashMap<>();
        Function calleeFunc = call.function;
        int instCount = calleeFunc.getInstCount();
        if (calleeFunc.blocks.size() > inlineInstLimit|| instCount > inlineBlockLimit)
            return;
        inlineCount++;
        preName = "inlineFunc." + calleeFunc.name + "." + inlineCount + ".";
        setNewBlock(calleeFunc);
        Block beginBlockNow = inlineBlocks.get(calleeFunc.beginBlock);
        Block endBlockNow = new Block(-1);

        // change blocks and operands
        for (Block oldBlock : calleeFunc.blocks) {
            Block newBlock = inlineBlocks.get(oldBlock);
            for (Block b : oldBlock.prevBlocks) {
                newBlock.prevBlocks.add(inlineBlocks.get(b));
            }
            for (Block b : oldBlock.nextBlocks) {
                newBlock.nextBlocks.add(inlineBlocks.get(b));
            }
            for (instruction inst : oldBlock.inst) {
                if (inst instanceof Assign) {
                    newBlock.inst.add(new Assign(newBlock, getValueNew(inst.register), getValueNew(((Assign) inst).value)));
                } else if (inst instanceof Binary) {
                    newBlock.inst.add(new Binary(newBlock, (register) getValueNew(inst.register), getValueNew(((Binary) inst).lhs), getValueNew(((Binary) inst).rhs), ((Binary) inst).op));
                } else if (inst instanceof BitCast) {
                    newBlock.inst.add(new BitCast(newBlock, getValueNew(inst.register), getValueNew(((BitCast) inst).value)));
                } else if (inst instanceof Branch) {
                    newBlock.inst.add(new Branch(newBlock, getValueNew(((Branch) inst).condition), inlineBlocks.get(((Branch) inst).trueBlock), inlineBlocks.get(((Branch) inst).falseBlock)));
                } else if (inst instanceof Call) {
                    Call newCall = new Call(newBlock, (register) getValueNew(inst.register), ((Call) inst).function);
                    for (operand o : ((Call) inst).params) {
                        newCall.params.add(getValueNew(o));
                    }
                    newBlock.inst.add(newCall);

//                    System.out.println(((Call) inst).function.name);

                } else if (inst instanceof GetElementPtr) {
                    newBlock.inst.add(new GetElementPtr(newBlock, getValueNew(inst.register), getValueNew(((GetElementPtr) inst).base), getValueNew(((GetElementPtr) inst).index), ((GetElementPtr) inst).offset));
                } else if (inst instanceof Icmp) {
                    newBlock.inst.add(new Icmp(newBlock, (register) getValueNew(inst.register), getValueNew(((Icmp) inst).lhs), getValueNew(((Icmp) inst).rhs), ((Icmp) inst).op));
                } else if (inst instanceof Jump) {
                    newBlock.inst.add(new Jump(newBlock, inlineBlocks.get(((Jump) inst).destination)));
                } else if (inst instanceof Load) {
                    newBlock.inst.add(new Load(newBlock, (register) getValueNew(inst.register), getValueNew(((Load) inst).address)));
                } else if (inst instanceof Phi) {
                    Phi newPhi = new Phi(newBlock, (register) getValueNew(inst.register));
                    newPhi.isMain = ((Phi) inst).isMain;
                    for (int i = 0; i < ((Phi) inst).blocks.size(); i++) {
                        newPhi.add(inlineBlocks.get(((Phi) inst).blocks.get(i)), getValueNew(((Phi) inst).values.get(i)));
                    }
                    newBlock.inst.add(newPhi);


//                    for (int i=0;i<((Phi) inst).blocks.size();i++){
//                        System.out.println(((Phi) inst).blocks.get(i).intoString());
//                        System.out.println(((Phi) inst).values.get(i).intoString());
//                    }
                } else if (inst instanceof Return) {
                    newBlock.inst.add(new Return(newBlock, getValueNew(((Return) inst).value)));
                    endBlockNow = newBlock;
                } else if (inst instanceof Store) {
                    newBlock.inst.add(new Store(newBlock, getValueNew(((Store) inst).address), getValueNew(((Store) inst).value)));
                }
            }
        }
        // get call inst pos
        Block callerBlock = call.block;
        int callInstPos = 0;
        while (callerBlock.inst.get(callInstPos) != call) {
            callInstPos++;
        }
//        for (int i=0;i<callerBlock.inst.size();i++){
//            if(callerBlock.inst.get(i)== call){
//                callInstPos=i;
//                break;
//            }
//        }
        // set pre block
        Block preCallBlock = new Block(callerBlock.loopDepth);
        preCallBlock.name = callerBlock.name + ".preCall";
        preCallBlock.inst = new ArrayList<>(callerBlock.inst.subList(0, callInstPos));
        for (int i = 0; i < call.params.size(); i++) {
            preCallBlock.addInst(new Assign(preCallBlock, getValueNew(calleeFunc.params.get(i)), call.params.get(i)));
        }
        preCallBlock.is_end = true;
        preCallBlock.inst.addAll(beginBlockNow.inst);
        preCallBlock.prevBlocks = callerBlock.prevBlocks;
        preCallBlock.nextBlocks = beginBlockNow.nextBlocks;
        for (instruction inst : preCallBlock.inst) {
            inst.block = preCallBlock;
        }
        for (Block block : preCallBlock.prevBlocks) {
            for (int i = 0; i < block.nextBlocks.size(); i++) {
                if (block.nextBlocks.get(i) == callerBlock) {
                    block.nextBlocks.set(i, preCallBlock);
                }
            }
            block.replaceNextBlock(callerBlock, preCallBlock);
        }
        for (Block block : preCallBlock.nextBlocks) {
            for (int i = 0; i < block.prevBlocks.size(); i++) {
                if (block.prevBlocks.get(i) == beginBlockNow)
                    block.prevBlocks.set(i, preCallBlock);
            }
            block.replacePrevBlock(beginBlockNow, preCallBlock);
        }
        if (callerFunc.beginBlock == callerBlock) {
            callerFunc.beginBlock = preCallBlock;
        }
        if (endBlockNow == beginBlockNow) { // has only one block
            endBlockNow = preCallBlock;
        }
        // set after block
        Block afterCallBlock = new Block(callerBlock.loopDepth);

        afterCallBlock.name = callerBlock.name + ".afterCall";
        Return newRet = (Return) endBlockNow.getEndInst();
        endBlockNow.popEndInst();
        afterCallBlock.inst = new ArrayList<>(endBlockNow.inst);
        if (!(newRet.value instanceof constVoid)) { // has return
            afterCallBlock.inst.add(new Assign(afterCallBlock, call.register, newRet.value));
        }
        afterCallBlock.is_end = true;
        afterCallBlock.inst.addAll(callerBlock.inst.subList(callInstPos + 1, callerBlock.inst.size()));
        afterCallBlock.prevBlocks = endBlockNow.prevBlocks;
        afterCallBlock.nextBlocks = callerBlock.nextBlocks;
        for (instruction inst : afterCallBlock.inst) {
            inst.block = afterCallBlock;
        }
        Block newEndBlock = endBlockNow;
        for (Block block : afterCallBlock.prevBlocks) {
            for (int i = 0; i < block.nextBlocks.size(); i++) {
                if (block.nextBlocks.get(i) == newEndBlock) {
                    block.nextBlocks.set(i, afterCallBlock);
                }
            }
            block.replaceNextBlock(newEndBlock, afterCallBlock);
        }
        for (Block block : afterCallBlock.nextBlocks) {
            for (int i = 0; i < block.prevBlocks.size(); i++) {
                if (block.prevBlocks.get(i) == callerBlock) {
                    block.prevBlocks.set(i, afterCallBlock);
                }
            }
            block.replacePrevBlock(callerBlock, afterCallBlock);
        }
        if (callerFunc.beginBlock == endBlockNow)
            callerFunc.beginBlock = afterCallBlock;
        inlineCollectFuncNow = callerFunc;
        inlineCollectFuncNow.blocks = new ArrayList<>();
        dfsBlockInLine(callerFunc.beginBlock);
    }

    public void setInLineFunc(Function func) {
        if (!alreadyInLine.contains(func)) {
            alreadyInLine.add(func);
            for (Function sonFunc : funcLink.get(func)) {
                setInLineFunc(sonFunc);
            }
            for (int i = 0; i < callLink.get(func).size(); i++) {
                moveInLine(callLink.get(func).get(i), reFuncLink.get(func).get(i));
            }
        }
    }

    public void setLink() {
        for (Map.Entry<String, Function> entry : iR.funcs.entrySet()) {
            Function func = entry.getValue();
            if (funcLink.get(func).contains(func)) {
                for (int i = 0; i < callLink.get(func).size(); i++) {
                    moveInLine(callLink.get(func).get(i), reFuncLink.get(func).get(i));
                }
            }
        }
    }

    public void inline() {
        inlineInit();
        getCall();
        setCanInLine();
        for (Function func : canInLine) {
            setInLineFunc(func);
        }
        setLink();
    }

    public void setPhi(Function function) {
        int blockIDNow = 0;

//        int dbg_cnt = 0;

//        for (Block block : function.blocks) {
        for (int block_num = 0; block_num < function.blocks.size(); block_num++) { // blocks changed in ArrayList in midway, can use : to ergodic
            Block block = function.blocks.get(block_num);
            boolean containPhi = false;
            for (instruction i : block.inst) {
                if (i instanceof Phi) {
                    containPhi = true;
                    break;
                }
            }
            if (containPhi) {
                for (int i = 0; i < block.prevBlocks.size(); i++) {
                    Block preBlock = block.prevBlocks.get(i);
                    if (preBlock.nextBlocks.size() > 1) {
                        Block insert = new Block(0);
                        insert.name = "block.phi." + blockIDNow;
                        function.blocks.add(insert);
                        insert.addInst(new Jump(insert, block));
                        insert.nextBlocks.add(block);
                        insert.is_end = true;
                        block.prevBlocks.set(i, insert);
                        block.replacePrevBlock(preBlock, insert);
                        for (int j = 0; j < preBlock.nextBlocks.size(); j++) {
                            if (preBlock.nextBlocks.get(j) == block)
                                preBlock.nextBlocks.set(j, insert);
                        }
                        preBlock.replaceNextBlock(block, insert);
                        blockIDNow++;
                    }
                }
                for (int i = 0; i < block.inst.size(); i++) {
                    instruction inst = block.inst.get(i);
                    if (inst instanceof Phi) {
                        for (int j = 0; j < ((Phi) inst).blocks.size(); j++)
                            ((Phi) inst).blocks.get(j).regValues.put(inst.register, ((Phi) inst).values.get(j)); // store ans values into each block of phi
                        block.inst.remove(i);
                        i--;
                    }
                }
            }

//            dbg_cnt++;
        }
        for (Block block : function.blocks) {
            while (!block.regValues.isEmpty()) {
                boolean cut = true;
                while (cut) {
                    cut = false;
                    Iterator<Map.Entry<register, operand>> iter = block.regValues.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<register, operand> nxt = iter.next();
                        if (!(nxt.getValue() instanceof register) || !block.regValues.containsKey(nxt.getValue())) {
                            block.addBackInst(new Assign(block, nxt.getKey(), nxt.getValue()));
                            iter.remove();
                            cut = true;
                        }
                    }
                }
                Iterator<Map.Entry<register, operand>> iter = block.regValues.entrySet().iterator();
                if (iter.hasNext()) {
                    Map.Entry<register, operand> nxt = iter.next();
                    register insert = new register(nxt.getKey().irType, "tmp");
                    block.addBackInst(new Assign(block, insert, nxt.getValue()));
                    block.regValues.forEach((key, value) -> {
                        if (value == nxt.getValue())
                            block.regValues.replace(key, insert);
                    });
                }
            }
        }
    }

    public void run() {
        iR.funcs.forEach((name, function) -> {
            currentFunction = function;
            iR.destBlock.addEndInst(new Return(iR.destBlock, new constVoid()));
            preHandle(function.beginBlock);
            getVariables();
            variablesDiffer();

//            System.out.println("phi");

            getPhi();
            variableSet();
            phiSimplify();
        });
        inline();
        iR.funcs.forEach((name, function) -> {
            setPhi(function);
        });
    }
}
