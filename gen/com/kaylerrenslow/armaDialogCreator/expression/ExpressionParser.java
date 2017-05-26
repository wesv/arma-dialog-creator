// Generated from D:/Archive/Intellij Files/Arma Tools/Arma Dialog Creator/src/com/kaylerrenslow/armaDialogCreator/expression\Expression.g4 by ANTLR 4.7
package com.kaylerrenslow.armaDialogCreator.expression;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		String=1, Quote=2, DQuote=3, LCurly=4, RCurly=5, LBracket=6, RBracket=7, 
		Plus=8, Minus=9, FSlash=10, Star=11, LParen=12, RParen=13, Comma=14, Min=15, 
		Max=16, If=17, Then=18, Else=19, ExitWith=20, Select=21, For=22, From=23, 
		To=24, Step=25, Do=26, EqEq=27, NotEq=28, Lt=29, LtEq=30, Gt=31, GtEq=32, 
		Equal=33, Semicolon=34, Identifier=35, IntegerLiteral=36, FloatLiteral=37, 
		Digits=38, DecSignificand=39, DecExponent=40, HexLiteral=41, HexDigits=42, 
		Letter=43, LetterOrDigit=44, WhiteSpace=45;
	public static final int
		RULE_statements = 0, RULE_statement = 1, RULE_assignment = 2, RULE_code = 3, 
		RULE_expression = 4, RULE_unary_expression = 5, RULE_paren_expression = 6, 
		RULE_literal_expression = 7, RULE_if_expression = 8, RULE_for_expression = 9, 
		RULE_array = 10, RULE_int_value = 11, RULE_float_value = 12;
	public static final String[] ruleNames = {
		"statements", "statement", "assignment", "code", "expression", "unary_expression", 
		"paren_expression", "literal_expression", "if_expression", "for_expression", 
		"array", "int_value", "float_value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'''", "'\"'", "'{'", "'}'", "'['", "']'", "'+'", "'-'", "'/'", 
		"'*'", "'('", "')'", "','", "'min'", "'max'", "'if'", "'then'", "'else'", 
		"'exitWith'", "'select'", "'for'", "'from'", "'to'", "'step'", "'do'", 
		"'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'='", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "String", "Quote", "DQuote", "LCurly", "RCurly", "LBracket", "RBracket", 
		"Plus", "Minus", "FSlash", "Star", "LParen", "RParen", "Comma", "Min", 
		"Max", "If", "Then", "Else", "ExitWith", "Select", "For", "From", "To", 
		"Step", "Do", "EqEq", "NotEq", "Lt", "LtEq", "Gt", "GtEq", "Equal", "Semicolon", 
		"Identifier", "IntegerLiteral", "FloatLiteral", "Digits", "DecSignificand", 
		"DecExponent", "HexLiteral", "HexDigits", "Letter", "LetterOrDigit", "WhiteSpace"
	};
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
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementsContext extends ParserRuleContext {
		public List<AST.Statement> lst;
		public StatementContext s;
		public StatementContext s2;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> Semicolon() { return getTokens(ExpressionParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(ExpressionParser.Semicolon, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statements);
		 ((StatementsContext)_localctx).lst =  new ArrayList<>(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(26);
					((StatementsContext)_localctx).s = statement();
					setState(27);
					match(Semicolon);
					_localctx.lst.add(((StatementsContext)_localctx).s.ast);
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(35);
			((StatementsContext)_localctx).s2 = statement();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Semicolon) {
				{
				setState(36);
				match(Semicolon);
				}
			}

			_localctx.lst.add(((StatementsContext)_localctx).s2.ast);
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

	public static class StatementContext extends ParserRuleContext {
		public AST.Statement ast;
		public AssignmentContext a;
		public ExpressionContext e;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(47);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				((StatementContext)_localctx).a = assignment();
				((StatementContext)_localctx).ast =  new AST.Statement(((StatementContext)_localctx).a.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				((StatementContext)_localctx).e = expression(0);
				((StatementContext)_localctx).ast =  new AST.Statement(((StatementContext)_localctx).e.ast);
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

	public static class AssignmentContext extends ParserRuleContext {
		public AST.Assignment ast;
		public Token i;
		public ExpressionContext e;
		public TerminalNode Equal() { return getToken(ExpressionParser.Equal, 0); }
		public TerminalNode Identifier() { return getToken(ExpressionParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((AssignmentContext)_localctx).i = match(Identifier);
			setState(50);
			match(Equal);
			setState(51);
			((AssignmentContext)_localctx).e = expression(0);
			((AssignmentContext)_localctx).ast =  new AST.Assignment((((AssignmentContext)_localctx).i!=null?((AssignmentContext)_localctx).i.getText():null), ((AssignmentContext)_localctx).e.ast);
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

	public static class CodeContext extends ParserRuleContext {
		public AST.Code ast;
		public List<AST.Statement> lst;
		public StatementsContext s;
		public TerminalNode LCurly() { return getToken(ExpressionParser.LCurly, 0); }
		public TerminalNode RCurly() { return getToken(ExpressionParser.RCurly, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_code);
		 ((CodeContext)_localctx).lst =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(LCurly);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << String) | (1L << LCurly) | (1L << LBracket) | (1L << Plus) | (1L << Minus) | (1L << LParen) | (1L << If) | (1L << For) | (1L << Identifier) | (1L << IntegerLiteral) | (1L << FloatLiteral) | (1L << HexLiteral))) != 0)) {
				{
				setState(55);
				((CodeContext)_localctx).s = statements();
				((CodeContext)_localctx).lst = ((CodeContext)_localctx).s.lst;
				}
			}

			setState(60);
			match(RCurly);
			((CodeContext)_localctx).ast =  new AST.Code(_localctx.lst);
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
		public AST.Expr ast;
		public ExpressionContext ls;
		public ExpressionContext lf;
		public ExpressionContext la;
		public ExpressionContext lm;
		public ExpressionContext lcomp;
		public ExpressionContext lmax;
		public ExpressionContext lmin;
		public ExpressionContext select_e;
		public Unary_expressionContext lu;
		public Paren_expressionContext lp;
		public Literal_expressionContext ll;
		public If_expressionContext ifexp;
		public For_expressionContext forexp;
		public CodeContext codeExp;
		public ExpressionContext rs;
		public ExpressionContext rf;
		public ExpressionContext ra;
		public ExpressionContext rm;
		public Token compOp;
		public ExpressionContext rcomp;
		public ExpressionContext rmax;
		public ExpressionContext rmin;
		public ExpressionContext select_i;
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public Paren_expressionContext paren_expression() {
			return getRuleContext(Paren_expressionContext.class,0);
		}
		public Literal_expressionContext literal_expression() {
			return getRuleContext(Literal_expressionContext.class,0);
		}
		public If_expressionContext if_expression() {
			return getRuleContext(If_expressionContext.class,0);
		}
		public For_expressionContext for_expression() {
			return getRuleContext(For_expressionContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TerminalNode Star() { return getToken(ExpressionParser.Star, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode FSlash() { return getToken(ExpressionParser.FSlash, 0); }
		public TerminalNode Plus() { return getToken(ExpressionParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(ExpressionParser.Minus, 0); }
		public TerminalNode EqEq() { return getToken(ExpressionParser.EqEq, 0); }
		public TerminalNode NotEq() { return getToken(ExpressionParser.NotEq, 0); }
		public TerminalNode LtEq() { return getToken(ExpressionParser.LtEq, 0); }
		public TerminalNode Lt() { return getToken(ExpressionParser.Lt, 0); }
		public TerminalNode GtEq() { return getToken(ExpressionParser.GtEq, 0); }
		public TerminalNode Gt() { return getToken(ExpressionParser.Gt, 0); }
		public TerminalNode Max() { return getToken(ExpressionParser.Max, 0); }
		public TerminalNode Min() { return getToken(ExpressionParser.Min, 0); }
		public TerminalNode Select() { return getToken(ExpressionParser.Select, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
			case Minus:
				{
				setState(64);
				((ExpressionContext)_localctx).lu = unary_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).lu.ast;
				}
				break;
			case LParen:
				{
				setState(67);
				((ExpressionContext)_localctx).lp = paren_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).lp.ast;
				}
				break;
			case String:
			case LBracket:
			case Identifier:
			case IntegerLiteral:
			case FloatLiteral:
			case HexLiteral:
				{
				setState(70);
				((ExpressionContext)_localctx).ll = literal_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).ll.ast;
				}
				break;
			case If:
				{
				setState(73);
				((ExpressionContext)_localctx).ifexp = if_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).ifexp.ast;
				}
				break;
			case For:
				{
				setState(76);
				((ExpressionContext)_localctx).forexp = for_expression();
				((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).forexp.ast;
				}
				break;
			case LCurly:
				{
				setState(79);
				((ExpressionContext)_localctx).codeExp = code();
				((ExpressionContext)_localctx).ast =  new AST.CodeExpr(((ExpressionContext)_localctx).codeExp.ast);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(124);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.ls = _prevctx;
						_localctx.ls = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(84);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(85);
						match(Star);
						setState(86);
						((ExpressionContext)_localctx).rs = expression(13);
						((ExpressionContext)_localctx).ast =  new AST.MultExpr(((ExpressionContext)_localctx).ls.ast, ((ExpressionContext)_localctx).rs.ast);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lf = _prevctx;
						_localctx.lf = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(89);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(90);
						match(FSlash);
						setState(91);
						((ExpressionContext)_localctx).rf = expression(12);
						((ExpressionContext)_localctx).ast =  new AST.DivExpr(((ExpressionContext)_localctx).lf.ast, ((ExpressionContext)_localctx).rf.ast);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.la = _prevctx;
						_localctx.la = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(95);
						match(Plus);
						setState(96);
						((ExpressionContext)_localctx).ra = expression(11);
						((ExpressionContext)_localctx).ast =  new AST.AddExpr(((ExpressionContext)_localctx).la.ast, ((ExpressionContext)_localctx).ra.ast);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lm = _prevctx;
						_localctx.lm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(99);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(100);
						match(Minus);
						setState(101);
						((ExpressionContext)_localctx).rm = expression(10);
						((ExpressionContext)_localctx).ast =  new AST.SubExpr(((ExpressionContext)_localctx).lm.ast, ((ExpressionContext)_localctx).rm.ast);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lcomp = _prevctx;
						_localctx.lcomp = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(104);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(105);
						((ExpressionContext)_localctx).compOp = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EqEq) | (1L << NotEq) | (1L << Lt) | (1L << LtEq) | (1L << Gt) | (1L << GtEq))) != 0)) ) {
							((ExpressionContext)_localctx).compOp = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(106);
						((ExpressionContext)_localctx).rcomp = expression(9);
						((ExpressionContext)_localctx).ast =  new AST.CompExpr(((ExpressionContext)_localctx).lcomp.ast, ((ExpressionContext)_localctx).rcomp.ast, (((ExpressionContext)_localctx).compOp!=null?((ExpressionContext)_localctx).compOp.getText():null));
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lmax = _prevctx;
						_localctx.lmax = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(110);
						match(Max);
						setState(111);
						((ExpressionContext)_localctx).rmax = expression(7);
						((ExpressionContext)_localctx).ast =  new AST.MaxExpr(((ExpressionContext)_localctx).lmax.ast, ((ExpressionContext)_localctx).rmax.ast);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.lmin = _prevctx;
						_localctx.lmin = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(114);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(115);
						match(Min);
						setState(116);
						((ExpressionContext)_localctx).rmin = expression(6);
						((ExpressionContext)_localctx).ast =  new AST.MinExpr(((ExpressionContext)_localctx).lmin.ast, ((ExpressionContext)_localctx).rmin.ast);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.select_e = _prevctx;
						_localctx.select_e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(119);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(120);
						match(Select);
						setState(121);
						((ExpressionContext)_localctx).select_i = expression(5);
						((ExpressionContext)_localctx).ast =  new AST.SelectExpr(((ExpressionContext)_localctx).select_e.ast, ((ExpressionContext)_localctx).select_i.ast);
						}
						break;
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class Unary_expressionContext extends ParserRuleContext {
		public AST.UnaryExpr ast;
		public Paren_expressionContext ep;
		public Literal_expressionContext ep1;
		public Paren_expressionContext em;
		public Literal_expressionContext em1;
		public TerminalNode Plus() { return getToken(ExpressionParser.Plus, 0); }
		public Paren_expressionContext paren_expression() {
			return getRuleContext(Paren_expressionContext.class,0);
		}
		public Literal_expressionContext literal_expression() {
			return getRuleContext(Literal_expressionContext.class,0);
		}
		public TerminalNode Minus() { return getToken(ExpressionParser.Minus, 0); }
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterUnary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitUnary_expression(this);
		}
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unary_expression);
		try {
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(Plus);
				setState(130);
				((Unary_expressionContext)_localctx).ep = paren_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(true, ((Unary_expressionContext)_localctx).ep.ast);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(Plus);
				setState(134);
				((Unary_expressionContext)_localctx).ep1 = literal_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(true, ((Unary_expressionContext)_localctx).ep1.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(Minus);
				setState(138);
				((Unary_expressionContext)_localctx).em = paren_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(false, ((Unary_expressionContext)_localctx).em.ast);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				match(Minus);
				setState(142);
				((Unary_expressionContext)_localctx).em1 = literal_expression();
				((Unary_expressionContext)_localctx).ast =  new AST.UnaryExpr(false, ((Unary_expressionContext)_localctx).em1.ast);
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

	public static class Paren_expressionContext extends ParserRuleContext {
		public AST.ParenExpr ast;
		public ExpressionContext e;
		public TerminalNode LParen() { return getToken(ExpressionParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(ExpressionParser.RParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Paren_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterParen_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitParen_expression(this);
		}
	}

	public final Paren_expressionContext paren_expression() throws RecognitionException {
		Paren_expressionContext _localctx = new Paren_expressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paren_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(LParen);
			setState(148);
			((Paren_expressionContext)_localctx).e = expression(0);
			setState(149);
			match(RParen);
			((Paren_expressionContext)_localctx).ast =  new AST.ParenExpr(((Paren_expressionContext)_localctx).e.ast);
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

	public static class Literal_expressionContext extends ParserRuleContext {
		public AST.LiteralExpr ast;
		public Token id;
		public Int_valueContext i;
		public Float_valueContext f;
		public Token s;
		public ArrayContext a;
		public TerminalNode Identifier() { return getToken(ExpressionParser.Identifier, 0); }
		public Int_valueContext int_value() {
			return getRuleContext(Int_valueContext.class,0);
		}
		public Float_valueContext float_value() {
			return getRuleContext(Float_valueContext.class,0);
		}
		public TerminalNode String() { return getToken(ExpressionParser.String, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public Literal_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterLiteral_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitLiteral_expression(this);
		}
	}

	public final Literal_expressionContext literal_expression() throws RecognitionException {
		Literal_expressionContext _localctx = new Literal_expressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal_expression);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				((Literal_expressionContext)_localctx).id = match(Identifier);
				((Literal_expressionContext)_localctx).ast =  new AST.IdentifierExpr((((Literal_expressionContext)_localctx).id!=null?((Literal_expressionContext)_localctx).id.getText():null));
				}
				break;
			case IntegerLiteral:
			case HexLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				((Literal_expressionContext)_localctx).i = int_value();
				((Literal_expressionContext)_localctx).ast =  new AST.IntegerExpr(((Literal_expressionContext)_localctx).i.i);
				}
				break;
			case FloatLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				((Literal_expressionContext)_localctx).f = float_value();
				((Literal_expressionContext)_localctx).ast =  new AST.FloatExpr(((Literal_expressionContext)_localctx).f.d);
				}
				break;
			case String:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				((Literal_expressionContext)_localctx).s = match(String);
				((Literal_expressionContext)_localctx).ast =  new AST.StringExpr((((Literal_expressionContext)_localctx).s!=null?((Literal_expressionContext)_localctx).s.getText():null));
				}
				break;
			case LBracket:
				enterOuterAlt(_localctx, 5);
				{
				setState(162);
				((Literal_expressionContext)_localctx).a = array();
				((Literal_expressionContext)_localctx).ast =  ((Literal_expressionContext)_localctx).a.ast;
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

	public static class If_expressionContext extends ParserRuleContext {
		public AST.IfExpr ast;
		public ExpressionContext cond;
		public ExpressionContext exitWith;
		public ArrayContext arr;
		public ExpressionContext condIsTrue;
		public ExpressionContext condIsFalse;
		public TerminalNode If() { return getToken(ExpressionParser.If, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ExitWith() { return getToken(ExpressionParser.ExitWith, 0); }
		public TerminalNode Then() { return getToken(ExpressionParser.Then, 0); }
		public TerminalNode Else() { return getToken(ExpressionParser.Else, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public If_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterIf_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitIf_expression(this);
		}
	}

	public final If_expressionContext if_expression() throws RecognitionException {
		If_expressionContext _localctx = new If_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(If);
			setState(168);
			((If_expressionContext)_localctx).cond = expression(0);
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				{
				setState(169);
				match(ExitWith);
				setState(170);
				((If_expressionContext)_localctx).exitWith = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).exitWith.ast, null, AST.IfExpr.Type.ExitWith);
				}
				}
				break;
			case 2:
				{
				{
				setState(173);
				match(Then);
				setState(174);
				((If_expressionContext)_localctx).arr = array();
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).arr.ast);
				}
				}
				break;
			case 3:
				{
				{
				setState(177);
				match(Then);
				setState(178);
				((If_expressionContext)_localctx).condIsTrue = expression(0);
				setState(179);
				match(Else);
				setState(180);
				((If_expressionContext)_localctx).condIsFalse = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).condIsTrue.ast, ((If_expressionContext)_localctx).condIsFalse.ast, AST.IfExpr.Type.IfThen);
				}
				}
				break;
			case 4:
				{
				{
				setState(183);
				match(Then);
				setState(184);
				((If_expressionContext)_localctx).condIsTrue = expression(0);
				((If_expressionContext)_localctx).ast =  new AST.IfExpr(((If_expressionContext)_localctx).cond.ast, ((If_expressionContext)_localctx).condIsTrue.ast, null, AST.IfExpr.Type.IfThen);
				}
				}
				break;
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

	public static class For_expressionContext extends ParserRuleContext {
		public AST.ForExpr ast;
		public ExpressionContext var;
		public ExpressionContext fromExp;
		public ExpressionContext toExp;
		public ExpressionContext stepExp;
		public ExpressionContext doExp;
		public ExpressionContext arr;
		public TerminalNode For() { return getToken(ExpressionParser.For, 0); }
		public TerminalNode From() { return getToken(ExpressionParser.From, 0); }
		public TerminalNode To() { return getToken(ExpressionParser.To, 0); }
		public TerminalNode Step() { return getToken(ExpressionParser.Step, 0); }
		public TerminalNode Do() { return getToken(ExpressionParser.Do, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public For_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFor_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFor_expression(this);
		}
	}

	public final For_expressionContext for_expression() throws RecognitionException {
		For_expressionContext _localctx = new For_expressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_for_expression);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(189);
				match(For);
				setState(190);
				((For_expressionContext)_localctx).var = expression(0);
				setState(191);
				match(From);
				setState(192);
				((For_expressionContext)_localctx).fromExp = expression(0);
				setState(193);
				match(To);
				setState(194);
				((For_expressionContext)_localctx).toExp = expression(0);
				setState(195);
				match(Step);
				setState(196);
				((For_expressionContext)_localctx).stepExp = expression(0);
				setState(197);
				match(Do);
				setState(198);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForVarExpr(((For_expressionContext)_localctx).var.ast, ((For_expressionContext)_localctx).fromExp.ast, ((For_expressionContext)_localctx).toExp.ast, ((For_expressionContext)_localctx).stepExp.ast, ((For_expressionContext)_localctx).doExp.ast);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(201);
				match(For);
				setState(202);
				((For_expressionContext)_localctx).var = expression(0);
				setState(203);
				match(From);
				setState(204);
				((For_expressionContext)_localctx).fromExp = expression(0);
				setState(205);
				match(To);
				setState(206);
				((For_expressionContext)_localctx).toExp = expression(0);
				setState(207);
				match(Do);
				setState(208);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForVarExpr(((For_expressionContext)_localctx).var.ast, ((For_expressionContext)_localctx).fromExp.ast, ((For_expressionContext)_localctx).toExp.ast, null, ((For_expressionContext)_localctx).doExp.ast);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(211);
				match(For);
				setState(212);
				((For_expressionContext)_localctx).arr = expression(0);
				setState(213);
				match(Do);
				setState(214);
				((For_expressionContext)_localctx).doExp = expression(0);
				((For_expressionContext)_localctx).ast =  new AST.ForArrExpr(((For_expressionContext)_localctx).arr.ast, ((For_expressionContext)_localctx).doExp.ast);
				}
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

	public static class ArrayContext extends ParserRuleContext {
		public AST.Array ast;
		public List<AST.Expr> items;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public TerminalNode LBracket() { return getToken(ExpressionParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(ExpressionParser.RBracket, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ExpressionParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ExpressionParser.Comma, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_array);
		((ArrayContext)_localctx).items =  new ArrayList<>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(LBracket);
			setState(220);
			((ArrayContext)_localctx).e1 = expression(0);
			_localctx.items.add(((ArrayContext)_localctx).e1.ast);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(222);
				match(Comma);
				setState(223);
				((ArrayContext)_localctx).e2 = expression(0);
				_localctx.items.add(((ArrayContext)_localctx).e2.ast);
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			match(RBracket);
			((ArrayContext)_localctx).ast =  new AST.Array(_localctx.items);
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

	public static class Int_valueContext extends ParserRuleContext {
		public Integer i;
		public Token il;
		public Token hl;
		public TerminalNode IntegerLiteral() { return getToken(ExpressionParser.IntegerLiteral, 0); }
		public TerminalNode HexLiteral() { return getToken(ExpressionParser.HexLiteral, 0); }
		public Int_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterInt_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitInt_value(this);
		}
	}

	public final Int_valueContext int_value() throws RecognitionException {
		Int_valueContext _localctx = new Int_valueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_int_value);
		try {
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				((Int_valueContext)_localctx).il = match(IntegerLiteral);
				((Int_valueContext)_localctx).i =  new Integer((((Int_valueContext)_localctx).il!=null?((Int_valueContext)_localctx).il.getText():null));
				}
				break;
			case HexLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				((Int_valueContext)_localctx).hl = match(HexLiteral);
				((Int_valueContext)_localctx).i =  new Integer(Integer.decode((((Int_valueContext)_localctx).hl!=null?((Int_valueContext)_localctx).hl.getText():null)));
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

	public static class Float_valueContext extends ParserRuleContext {
		public Double d;
		public Token fl;
		public TerminalNode FloatLiteral() { return getToken(ExpressionParser.FloatLiteral, 0); }
		public Float_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFloat_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFloat_value(this);
		}
	}

	public final Float_valueContext float_value() throws RecognitionException {
		Float_valueContext _localctx = new Float_valueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_float_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			((Float_valueContext)_localctx).fl = match(FloatLiteral);
			((Float_valueContext)_localctx).d =  new Double((((Float_valueContext)_localctx).fl!=null?((Float_valueContext)_localctx).fl.getText():null));
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
		case 4:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u00f6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\7\2!\n\2\f\2\16\2$\13\2"+
		"\3\2\3\2\5\2(\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\62\n\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5=\n\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6U\n\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6\177\n\6\f\6\16\6\u0082\13\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0094\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a8\n\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\5\n\u00be\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u00dc\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\7\f\u00e5\n\f\f\f\16\f\u00e8\13\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00f1"+
		"\n\r\3\16\3\16\3\16\3\16\2\3\n\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3"+
		"\3\2\35\"\2\u0107\2\"\3\2\2\2\4\61\3\2\2\2\6\63\3\2\2\2\b8\3\2\2\2\nT"+
		"\3\2\2\2\f\u0093\3\2\2\2\16\u0095\3\2\2\2\20\u00a7\3\2\2\2\22\u00a9\3"+
		"\2\2\2\24\u00db\3\2\2\2\26\u00dd\3\2\2\2\30\u00f0\3\2\2\2\32\u00f2\3\2"+
		"\2\2\34\35\5\4\3\2\35\36\7$\2\2\36\37\b\2\1\2\37!\3\2\2\2 \34\3\2\2\2"+
		"!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#%\3\2\2\2$\"\3\2\2\2%\'\5\4\3\2&(\7$"+
		"\2\2\'&\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\b\2\1\2*\3\3\2\2\2+,\5\6\4\2,-"+
		"\b\3\1\2-\62\3\2\2\2./\5\n\6\2/\60\b\3\1\2\60\62\3\2\2\2\61+\3\2\2\2\61"+
		".\3\2\2\2\62\5\3\2\2\2\63\64\7%\2\2\64\65\7#\2\2\65\66\5\n\6\2\66\67\b"+
		"\4\1\2\67\7\3\2\2\28<\7\6\2\29:\5\2\2\2:;\b\5\1\2;=\3\2\2\2<9\3\2\2\2"+
		"<=\3\2\2\2=>\3\2\2\2>?\7\7\2\2?@\b\5\1\2@\t\3\2\2\2AB\b\6\1\2BC\5\f\7"+
		"\2CD\b\6\1\2DU\3\2\2\2EF\5\16\b\2FG\b\6\1\2GU\3\2\2\2HI\5\20\t\2IJ\b\6"+
		"\1\2JU\3\2\2\2KL\5\22\n\2LM\b\6\1\2MU\3\2\2\2NO\5\24\13\2OP\b\6\1\2PU"+
		"\3\2\2\2QR\5\b\5\2RS\b\6\1\2SU\3\2\2\2TA\3\2\2\2TE\3\2\2\2TH\3\2\2\2T"+
		"K\3\2\2\2TN\3\2\2\2TQ\3\2\2\2U\u0080\3\2\2\2VW\f\16\2\2WX\7\r\2\2XY\5"+
		"\n\6\17YZ\b\6\1\2Z\177\3\2\2\2[\\\f\r\2\2\\]\7\f\2\2]^\5\n\6\16^_\b\6"+
		"\1\2_\177\3\2\2\2`a\f\f\2\2ab\7\n\2\2bc\5\n\6\rcd\b\6\1\2d\177\3\2\2\2"+
		"ef\f\13\2\2fg\7\13\2\2gh\5\n\6\fhi\b\6\1\2i\177\3\2\2\2jk\f\n\2\2kl\t"+
		"\2\2\2lm\5\n\6\13mn\b\6\1\2n\177\3\2\2\2op\f\b\2\2pq\7\22\2\2qr\5\n\6"+
		"\trs\b\6\1\2s\177\3\2\2\2tu\f\7\2\2uv\7\21\2\2vw\5\n\6\bwx\b\6\1\2x\177"+
		"\3\2\2\2yz\f\6\2\2z{\7\27\2\2{|\5\n\6\7|}\b\6\1\2}\177\3\2\2\2~V\3\2\2"+
		"\2~[\3\2\2\2~`\3\2\2\2~e\3\2\2\2~j\3\2\2\2~o\3\2\2\2~t\3\2\2\2~y\3\2\2"+
		"\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\13\3\2\2"+
		"\2\u0082\u0080\3\2\2\2\u0083\u0084\7\n\2\2\u0084\u0085\5\16\b\2\u0085"+
		"\u0086\b\7\1\2\u0086\u0094\3\2\2\2\u0087\u0088\7\n\2\2\u0088\u0089\5\20"+
		"\t\2\u0089\u008a\b\7\1\2\u008a\u0094\3\2\2\2\u008b\u008c\7\13\2\2\u008c"+
		"\u008d\5\16\b\2\u008d\u008e\b\7\1\2\u008e\u0094\3\2\2\2\u008f\u0090\7"+
		"\13\2\2\u0090\u0091\5\20\t\2\u0091\u0092\b\7\1\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0083\3\2\2\2\u0093\u0087\3\2\2\2\u0093\u008b\3\2\2\2\u0093\u008f\3\2"+
		"\2\2\u0094\r\3\2\2\2\u0095\u0096\7\16\2\2\u0096\u0097\5\n\6\2\u0097\u0098"+
		"\7\17\2\2\u0098\u0099\b\b\1\2\u0099\17\3\2\2\2\u009a\u009b\7%\2\2\u009b"+
		"\u00a8\b\t\1\2\u009c\u009d\5\30\r\2\u009d\u009e\b\t\1\2\u009e\u00a8\3"+
		"\2\2\2\u009f\u00a0\5\32\16\2\u00a0\u00a1\b\t\1\2\u00a1\u00a8\3\2\2\2\u00a2"+
		"\u00a3\7\3\2\2\u00a3\u00a8\b\t\1\2\u00a4\u00a5\5\26\f\2\u00a5\u00a6\b"+
		"\t\1\2\u00a6\u00a8\3\2\2\2\u00a7\u009a\3\2\2\2\u00a7\u009c\3\2\2\2\u00a7"+
		"\u009f\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a8\21\3\2\2"+
		"\2\u00a9\u00aa\7\23\2\2\u00aa\u00bd\5\n\6\2\u00ab\u00ac\7\26\2\2\u00ac"+
		"\u00ad\5\n\6\2\u00ad\u00ae\b\n\1\2\u00ae\u00be\3\2\2\2\u00af\u00b0\7\24"+
		"\2\2\u00b0\u00b1\5\26\f\2\u00b1\u00b2\b\n\1\2\u00b2\u00be\3\2\2\2\u00b3"+
		"\u00b4\7\24\2\2\u00b4\u00b5\5\n\6\2\u00b5\u00b6\7\25\2\2\u00b6\u00b7\5"+
		"\n\6\2\u00b7\u00b8\b\n\1\2\u00b8\u00be\3\2\2\2\u00b9\u00ba\7\24\2\2\u00ba"+
		"\u00bb\5\n\6\2\u00bb\u00bc\b\n\1\2\u00bc\u00be\3\2\2\2\u00bd\u00ab\3\2"+
		"\2\2\u00bd\u00af\3\2\2\2\u00bd\u00b3\3\2\2\2\u00bd\u00b9\3\2\2\2\u00be"+
		"\23\3\2\2\2\u00bf\u00c0\7\30\2\2\u00c0\u00c1\5\n\6\2\u00c1\u00c2\7\31"+
		"\2\2\u00c2\u00c3\5\n\6\2\u00c3\u00c4\7\32\2\2\u00c4\u00c5\5\n\6\2\u00c5"+
		"\u00c6\7\33\2\2\u00c6\u00c7\5\n\6\2\u00c7\u00c8\7\34\2\2\u00c8\u00c9\5"+
		"\n\6\2\u00c9\u00ca\b\13\1\2\u00ca\u00dc\3\2\2\2\u00cb\u00cc\7\30\2\2\u00cc"+
		"\u00cd\5\n\6\2\u00cd\u00ce\7\31\2\2\u00ce\u00cf\5\n\6\2\u00cf\u00d0\7"+
		"\32\2\2\u00d0\u00d1\5\n\6\2\u00d1\u00d2\7\34\2\2\u00d2\u00d3\5\n\6\2\u00d3"+
		"\u00d4\b\13\1\2\u00d4\u00dc\3\2\2\2\u00d5\u00d6\7\30\2\2\u00d6\u00d7\5"+
		"\n\6\2\u00d7\u00d8\7\34\2\2\u00d8\u00d9\5\n\6\2\u00d9\u00da\b\13\1\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00bf\3\2\2\2\u00db\u00cb\3\2\2\2\u00db\u00d5\3\2"+
		"\2\2\u00dc\25\3\2\2\2\u00dd\u00de\7\b\2\2\u00de\u00df\5\n\6\2\u00df\u00e6"+
		"\b\f\1\2\u00e0\u00e1\7\20\2\2\u00e1\u00e2\5\n\6\2\u00e2\u00e3\b\f\1\2"+
		"\u00e3\u00e5\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"\u00ea\7\t\2\2\u00ea\u00eb\b\f\1\2\u00eb\27\3\2\2\2\u00ec\u00ed\7&\2\2"+
		"\u00ed\u00f1\b\r\1\2\u00ee\u00ef\7+\2\2\u00ef\u00f1\b\r\1\2\u00f0\u00ec"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\31\3\2\2\2\u00f2\u00f3\7\'\2\2\u00f3"+
		"\u00f4\b\16\1\2\u00f4\33\3\2\2\2\17\"\'\61<T~\u0080\u0093\u00a7\u00bd"+
		"\u00db\u00e6\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}