package Frontend;

import AST.*;
import AST.Nodes.ASTNode;
import antlr.MxParserVisitor;
import antlr.MxParser;
import Utility.position;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;



public class ASTBuilder implements MxParserVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitProgramSub(MxParser.ProgramSubContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitProgramMain(MxParser.ProgramMainContext ctx) {
        return ctx.funcDefMain().accept(this);
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitFuncDefMain(MxParser.FuncDefMainContext ctx) {
         var a=new funcDefMainNode;

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
        var thisNode=new IfSent;
        thisNode.cond=ctx.cond.accept(this);
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
    public ASTNode visitBitwiseXorExp(MxParser.BitwiseXorExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitLogicNoExp(MxParser.LogicNoExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitMemberExp(MxParser.MemberExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBitwiseMoveExp(MxParser.BitwiseMoveExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitNewExp(MxParser.NewExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitEnvalueExp(MxParser.EnvalueExpContext ctx) {
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
    public ASTNode visitBitwiseAndExp(MxParser.BitwiseAndExpContext ctx) {
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
    public ASTNode visitLogicAndExp(MxParser.LogicAndExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitAddSubExp(MxParser.AddSubExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitBitwiseNoExp(MxParser.BitwiseNoExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitPrenumberExp(MxParser.PrenumberExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitCompareExp(MxParser.CompareExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitLogicOrExp(MxParser.LogicOrExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitMulDivModExp(MxParser.MulDivModExpContext ctx) {
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
    public ASTNode visitBitwiseOrExp(MxParser.BitwiseOrExpContext ctx) {
        return null;
    }

    @Override
    public ASTNode visitIsEquareExp(MxParser.IsEquareExpContext ctx) {
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
            case "int": return new IntTypeNode();
            case "bool": return new BoolTypeNode();
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
