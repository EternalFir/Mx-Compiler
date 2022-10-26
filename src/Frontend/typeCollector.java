package Frontend;

import AST.Nodes.*;
import AST.Scope;
import AST.Symbols.*;
import AST.Types.*;

import Utility.position;

public class typeCollector implements ASTVisitor {
    Scope globalScope;
    String classNow;

    public typeCollector(Scope scope_in) {
        globalScope = scope_in;
    }

    @Override
    public void visit(typeNode node) {

    }

    @Override
    public void visit(programNode node) {
        classNow = null;
        if (node.proBody != null) {
            for (ASTNode i : node.proBody) {
                i.accept(this);
            }
        }
    }

    @Override
    public void visit(funcDefNode node) {
        if (classNow == null) {
            globalScope.funcMap.get(node.name).returnType = globalScope.getType(node.type);
            if (node.paramList != null) {
                for (varDefSubNode i : node.paramList) {
                    globalScope.funcMap.get(node.name).paramList.add(new varSymbol(i.name, globalScope.getType(i.type)));
                }
            }
        } else {
            ((classType) globalScope.typeMap.get(classNow)).funcMap.get(node.name).returnType = globalScope.getType(node.type);
            if (node.paramList != null) {
                for (varDefSubNode i : node.paramList) {
                    ((classType) globalScope.typeMap.get(classNow)).funcMap.get(node.name).paramList.add(new varSymbol(i.name, globalScope.getType(i.type)));
                }
            }
        }
    }


    @Override
    public void visit(varDefSubNode node) {
        if (classNow == null) {
            globalScope.varMap.get(node.name).type = globalScope.getType(node.type);
        } else {
            ((classType) globalScope.typeMap.get(classNow)).varMap.get(node.name).type = globalScope.getType(node.type);
        }
    }

    @Override
    public void visit(varDefNode node) {

    }

    @Override
    public void visit(classDefNode node) {
        classNow = node.name;
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
        if(node.constructor!=null)
            ((classType)globalScope.typeMap.get(classNow)).constructor.returnType=null;
        classNow=null;
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

//    @Override
//    public void visit(mainNode node) {
//
//    }
}
