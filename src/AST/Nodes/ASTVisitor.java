package AST.Nodes;

public interface ASTVisitor {

    void visit(typeNode node);

    void visit(programNode node);

    void visit(funcDefNode node);

    void visit(funcDefMainNode node);

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
}
