%{
	#include "expr.tab.h"
%}

%%

([0-9])+	{
	yylval = atoi(yytext);
	return DIGIT;
}

[ ]	{
}

[\n]	{
	return '\n';
}

[R]	{
	return DIGIT;
}

[+]|[-]|[*]	{
	return yytext[0];
}

.	{
}
