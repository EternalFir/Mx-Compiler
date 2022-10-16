// Generated from D:/cys/SJTU/�Ͽ�/������/Compiler/Mx-Compiler/src/antlr\MxParser.g4 by ANTLR 4.10.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BoolTypeLiteral=1, IntTypeLiteral=2, UnsignedIntTypeLiteral=3, NullTypeLiteral=4, 
		StringTypeLiteral=5, VOID=6, BOOL=7, INT=8, STRING=9, NEW=10, CLASS=11, 
		NULL=12, TRUE=13, FALSE=14, THIS=15, IF=16, ELSE=17, FOR=18, WHILE=19, 
		BREAK=20, CONTINUE=21, RETURN=22, Identifier=23, LEFT_PARENTHESIS=24, 
		RIGHT_PARENTHESIS=25, LEFT_SQUAREBRACKETS=26, RIGHT_SQUAREBRACKETS=27, 
		LEFT_BRACKETS=28, RIGHT_BRACKETS=29, COLON=30, SEMICOLON=31, COMMA=32, 
		DOT=33, ARROW=34, SELFADD=35, SELFSUB=36, MUL=37, DIV=38, MOD=39, ADD=40, 
		SUB=41, LOGIC_NO=42, LOGIC_AND=43, LOGIC_OR=44, BITWISE_NO=45, BITWISE_MOVE_LEFT=46, 
		BITWISE_MOVE_RIGHT=47, BITWISE_AND=48, BITWISE_OR=49, BITWISE_XOR=50, 
		GREATER_EQUARE=51, LESS_EQUARE=52, GREATER=53, LESS=54, EQUARE=55, UNEQUARE=56, 
		ENVALUE=57, WhiteSpace=58, NewLine=59, LineComment=60, BlockComment=61;
	public static final int
		RULE_program = 0, RULE_programMain = 1, RULE_varDef = 2, RULE_funcDef = 3, 
		RULE_classDef = 4, RULE_lambdaDef = 5, RULE_sentence = 6, RULE_codeBlock = 7, 
		RULE_expression = 8, RULE_expressionGroup = 9, RULE_creation = 10, RULE_paramGroup = 11, 
		RULE_param = 12, RULE_type = 13, RULE_baseType = 14, RULE_returnType = 15, 
		RULE_literal = 16, RULE_baseExp = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programMain", "varDef", "funcDef", "classDef", "lambdaDef", 
			"sentence", "codeBlock", "expression", "expressionGroup", "creation", 
			"paramGroup", "param", "type", "baseType", "returnType", "literal", "baseExp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'void'", "'bool'", "'int'", "'string'", 
			"'new'", "'class'", "'null'", "'true'", "'false'", "'this'", "'if'", 
			"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", null, 
			"'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "';'", "','", "'.'", 
			"'->'", "'++'", "'--'", "'*'", "'/'", "'%'", "'+'", "'-'", "'!'", "'&&'", 
			"'||'", "'~'", "'<<'", "'>>'", "'&'", "'|'", "'^'", "'>='", "'<='", "'>'", 
			"'<'", "'=='", "'!='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BoolTypeLiteral", "IntTypeLiteral", "UnsignedIntTypeLiteral", 
			"NullTypeLiteral", "StringTypeLiteral", "VOID", "BOOL", "INT", "STRING", 
			"NEW", "CLASS", "NULL", "TRUE", "FALSE", "THIS", "IF", "ELSE", "FOR", 
			"WHILE", "BREAK", "CONTINUE", "RETURN", "Identifier", "LEFT_PARENTHESIS", 
			"RIGHT_PARENTHESIS", "LEFT_SQUAREBRACKETS", "RIGHT_SQUAREBRACKETS", "LEFT_BRACKETS", 
			"RIGHT_BRACKETS", "COLON", "SEMICOLON", "COMMA", "DOT", "ARROW", "SELFADD", 
			"SELFSUB", "MUL", "DIV", "MOD", "ADD", "SUB", "LOGIC_NO", "LOGIC_AND", 
			"LOGIC_OR", "BITWISE_NO", "BITWISE_MOVE_LEFT", "BITWISE_MOVE_RIGHT", 
			"BITWISE_AND", "BITWISE_OR", "BITWISE_XOR", "GREATER_EQUARE", "LESS_EQUARE", 
			"GREATER", "LESS", "EQUARE", "UNEQUARE", "ENVALUE", "WhiteSpace", "NewLine", 
			"LineComment", "BlockComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MxParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<ProgramMainContext> programMain() {
			return getRuleContexts(ProgramMainContext.class);
		}
		public ProgramMainContext programMain(int i) {
			return getRuleContext(ProgramMainContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << CLASS) | (1L << Identifier))) != 0)) {
				{
				{
				setState(36);
				programMain();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramMainContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public ProgramMainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programMain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterProgramMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitProgramMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitProgramMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramMainContext programMain() throws RecognitionException {
		ProgramMainContext _localctx = new ProgramMainContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programMain);
		try {
			setState(47);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				varDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				funcDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				classDef();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxParser.COMMA, i);
		}
		public List<TerminalNode> ENVALUE() { return getTokens(MxParser.ENVALUE); }
		public TerminalNode ENVALUE(int i) {
			return getToken(MxParser.ENVALUE, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			type();
			{
			setState(50);
			match(Identifier);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ENVALUE) {
				{
				setState(51);
				match(ENVALUE);
				setState(52);
				expression(0);
				}
			}

			}
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(55);
				match(COMMA);
				setState(56);
				match(Identifier);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ENVALUE) {
					{
					setState(57);
					match(ENVALUE);
					setState(58);
					expression(0);
					}
				}

				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public ParamGroupContext paramGroup() {
			return getRuleContext(ParamGroupContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(68);
				returnType();
				}
				break;
			}
			setState(71);
			match(Identifier);
			setState(72);
			match(LEFT_PARENTHESIS);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) {
				{
				setState(73);
				paramGroup();
				}
			}

			setState(76);
			match(RIGHT_PARENTHESIS);
			setState(77);
			codeBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MxParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LEFT_BRACKETS() { return getToken(MxParser.LEFT_BRACKETS, 0); }
		public TerminalNode RIGHT_BRACKETS() { return getToken(MxParser.RIGHT_BRACKETS, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(CLASS);
			setState(80);
			match(Identifier);
			setState(81);
			match(LEFT_BRACKETS);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) {
				{
				setState(84);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(82);
					varDef();
					}
					break;
				case 2:
					{
					setState(83);
					funcDef();
					}
					break;
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(RIGHT_BRACKETS);
			setState(90);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaDefContext extends ParserRuleContext {
		public Token ifAnd;
		public ParamGroupContext parlist;
		public ExpressionGroupContext par;
		public TerminalNode LEFT_SQUAREBRACKETS() { return getToken(MxParser.LEFT_SQUAREBRACKETS, 0); }
		public TerminalNode RIGHT_SQUAREBRACKETS() { return getToken(MxParser.RIGHT_SQUAREBRACKETS, 0); }
		public TerminalNode ARROW() { return getToken(MxParser.ARROW, 0); }
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public List<TerminalNode> LEFT_PARENTHESIS() { return getTokens(MxParser.LEFT_PARENTHESIS); }
		public TerminalNode LEFT_PARENTHESIS(int i) {
			return getToken(MxParser.LEFT_PARENTHESIS, i);
		}
		public List<TerminalNode> RIGHT_PARENTHESIS() { return getTokens(MxParser.RIGHT_PARENTHESIS); }
		public TerminalNode RIGHT_PARENTHESIS(int i) {
			return getToken(MxParser.RIGHT_PARENTHESIS, i);
		}
		public TerminalNode BITWISE_AND() { return getToken(MxParser.BITWISE_AND, 0); }
		public ExpressionGroupContext expressionGroup() {
			return getRuleContext(ExpressionGroupContext.class,0);
		}
		public ParamGroupContext paramGroup() {
			return getRuleContext(ParamGroupContext.class,0);
		}
		public LambdaDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLambdaDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLambdaDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLambdaDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaDefContext lambdaDef() throws RecognitionException {
		LambdaDefContext _localctx = new LambdaDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_lambdaDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(LEFT_SQUAREBRACKETS);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BITWISE_AND) {
				{
				setState(93);
				((LambdaDefContext)_localctx).ifAnd = match(BITWISE_AND);
				}
			}

			setState(96);
			match(RIGHT_SQUAREBRACKETS);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(97);
				match(LEFT_PARENTHESIS);
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) {
					{
					setState(98);
					((LambdaDefContext)_localctx).parlist = paramGroup();
					}
				}

				setState(101);
				match(RIGHT_PARENTHESIS);
				}
			}

			setState(104);
			match(ARROW);
			setState(105);
			codeBlock();
			setState(106);
			match(LEFT_PARENTHESIS);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
				{
				setState(107);
				((LambdaDefContext)_localctx).par = expressionGroup();
				}
			}

			setState(110);
			match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentenceContext extends ParserRuleContext {
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
	 
		public SentenceContext() { }
		public void copyFrom(SentenceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDefSentContext extends SentenceContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarDefSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterVarDefSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitVarDefSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitVarDefSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForSentContext extends SentenceContext {
		public ExpressionContext init;
		public ExpressionContext cond;
		public ExpressionContext move;
		public SentenceContext repeSent;
		public TerminalNode FOR() { return getToken(MxParser.FOR, 0); }
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(MxParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MxParser.SEMICOLON, i);
		}
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterForSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitForSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitForSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprOnlySentContext extends SentenceContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ExprOnlySentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterExprOnlySent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitExprOnlySent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitExprOnlySent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileSentContext extends SentenceContext {
		public ExpressionContext cond;
		public SentenceContext repeSent;
		public TerminalNode WHILE() { return getToken(MxParser.WHILE, 0); }
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public WhileSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterWhileSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitWhileSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitWhileSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfSentContext extends SentenceContext {
		public ExpressionContext cond;
		public SentenceContext trueSent;
		public SentenceContext falseSent;
		public TerminalNode IF() { return getToken(MxParser.IF, 0); }
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxParser.ELSE, 0); }
		public IfSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterIfSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitIfSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitIfSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakSentContext extends SentenceContext {
		public TerminalNode BREAK() { return getToken(MxParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public BreakSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBreakSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBreakSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBreakSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptySentContext extends SentenceContext {
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public EmptySentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterEmptySent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitEmptySent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitEmptySent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockSentContext extends SentenceContext {
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public BlockSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBlockSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBlockSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBlockSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnSentContext extends SentenceContext {
		public TerminalNode RETURN() { return getToken(MxParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterReturnSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitReturnSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitReturnSent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueSentContext extends SentenceContext {
		public TerminalNode CONTINUE() { return getToken(MxParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(MxParser.SEMICOLON, 0); }
		public ContinueSentContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterContinueSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitContinueSent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitContinueSent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sentence);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new BlockSentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				codeBlock();
				}
				break;
			case 2:
				_localctx = new VarDefSentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				varDef();
				}
				break;
			case 3:
				_localctx = new IfSentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(IF);
				setState(115);
				match(LEFT_PARENTHESIS);
				setState(116);
				((IfSentContext)_localctx).cond = expression(0);
				setState(117);
				match(RIGHT_PARENTHESIS);
				setState(118);
				((IfSentContext)_localctx).trueSent = sentence();
				setState(121);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(119);
					match(ELSE);
					setState(120);
					((IfSentContext)_localctx).falseSent = sentence();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new ForSentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(123);
				match(FOR);
				setState(124);
				match(LEFT_PARENTHESIS);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
					{
					setState(125);
					((ForSentContext)_localctx).init = expression(0);
					}
				}

				setState(128);
				match(SEMICOLON);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
					{
					setState(129);
					((ForSentContext)_localctx).cond = expression(0);
					}
				}

				setState(132);
				match(SEMICOLON);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
					{
					setState(133);
					((ForSentContext)_localctx).move = expression(0);
					}
				}

				setState(136);
				match(RIGHT_PARENTHESIS);
				setState(137);
				((ForSentContext)_localctx).repeSent = sentence();
				}
				break;
			case 5:
				_localctx = new WhileSentContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				match(WHILE);
				setState(139);
				match(LEFT_PARENTHESIS);
				setState(140);
				((WhileSentContext)_localctx).cond = expression(0);
				setState(141);
				match(RIGHT_PARENTHESIS);
				setState(142);
				((WhileSentContext)_localctx).repeSent = sentence();
				}
				break;
			case 6:
				_localctx = new ReturnSentContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				match(RETURN);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
					{
					setState(145);
					expression(0);
					}
				}

				setState(148);
				match(SEMICOLON);
				}
				break;
			case 7:
				_localctx = new BreakSentContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(149);
				match(BREAK);
				setState(150);
				match(SEMICOLON);
				}
				break;
			case 8:
				_localctx = new ContinueSentContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(151);
				match(CONTINUE);
				setState(152);
				match(SEMICOLON);
				}
				break;
			case 9:
				_localctx = new ExprOnlySentContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(153);
				expression(0);
				setState(154);
				match(SEMICOLON);
				}
				break;
			case 10:
				_localctx = new EmptySentContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(156);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeBlockContext extends ParserRuleContext {
		public TerminalNode LEFT_BRACKETS() { return getToken(MxParser.LEFT_BRACKETS, 0); }
		public TerminalNode RIGHT_BRACKETS() { return getToken(MxParser.RIGHT_BRACKETS, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterCodeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitCodeBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitCodeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_codeBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(LEFT_BRACKETS);
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NEW) | (1L << THIS) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << LEFT_BRACKETS) | (1L << SEMICOLON) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
				{
				{
				setState(160);
				sentence();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(166);
			match(RIGHT_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BitwiseXorExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITWISE_XOR() { return getToken(MxParser.BITWISE_XOR, 0); }
		public BitwiseXorExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBitwiseXorExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBitwiseXorExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBitwiseXorExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicNoExpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LOGIC_NO() { return getToken(MxParser.LOGIC_NO, 0); }
		public LogicNoExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLogicNoExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLogicNoExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLogicNoExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MxParser.DOT, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public MemberExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterMemberExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitMemberExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitMemberExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseMoveExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITWISE_MOVE_LEFT() { return getToken(MxParser.BITWISE_MOVE_LEFT, 0); }
		public TerminalNode BITWISE_MOVE_RIGHT() { return getToken(MxParser.BITWISE_MOVE_RIGHT, 0); }
		public BitwiseMoveExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBitwiseMoveExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBitwiseMoveExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBitwiseMoveExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewExpContext extends ExpressionContext {
		public CreationContext creation() {
			return getRuleContext(CreationContext.class,0);
		}
		public NewExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterNewExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitNewExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitNewExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EnvalueExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENVALUE() { return getToken(MxParser.ENVALUE, 0); }
		public EnvalueExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterEnvalueExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitEnvalueExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitEnvalueExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FrontselfExpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SELFADD() { return getToken(MxParser.SELFADD, 0); }
		public TerminalNode SELFSUB() { return getToken(MxParser.SELFSUB, 0); }
		public FrontselfExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFrontselfExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFrontselfExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFrontselfExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public ExpressionGroupContext expressionGroup() {
			return getRuleContext(ExpressionGroupContext.class,0);
		}
		public FuncExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterFuncExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitFuncExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitFuncExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseAndExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITWISE_AND() { return getToken(MxParser.BITWISE_AND, 0); }
		public BitwiseAndExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBitwiseAndExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBitwiseAndExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBitwiseAndExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleExpContext extends ExpressionContext {
		public BaseExpContext baseExp() {
			return getRuleContext(BaseExpContext.class,0);
		}
		public SimpleExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterSimpleExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitSimpleExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitSimpleExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LEFT_SQUAREBRACKETS() { return getToken(MxParser.LEFT_SQUAREBRACKETS, 0); }
		public TerminalNode RIGHT_SQUAREBRACKETS() { return getToken(MxParser.RIGHT_SQUAREBRACKETS, 0); }
		public ArrayExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterArrayExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitArrayExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitArrayExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicAndExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LOGIC_AND() { return getToken(MxParser.LOGIC_AND, 0); }
		public LogicAndExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLogicAndExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLogicAndExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLogicAndExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(MxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxParser.SUB, 0); }
		public AddSubExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterAddSubExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitAddSubExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitAddSubExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseNoExpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BITWISE_NO() { return getToken(MxParser.BITWISE_NO, 0); }
		public BitwiseNoExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBitwiseNoExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBitwiseNoExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBitwiseNoExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrenumberExpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD() { return getToken(MxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxParser.SUB, 0); }
		public PrenumberExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterPrenumberExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitPrenumberExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitPrenumberExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESS() { return getToken(MxParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(MxParser.GREATER, 0); }
		public TerminalNode LESS_EQUARE() { return getToken(MxParser.LESS_EQUARE, 0); }
		public TerminalNode GREATER_EQUARE() { return getToken(MxParser.GREATER_EQUARE, 0); }
		public CompareExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterCompareExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitCompareExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitCompareExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicOrExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LOGIC_OR() { return getToken(MxParser.LOGIC_OR, 0); }
		public LogicOrExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLogicOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLogicOrExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLogicOrExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MxParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MxParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MxParser.MOD, 0); }
		public MulDivModExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterMulDivModExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitMulDivModExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitMulDivModExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BackselfExpContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SELFADD() { return getToken(MxParser.SELFADD, 0); }
		public TerminalNode SELFSUB() { return getToken(MxParser.SELFSUB, 0); }
		public BackselfExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBackselfExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBackselfExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBackselfExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExpContext extends ExpressionContext {
		public LambdaDefContext lambdaDef() {
			return getRuleContext(LambdaDefContext.class,0);
		}
		public LambdaExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLambdaExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLambdaExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLambdaExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseOrExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITWISE_OR() { return getToken(MxParser.BITWISE_OR, 0); }
		public BitwiseOrExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBitwiseOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBitwiseOrExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBitwiseOrExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsEquareExpContext extends ExpressionContext {
		public ExpressionContext lexp;
		public Token op;
		public ExpressionContext rexp;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUARE() { return getToken(MxParser.EQUARE, 0); }
		public TerminalNode UNEQUARE() { return getToken(MxParser.UNEQUARE, 0); }
		public IsEquareExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterIsEquareExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitIsEquareExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitIsEquareExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BoolTypeLiteral:
			case IntTypeLiteral:
			case NullTypeLiteral:
			case StringTypeLiteral:
			case THIS:
			case Identifier:
			case LEFT_PARENTHESIS:
				{
				_localctx = new SimpleExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(169);
				baseExp();
				}
				break;
			case NEW:
				{
				_localctx = new NewExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				creation();
				}
				break;
			case LEFT_SQUAREBRACKETS:
				{
				_localctx = new LambdaExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				lambdaDef();
				}
				break;
			case SELFADD:
			case SELFSUB:
				{
				_localctx = new FrontselfExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				((FrontselfExpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SELFADD || _la==SELFSUB) ) {
					((FrontselfExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(173);
				expression(15);
				}
				break;
			case ADD:
			case SUB:
				{
				_localctx = new PrenumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				((PrenumberExpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((PrenumberExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(175);
				expression(14);
				}
				break;
			case LOGIC_NO:
				{
				_localctx = new LogicNoExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				((LogicNoExpContext)_localctx).op = match(LOGIC_NO);
				setState(177);
				expression(13);
				}
				break;
			case BITWISE_NO:
				{
				_localctx = new BitwiseNoExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(178);
				((BitwiseNoExpContext)_localctx).op = match(BITWISE_NO);
				setState(179);
				expression(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(233);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(231);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExpContext(new ExpressionContext(_parentctx, _parentState));
						((MulDivModExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(182);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(183);
						((MulDivModExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivModExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						((MulDivModExpContext)_localctx).rexp = expression(12);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpContext(new ExpressionContext(_parentctx, _parentState));
						((AddSubExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(186);
						((AddSubExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(187);
						((AddSubExpContext)_localctx).rexp = expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BitwiseMoveExpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseMoveExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(189);
						((BitwiseMoveExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==BITWISE_MOVE_LEFT || _la==BITWISE_MOVE_RIGHT) ) {
							((BitwiseMoveExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						((BitwiseMoveExpContext)_localctx).rexp = expression(10);
						}
						break;
					case 4:
						{
						_localctx = new CompareExpContext(new ExpressionContext(_parentctx, _parentState));
						((CompareExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(192);
						((CompareExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_EQUARE) | (1L << LESS_EQUARE) | (1L << GREATER) | (1L << LESS))) != 0)) ) {
							((CompareExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(193);
						((CompareExpContext)_localctx).rexp = expression(9);
						}
						break;
					case 5:
						{
						_localctx = new IsEquareExpContext(new ExpressionContext(_parentctx, _parentState));
						((IsEquareExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(195);
						((IsEquareExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUARE || _la==UNEQUARE) ) {
							((IsEquareExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(196);
						((IsEquareExpContext)_localctx).rexp = expression(8);
						}
						break;
					case 6:
						{
						_localctx = new LogicAndExpContext(new ExpressionContext(_parentctx, _parentState));
						((LogicAndExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(197);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(198);
						((LogicAndExpContext)_localctx).op = match(LOGIC_AND);
						setState(199);
						((LogicAndExpContext)_localctx).rexp = expression(7);
						}
						break;
					case 7:
						{
						_localctx = new LogicOrExpContext(new ExpressionContext(_parentctx, _parentState));
						((LogicOrExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(201);
						((LogicOrExpContext)_localctx).op = match(LOGIC_OR);
						setState(202);
						((LogicOrExpContext)_localctx).rexp = expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BitwiseAndExpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseAndExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(204);
						((BitwiseAndExpContext)_localctx).op = match(BITWISE_AND);
						setState(205);
						((BitwiseAndExpContext)_localctx).rexp = expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BitwiseXorExpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseXorExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(207);
						((BitwiseXorExpContext)_localctx).op = match(BITWISE_XOR);
						setState(208);
						((BitwiseXorExpContext)_localctx).rexp = expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BitwiseOrExpContext(new ExpressionContext(_parentctx, _parentState));
						((BitwiseOrExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(209);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(210);
						((BitwiseOrExpContext)_localctx).op = match(BITWISE_OR);
						setState(211);
						((BitwiseOrExpContext)_localctx).rexp = expression(3);
						}
						break;
					case 11:
						{
						_localctx = new EnvalueExpContext(new ExpressionContext(_parentctx, _parentState));
						((EnvalueExpContext)_localctx).lexp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(212);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(213);
						((EnvalueExpContext)_localctx).op = match(ENVALUE);
						setState(214);
						((EnvalueExpContext)_localctx).rexp = expression(1);
						}
						break;
					case 12:
						{
						_localctx = new ArrayExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(216);
						match(LEFT_SQUAREBRACKETS);
						setState(217);
						expression(0);
						setState(218);
						match(RIGHT_SQUAREBRACKETS);
						}
						break;
					case 13:
						{
						_localctx = new FuncExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(221);
						match(LEFT_PARENTHESIS);
						setState(223);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral) | (1L << NEW) | (1L << THIS) | (1L << Identifier) | (1L << LEFT_PARENTHESIS) | (1L << LEFT_SQUAREBRACKETS) | (1L << SELFADD) | (1L << SELFSUB) | (1L << ADD) | (1L << SUB) | (1L << LOGIC_NO) | (1L << BITWISE_NO))) != 0)) {
							{
							setState(222);
							expressionGroup();
							}
						}

						setState(225);
						match(RIGHT_PARENTHESIS);
						}
						break;
					case 14:
						{
						_localctx = new MemberExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(227);
						match(DOT);
						setState(228);
						match(Identifier);
						}
						break;
					case 15:
						{
						_localctx = new BackselfExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(230);
						((BackselfExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SELFADD || _la==SELFSUB) ) {
							((BackselfExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionGroupContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxParser.COMMA, i);
		}
		public ExpressionGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterExpressionGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitExpressionGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitExpressionGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionGroupContext expressionGroup() throws RecognitionException {
		ExpressionGroupContext _localctx = new ExpressionGroupContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expressionGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			expression(0);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(237);
				match(COMMA);
				setState(238);
				expression(0);
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreationContext extends ParserRuleContext {
		public CreationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creation; }
	 
		public CreationContext() { }
		public void copyFrom(CreationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayCreationContext extends CreationContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public List<TerminalNode> LEFT_SQUAREBRACKETS() { return getTokens(MxParser.LEFT_SQUAREBRACKETS); }
		public TerminalNode LEFT_SQUAREBRACKETS(int i) {
			return getToken(MxParser.LEFT_SQUAREBRACKETS, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RIGHT_SQUAREBRACKETS() { return getTokens(MxParser.RIGHT_SQUAREBRACKETS); }
		public TerminalNode RIGHT_SQUAREBRACKETS(int i) {
			return getToken(MxParser.RIGHT_SQUAREBRACKETS, i);
		}
		public ArrayCreationContext(CreationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterArrayCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitArrayCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitArrayCreation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleCreationContext extends CreationContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public SimpleCreationContext(CreationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterSimpleCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitSimpleCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitSimpleCreation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassCreationContext extends CreationContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public ClassCreationContext(CreationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterClassCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitClassCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitClassCreation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreationContext creation() throws RecognitionException {
		CreationContext _localctx = new CreationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_creation);
		try {
			int _alt;
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new ArrayCreationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				match(NEW);
				setState(245);
				baseType();
				setState(250); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(246);
						match(LEFT_SQUAREBRACKETS);
						setState(247);
						expression(0);
						setState(248);
						match(RIGHT_SQUAREBRACKETS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(252); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(254);
						match(LEFT_SQUAREBRACKETS);
						setState(255);
						match(RIGHT_SQUAREBRACKETS);
						}
						} 
					}
					setState(260);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new ClassCreationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(NEW);
				setState(262);
				baseType();
				setState(263);
				match(LEFT_PARENTHESIS);
				setState(264);
				match(RIGHT_PARENTHESIS);
				}
				break;
			case 3:
				_localctx = new SimpleCreationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(266);
				match(NEW);
				setState(267);
				baseType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamGroupContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxParser.COMMA, i);
		}
		public ParamGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterParamGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitParamGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitParamGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamGroupContext paramGroup() throws RecognitionException {
		ParamGroupContext _localctx = new ParamGroupContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_paramGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			param();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(271);
				match(COMMA);
				setState(272);
				param();
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			type();
			setState(279);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public List<TerminalNode> LEFT_SQUAREBRACKETS() { return getTokens(MxParser.LEFT_SQUAREBRACKETS); }
		public TerminalNode LEFT_SQUAREBRACKETS(int i) {
			return getToken(MxParser.LEFT_SQUAREBRACKETS, i);
		}
		public List<TerminalNode> RIGHT_SQUAREBRACKETS() { return getTokens(MxParser.RIGHT_SQUAREBRACKETS); }
		public TerminalNode RIGHT_SQUAREBRACKETS(int i) {
			return getToken(MxParser.RIGHT_SQUAREBRACKETS, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			baseType();
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LEFT_SQUAREBRACKETS) {
				{
				{
				setState(282);
				match(LEFT_SQUAREBRACKETS);
				setState(283);
				match(RIGHT_SQUAREBRACKETS);
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MxParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MxParser.STRING, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(MxParser.VOID, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnType);
		try {
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case STRING:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				baseType();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode BoolTypeLiteral() { return getToken(MxParser.BoolTypeLiteral, 0); }
		public TerminalNode IntTypeLiteral() { return getToken(MxParser.IntTypeLiteral, 0); }
		public TerminalNode NullTypeLiteral() { return getToken(MxParser.NullTypeLiteral, 0); }
		public TerminalNode StringTypeLiteral() { return getToken(MxParser.StringTypeLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BoolTypeLiteral) | (1L << IntTypeLiteral) | (1L << NullTypeLiteral) | (1L << StringTypeLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseExpContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode LEFT_PARENTHESIS() { return getToken(MxParser.LEFT_PARENTHESIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(MxParser.RIGHT_PARENTHESIS, 0); }
		public TerminalNode THIS() { return getToken(MxParser.THIS, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BaseExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).enterBaseExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxParserListener ) ((MxParserListener)listener).exitBaseExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxParserVisitor ) return ((MxParserVisitor<? extends T>)visitor).visitBaseExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseExpContext baseExp() throws RecognitionException {
		BaseExpContext _localctx = new BaseExpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_baseExp);
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BoolTypeLiteral:
			case IntTypeLiteral:
			case NullTypeLiteral:
			case StringTypeLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				literal();
				}
				break;
			case LEFT_PARENTHESIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(LEFT_PARENTHESIS);
				setState(299);
				expression(0);
				setState(300);
				match(RIGHT_PARENTHESIS);
				}
				break;
			case THIS:
				enterOuterAlt(_localctx, 3);
				{
				setState(302);
				match(THIS);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 4);
				{
				setState(303);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 21);
		case 12:
			return precpred(_ctx, 20);
		case 13:
			return precpred(_ctx, 19);
		case 14:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001=\u0133\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0005\u0000"+
		"&\b\u0000\n\u0000\f\u0000)\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u00010\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u00026\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002<\b\u0002\u0005\u0002>\b\u0002\n\u0002"+
		"\f\u0002A\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0003\u0003F\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003K\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004U\b\u0004\n\u0004\f\u0004X\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003\u0005_\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005d\b\u0005\u0001\u0005"+
		"\u0003\u0005g\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005m\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006z\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u007f\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0083\b"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0087\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0093\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u009e\b\u0006\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00a2\b\u0007\n\u0007\f\u0007\u00a5\t\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u00b5\b\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u00e0\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u00e8\b\b\n\b\f\b\u00eb\t\b\u0001\t\u0001\t\u0001\t\u0005\t"+
		"\u00f0\b\t\n\t\f\t\u00f3\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0004\n\u00fb\b\n\u000b\n\f\n\u00fc\u0001\n\u0001\n\u0005\n\u0101\b"+
		"\n\n\n\f\n\u0104\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u010d\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0112"+
		"\b\u000b\n\u000b\f\u000b\u0115\t\u000b\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\r\u0005\r\u011d\b\r\n\r\f\r\u0120\t\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0126\b\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u0131\b\u0011\u0001\u0011\u0000\u0001\u0010"+
		"\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"\u0000\b\u0001\u0000#$\u0001\u0000()\u0001\u0000"+
		"%\'\u0001\u0000./\u0001\u000036\u0001\u000078\u0002\u0000\u0007\t\u0017"+
		"\u0017\u0002\u0000\u0001\u0002\u0004\u0005\u015e\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0002/\u0001\u0000\u0000\u0000\u00041\u0001\u0000\u0000\u0000\u0006"+
		"E\u0001\u0000\u0000\u0000\bO\u0001\u0000\u0000\u0000\n\\\u0001\u0000\u0000"+
		"\u0000\f\u009d\u0001\u0000\u0000\u0000\u000e\u009f\u0001\u0000\u0000\u0000"+
		"\u0010\u00b4\u0001\u0000\u0000\u0000\u0012\u00ec\u0001\u0000\u0000\u0000"+
		"\u0014\u010c\u0001\u0000\u0000\u0000\u0016\u010e\u0001\u0000\u0000\u0000"+
		"\u0018\u0116\u0001\u0000\u0000\u0000\u001a\u0119\u0001\u0000\u0000\u0000"+
		"\u001c\u0121\u0001\u0000\u0000\u0000\u001e\u0125\u0001\u0000\u0000\u0000"+
		" \u0127\u0001\u0000\u0000\u0000\"\u0130\u0001\u0000\u0000\u0000$&\u0003"+
		"\u0002\u0001\u0000%$\u0001\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(*\u0001\u0000\u0000"+
		"\u0000)\'\u0001\u0000\u0000\u0000*+\u0005\u0000\u0000\u0001+\u0001\u0001"+
		"\u0000\u0000\u0000,0\u0003\u0004\u0002\u0000-0\u0003\u0006\u0003\u0000"+
		".0\u0003\b\u0004\u0000/,\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000"+
		"/.\u0001\u0000\u0000\u00000\u0003\u0001\u0000\u0000\u000012\u0003\u001a"+
		"\r\u000025\u0005\u0017\u0000\u000034\u00059\u0000\u000046\u0003\u0010"+
		"\b\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006?\u0001\u0000"+
		"\u0000\u000078\u0005 \u0000\u00008;\u0005\u0017\u0000\u00009:\u00059\u0000"+
		"\u0000:<\u0003\u0010\b\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<>\u0001\u0000\u0000\u0000=7\u0001\u0000\u0000\u0000>A\u0001\u0000"+
		"\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@B\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BC\u0005\u001f\u0000\u0000"+
		"C\u0005\u0001\u0000\u0000\u0000DF\u0003\u001e\u000f\u0000ED\u0001\u0000"+
		"\u0000\u0000EF\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0005"+
		"\u0017\u0000\u0000HJ\u0005\u0018\u0000\u0000IK\u0003\u0016\u000b\u0000"+
		"JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000"+
		"\u0000LM\u0005\u0019\u0000\u0000MN\u0003\u000e\u0007\u0000N\u0007\u0001"+
		"\u0000\u0000\u0000OP\u0005\u000b\u0000\u0000PQ\u0005\u0017\u0000\u0000"+
		"QV\u0005\u001c\u0000\u0000RU\u0003\u0004\u0002\u0000SU\u0003\u0006\u0003"+
		"\u0000TR\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000UX\u0001\u0000"+
		"\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WY\u0001"+
		"\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0005\u001d\u0000\u0000"+
		"Z[\u0005\u001f\u0000\u0000[\t\u0001\u0000\u0000\u0000\\^\u0005\u001a\u0000"+
		"\u0000]_\u00050\u0000\u0000^]\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`f\u0005\u001b\u0000\u0000ac\u0005\u0018"+
		"\u0000\u0000bd\u0003\u0016\u000b\u0000cb\u0001\u0000\u0000\u0000cd\u0001"+
		"\u0000\u0000\u0000de\u0001\u0000\u0000\u0000eg\u0005\u0019\u0000\u0000"+
		"fa\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0005\"\u0000\u0000ij\u0003\u000e\u0007\u0000jl\u0005\u0018\u0000"+
		"\u0000km\u0003\u0012\t\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000"+
		"\u0000mn\u0001\u0000\u0000\u0000no\u0005\u0019\u0000\u0000o\u000b\u0001"+
		"\u0000\u0000\u0000p\u009e\u0003\u000e\u0007\u0000q\u009e\u0003\u0004\u0002"+
		"\u0000rs\u0005\u0010\u0000\u0000st\u0005\u0018\u0000\u0000tu\u0003\u0010"+
		"\b\u0000uv\u0005\u0019\u0000\u0000vy\u0003\f\u0006\u0000wx\u0005\u0011"+
		"\u0000\u0000xz\u0003\f\u0006\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z\u009e\u0001\u0000\u0000\u0000{|\u0005\u0012\u0000\u0000"+
		"|~\u0005\u0018\u0000\u0000}\u007f\u0003\u0010\b\u0000~}\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000"+
		"\u0080\u0082\u0005\u001f\u0000\u0000\u0081\u0083\u0003\u0010\b\u0000\u0082"+
		"\u0081\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0001\u0000\u0000\u0000\u0084\u0086\u0005\u001f\u0000\u0000\u0085"+
		"\u0087\u0003\u0010\b\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089"+
		"\u0005\u0019\u0000\u0000\u0089\u009e\u0003\f\u0006\u0000\u008a\u008b\u0005"+
		"\u0013\u0000\u0000\u008b\u008c\u0005\u0018\u0000\u0000\u008c\u008d\u0003"+
		"\u0010\b\u0000\u008d\u008e\u0005\u0019\u0000\u0000\u008e\u008f\u0003\f"+
		"\u0006\u0000\u008f\u009e\u0001\u0000\u0000\u0000\u0090\u0092\u0005\u0016"+
		"\u0000\u0000\u0091\u0093\u0003\u0010\b\u0000\u0092\u0091\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000"+
		"\u0000\u0094\u009e\u0005\u001f\u0000\u0000\u0095\u0096\u0005\u0014\u0000"+
		"\u0000\u0096\u009e\u0005\u001f\u0000\u0000\u0097\u0098\u0005\u0015\u0000"+
		"\u0000\u0098\u009e\u0005\u001f\u0000\u0000\u0099\u009a\u0003\u0010\b\u0000"+
		"\u009a\u009b\u0005\u001f\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000"+
		"\u009c\u009e\u0005\u001f\u0000\u0000\u009dp\u0001\u0000\u0000\u0000\u009d"+
		"q\u0001\u0000\u0000\u0000\u009dr\u0001\u0000\u0000\u0000\u009d{\u0001"+
		"\u0000\u0000\u0000\u009d\u008a\u0001\u0000\u0000\u0000\u009d\u0090\u0001"+
		"\u0000\u0000\u0000\u009d\u0095\u0001\u0000\u0000\u0000\u009d\u0097\u0001"+
		"\u0000\u0000\u0000\u009d\u0099\u0001\u0000\u0000\u0000\u009d\u009c\u0001"+
		"\u0000\u0000\u0000\u009e\r\u0001\u0000\u0000\u0000\u009f\u00a3\u0005\u001c"+
		"\u0000\u0000\u00a0\u00a2\u0003\f\u0006\u0000\u00a1\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\u001d\u0000"+
		"\u0000\u00a7\u000f\u0001\u0000\u0000\u0000\u00a8\u00a9\u0006\b\uffff\uffff"+
		"\u0000\u00a9\u00b5\u0003\"\u0011\u0000\u00aa\u00b5\u0003\u0014\n\u0000"+
		"\u00ab\u00b5\u0003\n\u0005\u0000\u00ac\u00ad\u0007\u0000\u0000\u0000\u00ad"+
		"\u00b5\u0003\u0010\b\u000f\u00ae\u00af\u0007\u0001\u0000\u0000\u00af\u00b5"+
		"\u0003\u0010\b\u000e\u00b0\u00b1\u0005*\u0000\u0000\u00b1\u00b5\u0003"+
		"\u0010\b\r\u00b2\u00b3\u0005-\u0000\u0000\u00b3\u00b5\u0003\u0010\b\f"+
		"\u00b4\u00a8\u0001\u0000\u0000\u0000\u00b4\u00aa\u0001\u0000\u0000\u0000"+
		"\u00b4\u00ab\u0001\u0000\u0000\u0000\u00b4\u00ac\u0001\u0000\u0000\u0000"+
		"\u00b4\u00ae\u0001\u0000\u0000\u0000\u00b4\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00e9\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\n\u000b\u0000\u0000\u00b7\u00b8\u0007\u0002\u0000\u0000\u00b8"+
		"\u00e8\u0003\u0010\b\f\u00b9\u00ba\n\n\u0000\u0000\u00ba\u00bb\u0007\u0001"+
		"\u0000\u0000\u00bb\u00e8\u0003\u0010\b\u000b\u00bc\u00bd\n\t\u0000\u0000"+
		"\u00bd\u00be\u0007\u0003\u0000\u0000\u00be\u00e8\u0003\u0010\b\n\u00bf"+
		"\u00c0\n\b\u0000\u0000\u00c0\u00c1\u0007\u0004\u0000\u0000\u00c1\u00e8"+
		"\u0003\u0010\b\t\u00c2\u00c3\n\u0007\u0000\u0000\u00c3\u00c4\u0007\u0005"+
		"\u0000\u0000\u00c4\u00e8\u0003\u0010\b\b\u00c5\u00c6\n\u0006\u0000\u0000"+
		"\u00c6\u00c7\u0005+\u0000\u0000\u00c7\u00e8\u0003\u0010\b\u0007\u00c8"+
		"\u00c9\n\u0005\u0000\u0000\u00c9\u00ca\u0005,\u0000\u0000\u00ca\u00e8"+
		"\u0003\u0010\b\u0006\u00cb\u00cc\n\u0004\u0000\u0000\u00cc\u00cd\u0005"+
		"0\u0000\u0000\u00cd\u00e8\u0003\u0010\b\u0005\u00ce\u00cf\n\u0003\u0000"+
		"\u0000\u00cf\u00d0\u00052\u0000\u0000\u00d0\u00e8\u0003\u0010\b\u0004"+
		"\u00d1\u00d2\n\u0002\u0000\u0000\u00d2\u00d3\u00051\u0000\u0000\u00d3"+
		"\u00e8\u0003\u0010\b\u0003\u00d4\u00d5\n\u0001\u0000\u0000\u00d5\u00d6"+
		"\u00059\u0000\u0000\u00d6\u00e8\u0003\u0010\b\u0001\u00d7\u00d8\n\u0015"+
		"\u0000\u0000\u00d8\u00d9\u0005\u001a\u0000\u0000\u00d9\u00da\u0003\u0010"+
		"\b\u0000\u00da\u00db\u0005\u001b\u0000\u0000\u00db\u00e8\u0001\u0000\u0000"+
		"\u0000\u00dc\u00dd\n\u0014\u0000\u0000\u00dd\u00df\u0005\u0018\u0000\u0000"+
		"\u00de\u00e0\u0003\u0012\t\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00df"+
		"\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e8\u0005\u0019\u0000\u0000\u00e2\u00e3\n\u0013\u0000\u0000\u00e3\u00e4"+
		"\u0005!\u0000\u0000\u00e4\u00e8\u0005\u0017\u0000\u0000\u00e5\u00e6\n"+
		"\u0010\u0000\u0000\u00e6\u00e8\u0007\u0000\u0000\u0000\u00e7\u00b6\u0001"+
		"\u0000\u0000\u0000\u00e7\u00b9\u0001\u0000\u0000\u0000\u00e7\u00bc\u0001"+
		"\u0000\u0000\u0000\u00e7\u00bf\u0001\u0000\u0000\u0000\u00e7\u00c2\u0001"+
		"\u0000\u0000\u0000\u00e7\u00c5\u0001\u0000\u0000\u0000\u00e7\u00c8\u0001"+
		"\u0000\u0000\u0000\u00e7\u00cb\u0001\u0000\u0000\u0000\u00e7\u00ce\u0001"+
		"\u0000\u0000\u0000\u00e7\u00d1\u0001\u0000\u0000\u0000\u00e7\u00d4\u0001"+
		"\u0000\u0000\u0000\u00e7\u00d7\u0001\u0000\u0000\u0000\u00e7\u00dc\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e2\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001"+
		"\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u0011\u0001"+
		"\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00f1\u0003"+
		"\u0010\b\u0000\u00ed\u00ee\u0005 \u0000\u0000\u00ee\u00f0\u0003\u0010"+
		"\b\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f2\u0013\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f5\u0005\n\u0000\u0000\u00f5\u00fa\u0003\u001c\u000e\u0000"+
		"\u00f6\u00f7\u0005\u001a\u0000\u0000\u00f7\u00f8\u0003\u0010\b\u0000\u00f8"+
		"\u00f9\u0005\u001b\u0000\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa"+
		"\u00f6\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd"+
		"\u0102\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\u001a\u0000\u0000\u00ff"+
		"\u0101\u0005\u001b\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101"+
		"\u0104\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102"+
		"\u0103\u0001\u0000\u0000\u0000\u0103\u010d\u0001\u0000\u0000\u0000\u0104"+
		"\u0102\u0001\u0000\u0000\u0000\u0105\u0106\u0005\n\u0000\u0000\u0106\u0107"+
		"\u0003\u001c\u000e\u0000\u0107\u0108\u0005\u0018\u0000\u0000\u0108\u0109"+
		"\u0005\u0019\u0000\u0000\u0109\u010d\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\u0005\n\u0000\u0000\u010b\u010d\u0003\u001c\u000e\u0000\u010c\u00f4\u0001"+
		"\u0000\u0000\u0000\u010c\u0105\u0001\u0000\u0000\u0000\u010c\u010a\u0001"+
		"\u0000\u0000\u0000\u010d\u0015\u0001\u0000\u0000\u0000\u010e\u0113\u0003"+
		"\u0018\f\u0000\u010f\u0110\u0005 \u0000\u0000\u0110\u0112\u0003\u0018"+
		"\f\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0112\u0115\u0001\u0000\u0000"+
		"\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000"+
		"\u0000\u0114\u0017\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0003\u001a\r\u0000\u0117\u0118\u0005\u0017\u0000\u0000"+
		"\u0118\u0019\u0001\u0000\u0000\u0000\u0119\u011e\u0003\u001c\u000e\u0000"+
		"\u011a\u011b\u0005\u001a\u0000\u0000\u011b\u011d\u0005\u001b\u0000\u0000"+
		"\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u0120\u0001\u0000\u0000\u0000"+
		"\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000"+
		"\u011f\u001b\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0007\u0006\u0000\u0000\u0122\u001d\u0001\u0000\u0000\u0000"+
		"\u0123\u0126\u0003\u001c\u000e\u0000\u0124\u0126\u0005\u0006\u0000\u0000"+
		"\u0125\u0123\u0001\u0000\u0000\u0000\u0125\u0124\u0001\u0000\u0000\u0000"+
		"\u0126\u001f\u0001\u0000\u0000\u0000\u0127\u0128\u0007\u0007\u0000\u0000"+
		"\u0128!\u0001\u0000\u0000\u0000\u0129\u0131\u0003 \u0010\u0000\u012a\u012b"+
		"\u0005\u0018\u0000\u0000\u012b\u012c\u0003\u0010\b\u0000\u012c\u012d\u0005"+
		"\u0019\u0000\u0000\u012d\u0131\u0001\u0000\u0000\u0000\u012e\u0131\u0005"+
		"\u000f\u0000\u0000\u012f\u0131\u0005\u0017\u0000\u0000\u0130\u0129\u0001"+
		"\u0000\u0000\u0000\u0130\u012a\u0001\u0000\u0000\u0000\u0130\u012e\u0001"+
		"\u0000\u0000\u0000\u0130\u012f\u0001\u0000\u0000\u0000\u0131#\u0001\u0000"+
		"\u0000\u0000 \'/5;?EJTV^cfly~\u0082\u0086\u0092\u009d\u00a3\u00b4\u00df"+
		"\u00e7\u00e9\u00f1\u00fc\u0102\u010c\u0113\u011e\u0125\u0130";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}