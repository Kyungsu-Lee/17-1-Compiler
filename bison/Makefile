all	: flex.o bison.o expr.tab.h
	gcc lex.yy.c expr.tab.c -lfl -ly
	rm lex.yy.c *tab*

flex.o	: expr_lex.l
	flex expr_lex.l

bison.o	: expr.y
	bison -d expr.y
