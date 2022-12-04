#!/bin/bash
# clang -S -emit-llvm -O0 -fno-discard-value-names 
# echo `pwd`
path="../testspace"
llc ${path}/data.ll
# clang ${path}/data.s