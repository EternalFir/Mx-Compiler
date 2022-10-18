package AST.Nodes;

public interface ASTVisitor {
    void visit(funcDefNode node);

    void visit(funcDefMainNode node);

    void visit(varDefSubNode node);

    void visit(varDefNode node);
}
