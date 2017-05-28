%{
#include <stdio.h>
#include <ctype.h>
%}

TYPE void|int|char|short|long|boolean|enum
MOD public|private|protected
FS final|static
LOOP for|while
OJ class|interface

WP ([ ]|\t)
BR ([{]|[;]|[(])

%%
	FILE* file;


({TYPE}|{MOD}|{FS}|{OJ})({WP})+ {

	file = fopen("result", "a");
	fprintf(file, "<b>%s</b>", yytext);
	printf("<b>%s</b>", yytext);
	fclose(file);
}

[[:alpha:][:digit:]]({TYPE}|{MOD}|{FS}|{OJ}){WP} {
	file = fopen("result", "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}  
({WP}|{BR})+({TYPE}|{MOD}|{FS}|{OJ}|return)({WP})+ {
	file = fopen("result", "a");
               char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
               strncpy(tmp, yytext + 1, yyleng - 2);
               fprintf(file, "%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
               printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
            }

({WP}|{BR}){LOOP}({WP}|[(])   {
	file = fopen("result", "a");
               char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
               strncpy(tmp, yytext + 1, yyleng - 2);
               fprintf(file, "%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
               printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
		fclose(file);
            }
\n	{
	file = fopen("result", "a");
	fprintf(file, "\n");
	printf("\n");
	fclose(file);
}

.	{
	file = fopen("result", "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}

%%


main( argc, argv )
	int argc;
	char **argv;
{
	++argv, --argc;  /* skip over program name */
	FILE* fp = fopen("result", "w");
	fclose(fp);
	if ( argc > 0 )
		yyin = fopen( argv[0], "r" );
	else
	yyin = stdin;
	yylex(); 

}
