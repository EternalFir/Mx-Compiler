// This is a g4 file created for the Mx* Complier Program
// Created by Eternal-Fir

parser grammar MxParser;

options {
	tokenVocab = MxLexer;
}
//import MxLexer;

program: programSub* programMain programSub* EOF;
programSub: varDef | funcDef | classDef;
programMain: funcDefMain;

//Definition
varDef : type varDefSub (',' varDefSub)* ';';
varDefSub : Identifier ('=' expression)?;
funcDef : returnType? Identifier '(' paramGroup? ')' codeBlock ;
funcDefMain : INT MAIN '(' ')' codeBlock;
classDef : CLASS Identifier '{' (varDef | funcDef)* '}' ';';
lambdaDef : '[' ifAnd='&'? ']' ('(' parlist=paramGroup?')')? '->'  codeBlock '(' par=expressionGroup? ')';

sentence : codeBlock                                                                                 #blockSent
         | varDef                                                                                    #varDefSent
         | IF '(' cond=expression ')' trueSent=sentence (ELSE falseSent=sentence)?                   #ifSent
         | FOR '(' init=expression?  ';' cond=expression? ';' move=expression? ')' repeSent=sentence #forSent
         | WHILE '(' cond=expression ')' repeSent=sentence                                           #whileSent
         | RETURN expression? ';'                                                                    #returnSent
         | BREAK ';'                                                                                 #breakSent
         | CONTINUE ';'                                                                              #continueSent
         | expression ';'                                                                            #exprOnlySent
         | ';'                                                                                       #emptySent
         ;
codeBlock : '{' sentence* '}';

expression : baseExp                                                          #simpleExp
           | master=expression '[' index=expression ']'                       #arrayExp
           | expression '(' expressionGroup? ')'                              #funcExp
           | expression '.' Identifier                                        #memberExp
           | creation                                                         #newExp
           | lambdaDef                                                        #lambdaExp
           | expression op=('++' | '--')                                      #backselfExp
           | <assoc=right> op=('++' | '--') expression                        #frontselfExp
           | <assoc=right> op=('+' | '-') expression                          #preNumberExp
           | <assoc=right> op=('!' | '~') expression                          #preLogicNoExp
           | lexp=expression op=('*' | '/' | '%') rexp=expression             #binaryExp
           | lexp=expression op=('+' | '-') rexp=expression                   #binaryExp
           | lexp=expression op=('<<' | '>>') rexp=expression                 #binaryExp
           | lexp=expression op=('<' | '>' | '<=' | '>=') rexp=expression     #binaryExp
           | lexp=expression op=('==' | '!=') rexp=expression                 #binaryExp
           | lexp=expression op='&&' rexp=expression                          #binaryExp
           | lexp=expression op='||' rexp=expression                          #binaryExp
           | lexp=expression op='&' rexp=expression                           #binaryExp
           | lexp=expression op='^' rexp=expression                           #binaryExp
           | lexp=expression op='|' rexp=expression                           #binaryExp
           | <assoc=right> lexp=expression op='=' rexp=expression             #binaryExp
           ;

expressionGroup : expression (',' expression)*;

// 复杂的应优先匹配
creation : NEW baseType ('[' expression ']')+ ('['']' )*  #arrayCreation
         | NEW baseType '(' ')'                           #classCreation
         | NEW baseType                                   #simpleCreation
         ;

paramGroup : param (',' param)*;
param : type Identifier;

type : baseType('['']')*;
baseType : INT | BOOL | STRING | Identifier;
returnType : type | VOID;

literal : BoolTypeLiteral
        | IntTypeLiteral
        | NullTypeLiteral
        | StringTypeLiteral
        ;

baseExp : '(' expression ')'
        | THIS
        | Identifier
        | literal
        ;

identifier: Identifier | 'main';
