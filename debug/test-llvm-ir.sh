llvm-link test.ll builtin.ll -o link.bc
llvm-dis link.bc
clang++ link.bc -o test
./test