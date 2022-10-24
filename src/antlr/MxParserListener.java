// Generated from D:/cys/SJTU/ÉÏ¿Î/±àÒëÆ÷/Compiler/Mx-Compiler/src/antlr\MxParser.g4 by ANTLR 4.10.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#programSub}.
	 * @param ctx the parse tree
	 */
	void enterProgramSub(MxParser.ProgramSubContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#programSub}.
	 * @param ctx the parse tree
	 */
	void exitProgramSub(MxParser.ProgramSubContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefSub}.
	 * @param ctx the parse tree
	 */
	void enterVarDefSub(MxParser.VarDefSubContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefSub}.
	 * @param ctx the parse tree
	 */
	void exitVarDefSub(MxParser.VarDefSubContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambdaDef}.
	 * @param ctx the parse tree
	 */
	void enterLambdaDef(MxParser.LambdaDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambdaDef}.
	 * @param ctx the parse tree
	 */
	void exitLambdaDef(MxParser.LambdaDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterBlockSent(MxParser.BlockSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitBlockSent(MxParser.BlockSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterVarDefSent(MxParser.VarDefSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitVarDefSent(MxParser.VarDefSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterIfSent(MxParser.IfSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitIfSent(MxParser.IfSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterForSent(MxParser.ForSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitForSent(MxParser.ForSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterWhileSent(MxParser.WhileSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitWhileSent(MxParser.WhileSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterReturnSent(MxParser.ReturnSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitReturnSent(MxParser.ReturnSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterBreakSent(MxParser.BreakSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitBreakSent(MxParser.BreakSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterContinueSent(MxParser.ContinueSentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueSent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitContinueSent(MxParser.ContinueSentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprOnlySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterExprOnlySent(MxParser.ExprOnlySentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprOnlySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitExprOnlySent(MxParser.ExprOnlySentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterEmptySent(MxParser.EmptySentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptySent}
	 * labeled alternative in {@link MxParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitEmptySent(MxParser.EmptySentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(MxParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preNumberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreNumberExp(MxParser.PreNumberExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preNumberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreNumberExp(MxParser.PreNumberExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(MxParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(MxParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExp(MxParser.MemberExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExp(MxParser.MemberExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExp(MxParser.NewExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExp(MxParser.NewExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code backselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBackselfExp(MxParser.BackselfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code backselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBackselfExp(MxParser.BackselfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExp(MxParser.LambdaExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code frontselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFrontselfExp(MxParser.FrontselfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code frontselfExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFrontselfExp(MxParser.FrontselfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExp(MxParser.FuncExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExp(MxParser.FuncExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preLogicNoExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreLogicNoExp(MxParser.PreLogicNoExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preLogicNoExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreLogicNoExp(MxParser.PreLogicNoExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExp(MxParser.SimpleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExp(MxParser.SimpleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExp(MxParser.ArrayExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExp(MxParser.ArrayExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionGroup}.
	 * @param ctx the parse tree
	 */
	void enterExpressionGroup(MxParser.ExpressionGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionGroup}.
	 * @param ctx the parse tree
	 */
	void exitExpressionGroup(MxParser.ExpressionGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreation(MxParser.ArrayCreationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreation(MxParser.ArrayCreationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void enterClassCreation(MxParser.ClassCreationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void exitClassCreation(MxParser.ClassCreationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCreation(MxParser.SimpleCreationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleCreation}
	 * labeled alternative in {@link MxParser#creation}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCreation(MxParser.SimpleCreationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paramGroup}.
	 * @param ctx the parse tree
	 */
	void enterParamGroup(MxParser.ParamGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paramGroup}.
	 * @param ctx the parse tree
	 */
	void exitParamGroup(MxParser.ParamGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(MxParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(MxParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(MxParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(MxParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#baseExp}.
	 * @param ctx the parse tree
	 */
	void enterBaseExp(MxParser.BaseExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#baseExp}.
	 * @param ctx the parse tree
	 */
	void exitBaseExp(MxParser.BaseExpContext ctx);
}