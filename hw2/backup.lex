%{
#include <stdio.h>
#include <ctype.h>
%}

NNB null|true|false
TYPE byte|void|int|char|short|long|boolean|float|double|enum
NUMTYPE byte|int|char|short|long|float|double|boolean
MOD public|private|protected
FS final|static|abstract
LOOP for|while
OJ class|interface
COND if|else{WP}if

VAR [[:alpha:]_$]([[:alpha:][:digit:]&_])*
OP ([+]|[-]|[*]|[/]|[%])
AD [[:alpha:][:digit:]]
WPE ([ ]|\t)*
WP ([ ]|\t)+
BR ([{]|[;])

%%
	FILE* file;
	char* output ="result.html";


 /*			this/super	 */
(this|super)({WP}|[(]|[.])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file, "<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
				}

{AD}(this|super)({WP}|[[]|[.])	{
	
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}
({WP}|{BR})(this|super)({WP}|[(]|[.])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}
  

	/* throws, throw		*/

([)]|{WP})throws{WP}	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

(throw){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(throw){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(throw){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}


	/* 	enum	 */
(enum){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(enum){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(enum){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

 /*   modifier   */
({MOD}){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}({MOD}){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})({MOD}){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

	/*  final	*/
(final){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(fianl){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR}|[(])(final){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

	/*	static	*/
(static){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(static){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR}|[(])(static){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}
	/*	class, interface	*/
({OJ}){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}({OJ}){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})({OJ}){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}
	/*	return 	*/
(return){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(return){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(return){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}
	/*	assert	*/
(assert){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(assert){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(assert){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

	/*	import	*/
(import){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(import){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(import){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

	/*	extends. implements	*/
	
({WP}|{BR})(extends|implements){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}


	/*   numeral type(like "int") and void*/
({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]]) {
	file = fopen(output, "a");
					char* locationOfBracket = strchr(yytext, '[');
					char *tmp = (char*)malloc(sizeof(char) * (locationOfBracket - yytext));
					strncpy(tmp, yytext, locationOfBracket - yytext);
					fprintf(file,"<b>%s</b>%s", tmp, locationOfBracket);
					printf("<b>%s</b>%s", tmp, locationOfBracket);
	fclose(file);
				}
{AD}({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]])  {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}
({WP}|{BR})({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]]) {
	file = fopen(output, "a");
					char* locationOfBracket = strchr(yytext, '[');
					char *tmp = (char*)malloc(sizeof(char) * (locationOfBracket - yytext - 1));
					strncpy(tmp, yytext + 1, locationOfBracket - yytext - 1);
					printf("%c<b>%s</b>%s", yytext[0], tmp, locationOfBracket);
					fprintf(file,"%c<b>%s</b>%s", yytext[0], tmp, locationOfBracket);
	fclose(file);
				}
(void){WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}(void){WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
({WP}|{BR})(void){WP} {
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

 /*  for, while */

({LOOP})({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
{AD}({LOOP})({WP}|[(])	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
} 
({WP}|{BR})({LOOP})({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}


	/*	if, else, else if	*/
({COND})({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
{AD}({COND})({WP}|[(])	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
} 
({WP}|{BR})({COND})({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}
(else)({WP}|[{]|\n)	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
{AD}(else)({WP}|[{]|\n)	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
} 
({WP}|{BR})(else)({WP}|[{]|\n)	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}


	/* catch, try */
(try)({WP}|[{]|\n)	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
{AD}(try)({WP}|[{]|\n)	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
} 
({WP}|{BR})(try)({WP}|[{]|\n)	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}

(catch)({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
					fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
{AD}(catch)({WP}|[(])	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
} 
({WP}|{BR})(catch)({WP}|[(])	{
	file = fopen(output, "a");
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
					fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
				}




	/*	new	*/

new{WP}	{
	file = fopen(output, "a");
	printf("<b>%s</b>", yytext);
	fprintf(file,"<b>%s</b>", yytext);
	fclose(file);
}
{AD}new{WP} {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}
(={WPE}|{WP})new{WP}	{
	file = fopen(output, "a");
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
				strncpy(tmp, yytext + 1, yyleng - 2);
				printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
			}
	/*	null	*/
(null)({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) 	{
	file = fopen(output, "a");
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
				strncpy(tmp, yytext, yyleng - 1);
				printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
				fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
	
{AD}(null)({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
(=|={WP})(null)({WPE}([;]|[&&]|[||]|[)]|[,]|[:]))	{
	file = fopen(output, "a");
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
				strncpy(tmp, yytext + 1, yyleng - 2);
				printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
			}


	/* true, false */

(true|false)({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) 	{
	file = fopen(output, "a");
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
				strncpy(tmp, yytext, yyleng - 1);
				printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
				fprintf(file,"<b>%s</b>%c", tmp, yytext[yyleng - 1]);
	fclose(file);
			}
	
{AD}(true|false)({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) {
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}	
(=|={WP})(true|false)({WPE}([;]|[&&]|[||]|[)]|[,]|[:]))	{
	file = fopen(output, "a");
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
				strncpy(tmp, yytext + 1, yyleng - 2);
				printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				fprintf(file,"%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
	fclose(file);
			}

	/* // comment */
[//](.)*$	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}
	/*	double quotation	*/
["](.)*["]	{
	file = fopen(output, "a");
	fprintf(file, "%s", yytext);
	printf("%s", yytext);
	fclose(file);
}

\n	{
	file = fopen(output, "a");
	fprintf(file, "<br>\n");
	printf("\n");
	fclose(file);
}

\t	{
	file = fopen(output, "a");
	fprintf(file, "&nbsp;&nbsp;&nbsp;&nbsp;");
	printf("&nbsp;&nbsp;&nbsp;&nbsp;");
	fclose(file);
}
.	{
	file = fopen(output, "a");
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
	char* output = "result.html";
	FILE* fp = fopen(output, "w");

	fprintf(fp, "<!DOCTYPE html>\n");
	fprintf(fp, "<html>\n");
	fprintf(fp, "<head></head>\n");
	fprintf(fp, "<body>\n");

	fclose(fp);
	if ( argc > 0 )
		yyin = fopen( argv[0], "r" );
	else
	yyin = stdin;
	yylex(); 

	fprintf(fp, "</body>\n</html>");

	fclose(fp);

}




