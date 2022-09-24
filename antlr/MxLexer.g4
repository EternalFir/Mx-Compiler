// This is a g4 file created for the Mx* Complier Program
// Created by Eternal-Fir

lexer grammar MxLexer;

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

// 4.Comments
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip;
COMMENT : '/*' .*? '*/' -> skip;

// 3.BlankWords
WS : (' '|'\t'|'\r'|'\n')+ -> skip;