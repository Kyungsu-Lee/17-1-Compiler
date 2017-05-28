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
BR ([{]|[;]|[(])

%%

(this|super)({WP}|[(]|[.])	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
				}

{AD}(this|super)({WP}|[[]|[.])	ECHO;
({WP}|{BR})(this|super)({WP}|[(]|[.])	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				}
 

([)]|{WP})throws{WP}	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				}

({TYPE}|{MOD}|{FS}|{OJ}|return|assert|import|throw){WP}	printf("<b>%s</b>", yytext);
{AD}({TYPE}|{MOD}|{FS}|{OJ}|return|assert|import|throw){WP} ECHO;		
({WP}|{BR})({TYPE}|{MOD}|{FS}|{OJ}|return|assert|import|throw|extends|implements){WP} {
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				}


({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]]) {
					char* locationOfBracket = strchr(yytext, '[');
					char *tmp = (char*)malloc(sizeof(char) * (locationOfBracket - yytext));
					strncpy(tmp, yytext, locationOfBracket - yytext);
					printf("<b>%s</b>%s", tmp, locationOfBracket);
				}
{AD}({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]]) ECHO; 
({WP}|{BR})({NUMTYPE})({WPE}[[]({WPE}|{OP}|{AD}|[.])*[]]) {
					char* locationOfBracket = strchr(yytext, '[');
					char *tmp = (char*)malloc(sizeof(char) * (locationOfBracket - yytext - 1));
					strncpy(tmp, yytext + 1, locationOfBracket - yytext - 1);
					printf("%c<b>%s</b>%s", yytext[0], tmp, locationOfBracket);
				}


({LOOP}|{COND}|catch)({WP}|[(])	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
			}
{AD}({LOOP}|{COND}|catch)({WP}|[(])	ECHO; 
({WP}|{BR})({LOOP}|{COND}|catch)({WP}|[(])	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				}



(else|try)({WP}|[{]|\n)	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
					strncpy(tmp, yytext, yyleng - 1);
					printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
			}
{AD}(else|try)({WP}|[{]|\n)	ECHO; 
({WP}|{BR})(else|try)({WP}|[{]|\n)	{
					char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
					strncpy(tmp, yytext + 1, yyleng - 2);
					printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
				}


new{WP}	printf("<b>%s</b>", yytext);
{AD}new{WP} ECHO;
(={WPE}|{WP})new{WP}	{
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
				strncpy(tmp, yytext + 1, yyleng - 2);
				printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
			}
{NNB}({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) 	{
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 1));
				strncpy(tmp, yytext, yyleng - 1);
				printf("<b>%s</b>%c", tmp, yytext[yyleng - 1]);
			}
	
{AD}{NNB}({WPE}([;]|[&&]|[||]|[)]|[,]|[:])) ECHO;	
(=|={WP})({NNB})({WPE}([;]|[&&]|[||]|[)]|[,]|[:]))	{
				char *tmp = (char*)malloc(sizeof(char) * (yyleng - 2));
				strncpy(tmp, yytext + 1, yyleng - 2);
				printf("%c<b>%s</b>%c", yytext[0], tmp, yytext[yyleng - 1]);
			}


[//](.)*$	ECHO;
["](.)*["]	ECHO;


%%
void main(){
	yylex();
}



