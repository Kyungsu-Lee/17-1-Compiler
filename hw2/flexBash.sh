#!/bin/bash

flex $1
gcc lex.yy.c -o $1".out" -lfl
rm lex.yy.c
echo "finished"
