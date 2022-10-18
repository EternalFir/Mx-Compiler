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
varDef : type (Identifier ('=' expression)?) (',' Identifier ('=' expression)?)* ';';
funcDef : returnType? Identifier '(' paramGroup? ')' codeBlock ;
funcDefMain : INT MAIN '(' ')' codeBlock;
classDef : CLASS Identifier '{' (varDef | funcDef)* '}' ';';
lambdaDef : '[' ifAnd='&'? ']' ('(' parlist=paramGroup?')')? '->'  codeBlock '(' par=expressionGroup? ')';

sentence : codeBlock                                                                                 #blockSent
         | varDef                                                                                    #varDefSent
         | IF '(' cond=expression ')' trueSent=sentence (ELSE falseSent=sentence)?                   #ifSent
         | FOR '(' init=expression? ';' cond=expression? ';' move=expression? ')' repeSent=sentence  #forSent
         | WHILE '(' cond=expression ')' repeSent=sentence                                           #whileSent
         | RETURN expression? ';'                                                                    #returnSent
         | BREAK ';'                                                                                 #breakSent
         | CONTINUE ';'                                                                              #continueSent
         | expression ';'                                                                            #exprOnlySent
         | ';'                                                                                       #emptySent
         ;
codeBlock : '{' sentence* '}';

expression : baseExp                                                          #simpleExp
           | expression '[' expression ']'                                    #arrayExp
           | expression '(' expressionGroup? ')'                              #funcExp
           | expression '.' Identifier                                        #memberExp
           | creation                                                         #newExp
           | lambdaDef                                                        #lambdaExp
           | expression op=('++' | '--')                                      #backselfExp
           | <assoc=right> op=('++' | '--') expression                        #frontselfExp
           | <assoc=right> op=('+' | '-') expression                          #prenumberExp
           | <assoc=right> op='!' expression                                  #logicNoExp
           | <assoc=right> op='~' expression                                  #bitwiseNoExp
           | lexp=expression op=('*' | '/' | '%') rexp=expression             #mulDivModExp
           | lexp=expression op=('+' | '-') rexp=expression                   #addSubExp
           | lexp=expression op=('<<' | '>>') rexp=expression                 #bitwiseMoveExp
           | lexp=expression op=('<' | '>' | '<=' | '>=') rexp=expression     #compareExp
           | lexp=expression op=('==' | '!=') rexp=expression                 #isEquareExp
           | lexp=expression op='&&' rexp=expression                          #logicAndExp
           | lexp=expression op='||' rexp=expression                          #logicOrExp
           | lexp=expression op='&' rexp=expression                           #bitwiseAndExp
           | lexp=expression op='^' rexp=expression                           #bitwiseXorExp
           | lexp=expression op='|' rexp=expression                           #bitwiseOrExp
           | <assoc=right> lexp=expression op='=' rexp=expression             #envalueExp
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
returnType : baseType | VOID;

literal : BoolTypeLiteral
        | IntTypeLiteral
        | NullTypeLiteral
        | StringTypeLiteral
        ;

baseExp : literal
        | '(' expression ')'
        | THIS
        | Identifier
        ;
