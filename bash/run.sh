#!/bin/bash
llc data.ll
clang++ data.s builtin.s -o data -g
valgrind --leak-check=full ./data
rm data