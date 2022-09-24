grammar Yx;

program : 'int main()' suite EOF;

suite : '{' statement* '}';
varDef  : Int Identifier ('=' expression)? ';';

statement : suite                       # block
    | varDef                            # VarDefinition
    | If '(' expression ')' ifstmt = statement
        (Else flstmt = statement)?      # If_Else
    | Return expression? ';'            # return
    | expression                        # Purexpression
    | ';'                               # Emptyexpression
    ;

expression : primary                            # AtomExpr
    | expression op = ('+' | '-') expression    # ArithExpr
    | expression op = ('==' | '!=') expression  # LogicExpr
    | <assoc=right> expression '=' expression   # AsignExpr
    ;

primary : '(' expression ')'
    | Identifier
    | literal
    ;

literal : Decimal;


Int : 'int';
If  : 'if';
Else    : 'else';
Return  : 'return';

LeftParen   : '(';
RightParen  : ')';
LeftBracket : '[';
RightBracket    :   ']';
LeftBrace   : '{';
RightBrace  : '}';

Less    : '<';
Leq     : '<=';
Greater : '>';
Geq     : '>=';
LeftShift   :   '<<';
RightShift  :   '>>';

Add     : '+';
Sub     : '-';
Mul     : '*';
Div     : '/';

And     : '&';
LogicAnd: '&&';
Or      : '|';
LogicOr : '||';
Xor     : '^';
Not     : '!';

Semi    : ';';
Colon   : ':';
Question: '?';

Assign  : '=';
Equal   : '==';
NotEqual: '!=';

Identifier  :   [a-zA-Z][a-zA-Z0-9_]*;
Decimal : [1-9][0-9]* | '0';

WhiteSpace  : [ \t] -> skip;
NewLine     : ('\r\n' | '\n') -> skip;
BlockComment    : '/*' .*? '*/' -> skip;
LineComment     : '//' ~[\r\n]* -> skip;