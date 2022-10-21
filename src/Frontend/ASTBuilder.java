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
import java.util.Objects;


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
        if (ctx.funcDefMain() != null)
            return visit(ctx.funcDefMain());
        else
            return null;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        varDefNode node = new varDefNode(new position(ctx));
        typeNode type = (typeNode) visit(ctx.type());
        if (!ctx.varDefSub().isEmpty()) {
            for (ParserRuleContext i : ctx.varDefSub()) {
                varDefSubNode t = (varDefSubNode) visit(i);
                t.type = type;
                node.varDefList.add(t);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitVarDefSub(MxParser.VarDefSubContext ctx) {
        varDefSubNode node = new varDefSubNode(new position(ctx));
        node.name = ctx.getText();
        if (ctx.expression() != null) {
            node.expression = (ExpressionNode) visit(ctx.expression());
        }
        ;
        return node;
    }

    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        funcDefNode node = new funcDefNode(new position(ctx), ctx.Identifier().getText(), ctx.returnType() != null ? (typeNode) visit(ctx.returnType()) : null,
                (codeBlockNode) visit(ctx.codeBlock()), ctx.paramGroup() != null ? ((varDefNode) visit(ctx.paramGroup())).varDefList : new ArrayList<>());
        return node;
    }

    @Override
    public ASTNode visitFuncDefMain(MxParser.FuncDefMainContext ctx) {
        funcDefMainNode node = new funcDefMainNode(new position(ctx), "main", ctx.codeBlock() != null ? (codeBlockNode) visit(ctx.codeBlock()) : null);
        return node;
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        classDefNode node = new classDefNode(new position(ctx), ctx.Identifier().getText());
        if (ctx.varDef() != null) {
            for (ParserRuleContext i : ctx.varDef()) {
                varDefNode t = (varDefNode) visit(i);
                node.varList.addAll(t.varDefList);
            }
        }
        if (ctx.funcDef() != null) {
            for (ParserRuleContext i : ctx.funcDef()) {
                funcDefNode t = (funcDefNode) visit(i);
                if (t.type == null)
                    node.constructor = t;
                else
                    node.funcList.add(t);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitLambdaDef(MxParser.LambdaDefContext ctx) {
        lambdaDefNode node = new lambdaDefNode(new position(ctx), Objects.equals(ctx.ifAnd.getText(), "&"),
                ctx.codeBlock() != null ? (codeBlockNode) visit(ctx.codeBlock()) : null, ctx.paramGroup() != null ? ((varDefNode) visit(ctx.paramGroup())).varDefList : new ArrayList<>(),
                ctx.expressionGroup() != null ? (expressionGroupNode) visit(ctx.expressionGroup()) : new expressionGroupNode(new position(ctx)));
        return node;
    }

    @Override
    public ASTNode visitBlockSent(MxParser.BlockSentContext ctx) {
        return visit(ctx.codeBlock());
    }

    @Override
    public ASTNode visitVarDefSent(MxParser.VarDefSentContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTNode visitIfSent(MxParser.IfSentContext ctx) {
        ifSentNode node = new ifSentNode(new position(ctx));
        node.cond = (ExpressionNode) visit(ctx.cond);
        node.trueSent = (SentenceNode) visit(ctx.trueSent);
        if (ctx.falseSent != null)
            node.falseSent = (SentenceNode) visit(ctx.falseSent);
        else
            node.falseSent = null;
        return node;
    }

    @Override
    public ASTNode visitForSent(MxParser.ForSentContext ctx) {
        forSentNode node = new forSentNode(new position(ctx));
        if (ctx.init != null)
            node.init = (ExpressionNode) visit(ctx.init);
        else
            node.init = null;
        if (ctx.cond != null)
            node.cond = (ExpressionNode) visit(ctx.cond);
        else
            node.cond = null;
        if (ctx.move != null)
            node.move = (ExpressionNode) visit(ctx.move);
        else
            node.move = null;
        node.repeSent = (SentenceNode) visit(ctx.repeSent);
        return node;
    }

    @Override
    public ASTNode visitWhileSent(MxParser.WhileSentContext ctx) {
        whileSentNode node = new whileSentNode(new position(ctx));
        if (ctx.cond != null)
            node.cond = (ExpressionNode) visit(ctx.cond);
        else
            node.cond = null;
        node.repeSent = (SentenceNode) visit(ctx.repeSent);
        return node;
    }

    @Override
    public ASTNode visitReturnSent(MxParser.ReturnSentContext ctx) {
        returnSentNode node = new returnSentNode(new position(ctx));
        if (ctx.expression() != null)
            node.value = (ExpressionNode) visit(ctx.expression());
        else
            node.value = null;
        return node;
    }

    @Override
    public ASTNode visitBreakSent(MxParser.BreakSentContext ctx) {
        return new breakSentNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueSent(MxParser.ContinueSentContext ctx) {
        return new continueSentNode(new position(ctx));
    }

    @Override
    public ASTNode visitExprOnlySent(MxParser.ExprOnlySentContext ctx) {
        return new expressionOnlySentNode(new position(ctx), (ExpressionNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitEmptySent(MxParser.EmptySentContext ctx) {
        return new emptySentNode(new position(ctx));
    }

    @Override
    public ASTNode visitCodeBlock(MxParser.CodeBlockContext ctx) {
        codeBlockNode node = new codeBlockNode(new position(ctx));
        if (ctx.sentence() != null) {
            for (ParserRuleContext i : ctx.sentence()) {
                SentenceNode t = (SentenceNode) visit(i);
                node.sentencesList.add(t);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitPreNumberExp(MxParser.PreNumberExpContext ctx) {
        return new preNumberExpNode(new position(ctx), ctx.op.getText(), (ExpressionNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitBinaryExp(MxParser.BinaryExpContext ctx) {
        return new binaryExpNode(new position(ctx), ctx.op.getText(), (ExpressionNode) visit(ctx.lexp), (ExpressionNode) visit(ctx.rexp));
    }

    @Override
    public ASTNode visitMemberExp(MxParser.MemberExpContext ctx) {
        return new memberExpNode(new position(ctx), (ExpressionNode) visit(ctx.expression()), ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitNewExp(MxParser.NewExpContext ctx) {
        return visit(ctx.creation());
    }

    @Override
    public ASTNode visitBackselfExp(MxParser.BackselfExpContext ctx) {
        return new backselfExpNode(new position(ctx), (ExpressionNode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTNode visitLambdaExp(MxParser.LambdaExpContext ctx) {
        return visit(ctx.lambdaDef());
    }

    @Override
    public ASTNode visitFrontselfExp(MxParser.FrontselfExpContext ctx) {
        return new frontselfExpNode(new position(ctx), (ExpressionNode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTNode visitFuncExp(MxParser.FuncExpContext ctx) {
        funcExpNode node = new funcExpNode(new position(ctx));
        node.master = (ExpressionNode) visit(ctx.expression());
        if (ctx.expressionGroup() != null)
            node.expList = ((expressionGroupNode) visit(ctx.expressionGroup())).expList;
        else
            node.expList = new expressionGroupNode(new position(ctx)).expList;
        return node;
    }

    @Override
    public ASTNode visitPreLogicNoExp(MxParser.PreLogicNoExpContext ctx) {
        return new preLogicExpNode(new position(ctx), (ExpressionNode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTNode visitSimpleExp(MxParser.SimpleExpContext ctx) {
        return visit(ctx.baseExp());
    }

    @Override
    public ASTNode visitArrayExp(MxParser.ArrayExpContext ctx) {
        return new arrayExpNode(new position(ctx), (ExpressionNode) visit(ctx.master), (ExpressionNode) visit(ctx.index));
    }

    @Override
    public ASTNode visitExpressionGroup(MxParser.ExpressionGroupContext ctx) {
        expressionGroupNode node = new expressionGroupNode(new position(ctx));
        if (ctx.expression() != null) {
            for (ParserRuleContext i : ctx.expression()) {
                node.expList.add((ExpressionNode) visit(i));
            }
        }
        return node;
    }

    @Override
    public ASTNode visitArrayCreation(MxParser.ArrayCreationContext ctx) {
        ArrayList<ExpressionNode> expList = new ArrayList<>();
        for (ParserRuleContext i : ctx.expression()) {
            expList.add((ExpressionNode) visit(i));
        }
        return new newExpNode(new position(ctx), (typeNode) visit(ctx.baseType()), (ctx.getChildCount() - ctx.expression().size() - 1) / 2, expList);
    }

    @Override
    public ASTNode visitClassCreation(MxParser.ClassCreationContext ctx) {
        return new newExpNode(new position(ctx), (typeNode) visit(ctx.baseType()), 0, null);
    }

    @Override
    public ASTNode visitSimpleCreation(MxParser.SimpleCreationContext ctx) {
        return new newExpNode(new position(ctx), (typeNode) visit(ctx.baseType()), 0, null);
    }

    @Override
    public ASTNode visitParamGroup(MxParser.ParamGroupContext ctx) {
        varDefNode node = new varDefNode(new position(ctx));
        if (ctx.param() != null) {
            for (ParserRuleContext i : ctx.param()) {
                node.varDefList.add((varDefSubNode) visit(i));
            }
        }
        return node;
    }

    @Override
    public ASTNode visitParam(MxParser.ParamContext ctx) {
        varDefSubNode node = new varDefSubNode(new position(ctx), ctx.Identifier().getText(), null);
        node.type = (typeNode) visit(ctx.type());
        return node;
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        return new typeNode(new position(ctx), ctx.baseType().getText(), (ctx.getChildCount() - 1) / 2);
    }

    @Override
    public ASTNode visitBaseType(MxParser.BaseTypeContext ctx) {
//        var text = ctx.getText();
//        switch (text) {
//            case "int":
//                return new IntTypeNode;
//            case "bool":
//                return new BoolTypeNode;
//        }
        return new typeNode(new position(ctx), ctx.getText(), 0);
    }

    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        if (ctx.type() != null) {
            return visit(ctx.type());
        } else { //省略类型(void)
            return new typeNode(new position(ctx), ctx.VOID().getText(), 0);
        }
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.BoolTypeLiteral() != null)
            return new boolLiteralNode(new position(ctx), Boolean.parseBoolean(ctx.BoolTypeLiteral().getText()));
        else if (ctx.IntTypeLiteral() != null)
            return new intLiteralNode(new position(ctx), Integer.parseInt(ctx.IntTypeLiteral().getText()));
        else if (ctx.NullTypeLiteral() != null)
            return new nullLiteralNode(new position(ctx));
        else if (ctx.StringTypeLiteral() != null)
            return new stringLiteralNode(new position(ctx), ctx.StringTypeLiteral().getText());
        else
            throw new syntaxError("literal type error", new position(ctx));
    }

    @Override
    public ASTNode visitBaseExp(MxParser.BaseExpContext ctx) {
        if (ctx.expression() != null)
            return visit(ctx.expression());
        else if (ctx.THIS() != null)
            return new thisExpNode(new position(ctx));
        else if (ctx.Identifier() != null)
            return new varExpNode(new position(ctx), ctx.Identifier().getText());
        else
            return visit(ctx.literal());
    }

    @Override
    public ASTNode visitIdentifier(MxParser.IdentifierContext ctx) {
        if (Objects.equals(ctx.Identifier().getText(), "main"))
            return new mainNode(new position(ctx));
        else return new varExpNode(new position(ctx), ctx.Identifier().getText());
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
