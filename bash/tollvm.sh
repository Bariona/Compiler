#!/bin/bash
path="../testspace"
echo "Compiling $1 into .ll file"
file=$1
filename=${file%.*}
clang -S -emit-llvm -O0 -fno-discard-value-names $path/$1 -o $path/$filename.ll
