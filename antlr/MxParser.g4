// This is a g4 file created for the Mx* Complier Program
// Created by Eternal-Fir

grammar MxParser;

import MxLexer;

program: program_main* EOF;
program_main: var_def | func_def | class_def;

//Difination
var_def : type (Identifier ('=' expression)?) (',' Identifier ('=' expression)?)* ';';
func_def : return_type? Identifier '(' param_group? ')' code_block;
class_def : CLASS Identifier '{' (var_def | func_def)* '}' ';';

sentence : code_block                                                                                  #block_Sent
         | var_def                                                                                     #var_def_Sent
         | IF '(' cond=expression ')' true_sent=sentence (ELSE false_sent=sentence)?                   #if_Sent
         | FOR '(' init=expression? ';' cond=expression? ';' move=expression? ')' repe_sent=sentence   #for_Sent
         | WHILE '(' cond=expression ')' repe_sent=sentence                                            #while_Sent
         | RETURN expression? ';'                                                                      #return_Sent
         | BREAK ';'                                                                                   #break_Sent_Sent
         | CONTINUE ';'                                                                                #continue_Sent
         | expression ';'                                                                              #expr_only_Sent
         | ';'                                                                                         #empty_Sent
         ;
code_block : '{' sentence* '}';

expression : base_exp                                                         #simple_Exp
           | expression '[' expression ']'                                    #array_Exp
           | expression '(' expression_group? ')'                             #func_Exp
           | expression '.' Identifier                                        #member_Exp
           | creation                                                         #new_Exp
           | expression op=('++' | '--')                                      #backself_Exp
           | <assoc=right> op=('++' | '--') expression                        #frontself_Exp
           | <assoc=right> op=('+' | '-') expression                          #prenumber_Exp
           | <assoc=right> op=('!' | '~') expression                          #prebinary_Exp
           | lexp=expression op=('*' | '/' | '%') rexp=expression             #mul_div_mod_Exp
           | lexp=expression op=('+' | '-') rexp=expression                   #add_sub_Exp
           | lexp=expression op=('<<' | '>>') rexp=expression                 #move_Exp
           | lexp=expression op=('<' | '>' | '<=' | '>=') rexp=expression     #compare_Exp
           | lexp=expression op=('==' | '!=') rexp=expression                 #ifsame_Exp
           | lexp=expression op='&' rexp=expression                           #and_Exp
           | lexp=expression op='^' rexp=expression                           #xor_Exp
           | lexp=expression op='|' rexp=expression                           #or_Exp
           | lexp=expression op='&&' rexp=expression                          #relation_and_Exp
           | lexp=expression op='||' rexp=expression                          #relation_or_Exp
           | <assoc=right> lexp=expression op='=' rexp=expression             #envalue_Exp
           ;

expression_group : expression (',' expression)*;

// 复杂的应优先匹配
creation : NEW base_type ('[' expression ']')+ ('['']' )*  #array_Creation
         | NEW base_type '(' ')'                           #class_Creation
         | NEW base_type                                   #simple_Creation
         ;

param_group : param (',' param)*;
param : type Identifier;

type : base_type('['']')*;
base_type : INT | BOOL | STRING | Identifier;
return_type : base_type | VOID;

literal : BoolTypeLiteral
        | IntTypeLiteral
        | NullTypeLiteral
        | StringTypeLiteral
        ;

base_exp : literal
        | '(' expression ')'
        | THIS
        | Identifier
        ;

// TODO: lambda?
