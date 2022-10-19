package Frontend;

import AST.Nodes.*;
import AST.Nodes.ASTNode;
import antlr.MxParserVisitor;
import antlr.MxParser;
import antlr.MxParserBaseVisitor;
import Utility.position;
import Utility.errors.syntaxError;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;


public class ASTBuilder implements MxParserVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        programNode node = new programNode(new position(ctx));
        if (ctx.programSub() != null) {
            for (ParserRuleContext i : ctx.programSub()) {
                ASTNode t = visit(i);
                if (t instanceof varDefNode) {
                    node.proBody.addAll(((varDefNode) t).varDefList);
                } else {
                    node.proBody.add(t);
                }
            }
        }
        return node;
    }

    @Override
    public ASTNode visitProgramSub(MxParser.ProgramSubContext ctx) {
        if (ctx.funcDef() != null)
            return visit(ctx.funcDef());
        else if (ctx.varDef() != null)
            return visit(ctx.varDef());
        else if (ctx.classDef() != null)
            return visit(ctx.classDef());
        else
            return null;
    }

    @Override
    public ASTNode visitProgramMain(MxParser.ProgramMainContext ctx) {
        if(ctx.funcDefMain()!=null)
            return visit(ctx.funcDefMain());
        else
            return null;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        varDefNode node=new varDefNode(new position(ctx));
        typeNode type=(typeNode) visit(ctx.type());
        if(!ctx.varDefSub().isEmpty()){
            for(ParserRuleContext i : ctx.varDefSub()){
                varDefSubNode t=(varDefSubNode) visit(i);
                t.type=type;
                node.varDefList.add(t);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitVarDefSub(MxParser.VarDefSubContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDefMain(MxParser.FuncDefMainContext ctx) {
        var a = new funcDefMainNode;
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitLambdaDef(MxParser.LambdaDefContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBlockSent(MxParser.BlockSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitVarDefSent(MxParser.VarDefSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitIfSent(MxParser.IfSentContext ctx) {
        var thisNode = new ifSentNode;
        thisNode.cond = ctx.cond.accept(this);
        return thisNode;
    }

    @Override
    public ASTNode visitForSent(MxParser.ForSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitWhileSent(MxParser.WhileSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitReturnSent(MxParser.ReturnSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBreakSent(MxParser.BreakSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitContinueSent(MxParser.ContinueSentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitExprOnlySent(MxParser.ExprOnlySentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitEmptySent(MxParser.EmptySentContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitCodeBlock(MxParser.CodeBlockContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitPreNumberExp(MxParser.PreNumberExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBinaryExp(MxParser.BinaryExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitMemberExp(MxParser.MemberExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitNewExp(MxParser.NewExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBackselfExp(MxParser.BackselfExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitLambdaExp(MxParser.LambdaExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFrontselfExp(MxParser.FrontselfExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncExp(MxParser.FuncExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitPreLogicNoExp(MxParser.PreLogicNoExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitSimpleExp(MxParser.SimpleExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitArrayExp(MxParser.ArrayExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitExpressionGroup(MxParser.ExpressionGroupContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitArrayCreation(MxParser.ArrayCreationContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitClassCreation(MxParser.ClassCreationContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitSimpleCreation(MxParser.SimpleCreationContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitParamGroup(MxParser.ParamGroupContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitParam(MxParser.ParamContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBaseType(MxParser.BaseTypeContext ctx) {
        var text = ctx.getText();
        switch (text) {
            case "int":
                return new IntTypeNode;
            case "bool":
                return new BoolTypeNode;
        }
        return null;
    }

    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBaseExp(MxParser.BaseExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitIdentifier(MxParser.IdentifierContext ctx) {
        return null;
    }

    @Override
    public ASTNode visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public ASTNode visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public ASTNode visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public ASTNode visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
