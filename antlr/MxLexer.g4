// This is a g4 file created for the Mx* Complier Program
// Created by Eternal-Fir

lexer grammar MxLexer;

// 5.Idetifier
Identifier : [a-zA-Z][a-zA-Z0-9_]*;

fragment
ALPHABATE : [A-Za-z];
NUMBER : [0-9];
NUMBER_NOZERO : [1:9];

// Types
BoolType : TRUE|FALSE;
IntType : (('-')?NUMBER_NOZERO NUMBER*)|'0';
VoidType : VOID;
StringType : '"'('//"'|'////'|.)*?{0,255}'"';

// 2.Keywords
VOID : 'void';
BOOL : 'bool';
INT : 'int';
STRING : 'string';
NEW : 'new';
CLASS : 'class';
NULL : 'null';
TRUE : 'true';
FALSE : 'false';
THIS : 'this';
IF : 'if';
ELSE : 'else';
FOR : 'for';
WHILE : 'while';
BREAK : 'break';
CONTINUE : 'continue';
RETURN : 'return';

// 3.BlankWords
WhiteSpace : (' '|'\t')+ -> skip;
NewLine : ('\r''\t'|'\t') -> skip;

// 4.Comments
LineComment : '//' .*? '\r'? '\n' -> skip;
BlockComment : '/*' .*? '*/' -> skip;