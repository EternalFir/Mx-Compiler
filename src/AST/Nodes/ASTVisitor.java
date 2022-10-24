package AST.Nodes;

public interface ASTVisitor {

    void visit(typeNode node);

    void visit(programNode node);

    void visit(funcDefNode node);

    void visit(varDefSubNode node);

    void visit(varDefNode node);

    void visit(classDefNode node);

    void visit(lambdaDefNode node);

    void visit(codeBlockNode node);

    void visit(ifSentNode node);

    void visit(forSentNode node);

    void visit(whileSentNode node);

    void visit(returnSentNode node);

    void visit(breakSentNode node);

    void visit(continueSentNode node);

    void visit(expressionOnlySentNode node);

    void visit(emptySentNode node);

    void visit(preNumberExpNode node);

    void visit(binaryExpNode node);

    void visit(memberExpNode node);

    void visit(newExpNode node);

    void visit(backselfExpNode node);

    void visit(frontselfExpNode node);

    void visit(funcExpNode node);

    void visit(preLogicExpNode node);

    void visit(arrayExpNode node);

    void visit(expressionGroupNode node);

    void visit(boolLiteralNode node);

    void visit(intLiteralNode node);

    void visit(stringLiteralNode node);

    void visit(nullLiteralNode node);

    void visit(thisExpNode node);

    void visit(varExpNode node);

    void visit(mainNode node);
}
