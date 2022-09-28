grammar Mx;

program : def* EOF;

suite : '{' statement* '}';

statement : suite                                               # SuiteStmt
    | def                                                       # Definition
    | If '(' expression ')' statement
        (Else statement)?                                       # If_Else
    | Break ';'                                                 # Break
    | Continue ';'                                              # Continue
    | Return expression? ';'                                    # Return
    | While '(' expression ')' statement                        # WhileStmt
    | For '(' (initialExpr = expression | varDef Semi)? ';' (condiExpr = expression)? ';' (stepExpr = expression)? ')' statement    #ForStmt
    | expression ';'                                            # Purexpression
    | systemfuc                                                 # Sysyemfunc
    | ';'                                                       # Emptyexpression
    ;

typeName    : (Int | Bool | String | Void | Identifier) bracket*;
bracket : '[' expression? ']';

def     : varDef ';'                        # VarDefi
        | classDef                          # ClassDefi
        | functionDef                       # FunctDefi
        ;

varDef  : typeName varTerm (',' varTerm)*      # VariableDef
//        | typeName Identifier ('=' New typeName '['Decimal']' ('[' Decimal? ']')*)?      # ArrayDef
//        | Identifier '['Decimal']' '=' (Null | (New typeName '['Decimal']'))             # Jagged
        ;
varTerm : Identifier ('=' expression)?;

classDef    :   Class Identifier '{' // whether here to use brackets to avoid mismatch?
        (
            typeName Identifier
            |  typeName Identifier (',' Identifier)*
            |  Identifier'(' ')' '{' (expression | statement)* '}'
            |  functionDef
        )*
        '}' ';'
        ;

functionDef :  typeName Identifier '(' funcParList? ')' suite;
funcParList : typeName Identifier | (',' typeName Identifier)*;
parameterList   :  expression | (',' expression)*;

systemfuc   : 'print' '(' expression ')'    # PrintStr
    | 'println' '(' expression ')'          # PrintlnStr
    | 'printInt' '(' expression ')'         # PrintInt
    | 'printlnInt' '(' expression ')'       # PrintlnInt
    | 'getString' '(' ')'                   # getString
    | 'getInt' '(' ')'                      # getInt
    | 'toString' '(' expression ')'         # toString
    ;

expression : primary                                # AtomExpr
    | systemfuc                                     # SystemFunc
    | expression '.' Identifier                     # MemberExpr
    | expression '[' expression ']'                 # BracketExpr
    | expression '(' parameterList? ')'              # FunctionExpr
    | <assoc=right> op = ('++' | '--') expression   # SelfExpr
    | <assoc=right> expression op = ('--' | '++')   # SelfExpr
    | <assoc=right> '!' expression              # UnaryExpr
    | <assoc=right> '~' expression              # UnaryExpr
    | <assoc=right> '-' expression              # UnaryExpr
    | <assoc=right> New typeName                # Newtype

    | expression op = ('*' | '/' | '%') expression      # BinaryExpr
    | expression op = ('+' | '-') expression            # BinaryExpr
    | expression op = ('<<' | '>>') expression          # BinaryExpr
    | expression op = ('<' | '<=' | '>' | '>=') expression   # BinaryExpr

    | expression op = ('==' | '!=') expression  # BinaryExpr
    | expression op = '&' expression            # BinaryExpr
    | expression op = '^' expression            # BinaryExpr
    | expression op = '|' expression            # BinaryExpr

    | expression op = '&&' expression           # BinaryExpr
    | expression op = '||' expression           # BinaryExpr

    | <assoc=right> expression '=' expression   # AsignExpr
    ;

primary : '(' expression ')'
    | This
    | (True | False)
    | Identifier
    | Decimal
    | StringConst
    | Null
    ;

// ----------- Lexer Part Definitions -------------

Void    : 'void';
Bool    : 'bool';
Int : 'int';

New : 'new';
Class   : 'class';
Null    : 'null';
This    : 'this';

For     : 'for';
While   : 'while';
Break   : 'break';
Continue    : 'continue';

True    : 'true';
False   : 'false';

If  : 'if';
Else    : 'else';

Return  : 'return';

String  : 'string';
StringConst : '"' (ESC | .)*? '"';
ESC : '\\"' | '\\\\';

Dot : '.';
LeftParen   : '(';
RightParen  : ')';
LeftBracket     : '[';
RightBracket    : ']';
LeftBrace   : '{';
RightBrace  : '}';

Add     : '+';
Sub     : '-';
Mul     : '*';
Div     : '/';
Mod     : '%';

Less    : '<';
Leq     : '<=';
Greater : '>';
Geq     : '>=';
Equal   : '==';
NotEqual    : '!=';

LogicAnd    : '&&';
LogicOr     : '||';
LogicNot    : '!';

LeftShift   : '<<';
RightShift  : '>>';

And     : '&';
Or      : '|';
Xor     : '^';
Not     : '~';

Assign  : '=';
SelfAdd : '++';
SelfSub : '--';

Semi    : ';';
Colon   : ':';
Question    : '?';

Identifier  : [a-zA-Z][a-zA-Z0-9_]*;
Decimal : [1-9][0-9]* | '0';

WhiteSpace  : [ \t] -> skip; // skip space & tab
NewLine     : ('\r\n' | '\n') -> skip;
BlockComment    : '/*' .*? '*/' -> skip;
LineComment     : '//' .*? '\r'? '\n' -> skip;