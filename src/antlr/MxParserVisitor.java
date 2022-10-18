// Generated from D:/cys/SJTU/ÉÏ¿Î/±àÒëÆ÷/Compiler/Mx-Compiler/src/antlr\MxParser.g4 by ANTLR 4.10.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#programSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramSub(MxParser.ProgramSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#programMain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramMain(MxParser.ProgramMainContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefSub(MxParser.VarDefSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDefMain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefMain(MxParser.FuncDefMainContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lambdaDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaDef(MxParser.LambdaDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockSent(MxParser.BlockSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDefSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefSent(MxParser.VarDefSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSent(MxParser.IfSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForSent(MxParser.ForSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileSent(MxParser.WhileSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSent(MxParser.ReturnSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakSent(MxParser.BreakSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueSent(MxParser.ContinueSentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOnlySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOnlySent(MxParser.ExprOnlySentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptySent(MxParser.EmptySentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#codeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseXorExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseXorExp(MxParser.BitwiseXorExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicNoExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicNoExp(MxParser.LogicNoExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExp(MxParser.MemberExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseMoveExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseMoveExp(MxParser.BitwiseMoveExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExp(MxParser.NewExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code envalueExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnvalueExp(MxParser.EnvalueExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code frontselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrontselfExp(MxParser.FrontselfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExp(MxParser.FuncExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseAndExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseAndExp(MxParser.BitwiseAndExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExp(MxParser.SimpleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExp(MxParser.ArrayExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicAndExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicAndExp(MxParser.LogicAndExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExp(MxParser.AddSubExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseNoExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseNoExp(MxParser.BitwiseNoExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prenumberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrenumberExp(MxParser.PrenumberExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExp(MxParser.CompareExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicOrExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOrExp(MxParser.LogicOrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExp(MxParser.MulDivModExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code backselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackselfExp(MxParser.BackselfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseOrExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseOrExp(MxParser.BitwiseOrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isEquareExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsEquareExp(MxParser.IsEquareExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionGroup(MxParser.ExpressionGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreation(MxParser.ArrayCreationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCreation(MxParser.ClassCreationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleCreation(MxParser.SimpleCreationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#paramGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamGroup(MxParser.ParamGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(MxParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(MxParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#baseExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(MxParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MxParser.IdentifierContext ctx);
}