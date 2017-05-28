%{
#include <ctype.h>
#include <stdio.h>
%}

%token DIGIT
%token RR


%%
line	: expr '\n'		{printf("%d\n", $1);}
	| line expr '\n' 	{printf("%d\n", $2);}
	;
expr	: expr '+' term		{$$ = $1 + $3;}
	| term
	| '-' factor		{$$ = -$2;}
	;
term	: term '*' factor	{$$ = $1 * $3;}
	| factor
	;
factor	: '(' expr ')'		{$$ = $2;}
	| DIGIT
	;
%%

yylex()	{
	int c;
	c= getchar();
	if(isdigit(c)){
		yylval = c - '0';
		return DIGIT;
	}
	return add(c);}

int
add(int n)
{
	printf("::%c\n", n);
	return n;
}
