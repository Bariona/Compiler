grammar Mx;

program : def* EOF;

suite : '{' statement* '}';

statement : suite                                               # SuiteStmt
    | def                                                       # DefStmt
    | If '(' expression ')' statement
        (Else statement)?                                       # IfElseStmt
    | Break ';'                                                 # BreakStmt
    | Continue ';'                                              # ContinueStmt
    | Return expression? ';'                                    # ReturnStmt
    | While '(' expression ')' statement                        # WhileStmt
    | For '(' (initialExpr = expression | varDef Semi)? ';' (condiExpr = expression)? ';' (stepExpr = expression)? ')' statement    # ForStmt
    | expression ';'                                            # PurExprStmt
//    | sysfunction                                               # Sysyemfunc
    | ';'                                                       # EmptyStmt
    ;

typeName    : (Int | Bool | String | Void | Identifier) bracket*;
bracket : '[' expression? ']';

def     : varDef ';'                        # VarDefinition
        | classDef                          # ClassDefinition
        | functionDef                       # FunctDefinition
        ;

varDef  : typeName varTerm (',' varTerm)*; // include array & jagged array
varTerm : Identifier ('=' expression)?;

functionDef   : typeName? Identifier '(' parameterList? ')' suite;
parameterList : typeName Identifier (',' typeName Identifier)*;

classDef    :   Class Identifier '{' (varDef ';' | functionDef)* '}' ';' ;

//sysfunction   : 'print' '(' expression ')'  # PrintStr
//    | 'println' '(' expression ')'          # PrintlnStr
//    | 'printInt' '(' expression ')'         # PrintInt
//    | 'printlnInt' '(' expression ')'       # PrintlnInt
//    | 'getString' '(' ')'                   # getString
//    | 'getInt' '(' ')'                      # getInt
//    | 'toString' '(' expression ')'         # toString
//    | 'length' '(' ')'                      # StrLength
//    | 'substring' '(' expression ',' expression ')' # StrSubstr
//    | 'parseInt' '(' ')'                    # StrtoInt
//    | 'ord' '(' expression ')'              # StrtoASCii
//    ;

argumentList   :  expression (',' expression)*;

expression : primary                                # AtomExpr
    | '(' expression ')'                            # ParenExpr
//    | sysfunction                                   # SystemFunc
    // this can be written as Identifier, with 'a.size()' seen as FunctionExpr -> MemberExpr
    | expression '.' Identifier                     # MemberExpr
    | expression '[' expression ']'                 # BracketExpr
    | expression '(' argumentList? ')'              # FunctionExpr
    | expression op = ('--' | '++')                 # SelfExpr
    | <assoc=right> op = ('++' | '--') expression   # UnaryExpr
    | <assoc=right> op = ('+' | '-') expression     # UnaryExpr
    | <assoc=right> op = ('!' | '~') expression     # UnaryExpr
    | <assoc=right> New typeName                    # Newtype

    | expression op = ('*' | '/' | '%') expression              # BinaryExpr
    | expression op = ('+' | '-') expression                    # BinaryExpr
    | expression op = ('<<' | '>>') expression                  # BinaryExpr
    | expression op = ('<' | '<=' | '>' | '>=') expression      # BinaryExpr

    | expression op = ('==' | '!=') expression  # BinaryExpr
    | expression op = '&' expression            # BinaryExpr
    | expression op = '^' expression            # BinaryExpr
    | expression op = '|' expression            # BinaryExpr

    | expression op = '&&' expression           # BinaryExpr
    | expression op = '||' expression           # BinaryExpr

    | <assoc=right> expression '=' expression   # AssignExpr

    | '[' '&'? ']' ('(' parameterList? ')')? '->' suite '(' argumentList? ')'     # LambdaExpr
    ;

primary : This
    | Null
    | (True | False)
    | Identifier
    | Decimal
    | StringConst
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

Arrow   : '->';

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