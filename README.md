# Mx-Compiler
---

- [x] Current Progress: Optimization done...

:computer: [Course Link](https://github.com/ACMClassCourses/Compiler-Design-Implementation)

<div align=center> <img src="doc/static/200w.gif", width="250"> </div> 

- A language-processing system:

```mermaid
graph TB
S(source program)-->P[Preprocessor]
P--modified source program-->C[Compiler]
C--target assembly program-->A[Assembler]
A--relocatable machine code-->L[Linker/Loader]
li[library file <br> relocatable object files]-->L
L-->T(target machine code)
```

### Configuration

[IDEA Java environment](https://blog.csdn.net/brytlevson/article/details/106461319?spm=1001.2014.3001.5506)

[Set up Antlr4 on Win10](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md)

### Relevant commands

```
   antlr4 <filename>.g4 -visitor
   javac *.java
   grun <filename> <rulename> -tokens
   
   diff <file1> <file2> -qZB # regardless of space endl
```

compile java file:

```
javac <filename>.java 
javac -encoding UTF-8 <filename>.java # support Chinese comment 
java <filename>
```
enable `assert`  mode:  in IDEA's run configuration, add `-ea` in "VM option".



`.c` to `.ll`

```
clang <filename>.c -S -emit-llvm -O0 -fno-discard-value-names
```

`.ll` to `.s`

```
llc builtin.ll -o builtin.s -march=riscv32 -mattr=+m
```




## Semantic Design

- File structure:

```mermaid
graph LR
g4[Mx.g4]-->V[MxBaseVisitor]
g4-->P[MxParser, MxLexer...]

V==ASTNode==>A[ASTBuilder]

A--Scope-->S[SemanticChecker]
A--for debug-->D[ASTPrinter]
```

- Procedure description:

```mermaid
graph LR
I(Code)--Character Stream-->L[Lexer]
L--Token Stream-->P[Parser]
P--frontend.parser tree-->AB[AST build]
P--visitor mode-->AB[AST build]
AB==AST node==>S[Semantic check]
AB==Scope manager==>S
```
## IR

based on LLVM IR

## Code generation

RISCV32i

## Register Allocation



## Reference

1. [Yx written by Antlr4](https://github.com/ZYHowell/Yx/tree/1c1a74e8e636cf64d2e6f73975cfb2cf50f69cca)
2. Tiger Book (Modern Compiler Implementation)
3. Dragon Book (Compilers: Principles, Techniques, and Tools 2e)
4. NJU Static Analysis by Prof. Yue Li and Tian Tan
3. SSA Book
