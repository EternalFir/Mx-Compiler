// This is a g4 file created for the Mx* Complier Program
// Created by Eternal-Fir

lexer grammar MxLexer;

fragment ALPHABATE : [A-Za-z];

fragment DIGIT : [0-9];

fragment NONZERODIGIT : [1-9];

fragment OCTALDIGIT : [0-7];

fragment HEXADECIMALDIGIT : [0-9a-fA-F];

fragment BINARYDIGIT : [01];

// TypeLiterals
BoolTypeLiteral : TRUE|FALSE;
IntTypeLiteral : (('-')?NONZERODIGIT DIGIT*)|'0';
UnsignedIntTypeLiteral : (NONZERODIGIT DIGIT*);
NullTypeLiteral : NULL;
StringTypeLiteral : '"' ('\\"' | '\\\\' | '\\n' | ~[\\])*? '"';

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

//Keywords : VOID|BOOL|INT|STRING|NEW|CLASS|NULL|TRUE|FALSE|THIS|IF|ELSE|FOR|WHILE|BREAK|CONTINUE|RETURN;

// 5.Idetifier
Identifier : ([a-zA-Z][a-zA-Z0-9_]*);

// Punctuation
LEFT_PARENTHESIS : '(';
RIGHT_PARENTHESIS : ')';
LEFT_SQUAREBRACKETS : '[';
RIGHT_SQUAREBRACKETS : ']';
LEFT_BRACKETS : '{';
RIGHT_BRACKETS : '}';
COLON : ':';
SEMICOLON : ';';
COMMA : ',';
DOT : '.';
ARROW : '->';
SELFADD : '++';
SELFSUB : '--';
MUL : '*';
DIV : '/';
MOD : '%';
ADD : '+';
SUB : '-';
LOGIC_NO : '!';
LOGIC_AND : '&&';
LOGIC_OR : '||';
BITWISE_NO : '~';
BITWISE_MOVE_LEFT : '<<';
BITWISE_MOVE_RIGHT : '>>';
BITWISE_AND : '&';
BITWISE_OR : '|';
BITWISE_XOR : '^';
GREATER_EQUARE : '>=';
LESS_EQUARE : '<=';
GREATER : '>';
LESS : '<';
EQUARE : '==';
UNEQUARE : '!=';
ENVALUE : '=';




// 3.BlankWords
WhiteSpace : (' '|'\t')+ -> skip;
NewLine : ('\r\n'|'\n') -> skip;

// 4.Comments
LineComment : '//' .*? '\r'? '\n' -> skip;
BlockComment : '/*' .*? '*/' -> skip;


