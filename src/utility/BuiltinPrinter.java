package utility;

import java.io.FileOutputStream;
import java.io.IOException;

public class BuiltinPrinter {
  String builtin_s_as_literal = """
          	.text
          	.file	"builtin.c"
          	.globl	_toy_malloc             # -- Begin function _toy_malloc
          	.p2align	2
          	.type	_toy_malloc,@function
          _toy_malloc:                            # @_toy_malloc
          	.cfi_startproc
          # %bb.0:                                # %entry
          	srai	a1, a0, 31
          	tail	malloc
          .Lfunc_end0:
          	.size	_toy_malloc, .Lfunc_end0-_toy_malloc
          	.cfi_endproc
                                                  # -- End function
          	.globl	print                   # -- Begin function print
          	.p2align	2
          	.type	print,@function
          print:                                  # @print
          	.cfi_startproc
          # %bb.0:                                # %entry
          	lui	a1, %hi(.L.str)
          	addi	a1, a1, %lo(.L.str)
          	mv	a2, a0
          	mv	a0, a1
          	mv	a1, a2
          	tail	printf
          .Lfunc_end1:
          	.size	print, .Lfunc_end1-print
          	.cfi_endproc
                                                  # -- End function
          	.globl	println                 # -- Begin function println
          	.p2align	2
          	.type	println,@function
          println:                                # @println
          	.cfi_startproc
          # %bb.0:                                # %entry
          	tail	puts
          .Lfunc_end2:
          	.size	println, .Lfunc_end2-println
          	.cfi_endproc
                                                  # -- End function
          	.globl	printInt                # -- Begin function printInt
          	.p2align	2
          	.type	printInt,@function
          printInt:                               # @printInt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	lui	a1, %hi(.L.str.2)
          	addi	a1, a1, %lo(.L.str.2)
          	mv	a2, a0
          	mv	a0, a1
          	mv	a1, a2
          	tail	printf
          .Lfunc_end3:
          	.size	printInt, .Lfunc_end3-printInt
          	.cfi_endproc
                                                  # -- End function
          	.globl	printlnInt              # -- Begin function printlnInt
          	.p2align	2
          	.type	printlnInt,@function
          printlnInt:                             # @printlnInt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	lui	a1, %hi(.L.str.3)
          	addi	a1, a1, %lo(.L.str.3)
          	mv	a2, a0
          	mv	a0, a1
          	mv	a1, a2
          	tail	printf
          .Lfunc_end4:
          	.size	printlnInt, .Lfunc_end4-printlnInt
          	.cfi_endproc
                                                  # -- End function
          	.globl	getString               # -- Begin function getString
          	.p2align	2
          	.type	getString,@function
          getString:                              # @getString
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	sw	s0, 8(sp)
          	.cfi_offset ra, -4
          	.cfi_offset s0, -8
          	addi	a0, zero, 1024
          	mv	a1, zero
          	call	malloc
          	mv	s0, a0
          	lui	a0, %hi(.L.str)
          	addi	a0, a0, %lo(.L.str)
          	mv	a1, s0
          	call	__isoc99_scanf
          	mv	a0, s0
          	lw	s0, 8(sp)
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end5:
          	.size	getString, .Lfunc_end5-getString
          	.cfi_endproc
                                                  # -- End function
          	.globl	getInt                  # -- Begin function getInt
          	.p2align	2
          	.type	getInt,@function
          getInt:                                 # @getInt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	lui	a0, %hi(.L.str.2)
          	addi	a0, a0, %lo(.L.str.2)
          	addi	a1, sp, 8
          	call	__isoc99_scanf
          	lw	a0, 8(sp)
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end6:
          	.size	getInt, .Lfunc_end6-getInt
          	.cfi_endproc
                                                  # -- End function
          	.globl	toString                # -- Begin function toString
          	.p2align	2
          	.type	toString,@function
          toString:                               # @toString
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	sw	s0, 8(sp)
          	sw	s1, 4(sp)
          	.cfi_offset ra, -4
          	.cfi_offset s0, -8
          	.cfi_offset s1, -12
          	mv	s0, a0
          	addi	a0, zero, 13
          	mv	a1, zero
          	call	malloc
          	mv	s1, a0
          	lui	a0, %hi(.L.str.2)
          	addi	a1, a0, %lo(.L.str.2)
          	mv	a0, s1
          	mv	a2, s0
          	call	sprintf
          	mv	a0, s1
          	lw	s1, 4(sp)
          	lw	s0, 8(sp)
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end7:
          	.size	toString, .Lfunc_end7-toString
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_add                # -- Begin function _str_add
          	.p2align	2
          	.type	_str_add,@function
          _str_add:                               # @_str_add
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -32
          	.cfi_def_cfa_offset 32
          	sw	ra, 28(sp)
          	sw	s0, 24(sp)
          	sw	s1, 20(sp)
          	sw	s2, 16(sp)
          	sw	s3, 12(sp)
          	.cfi_offset ra, -4
          	.cfi_offset s0, -8
          	.cfi_offset s1, -12
          	.cfi_offset s2, -16
          	.cfi_offset s3, -20
          	mv	s2, a1
          	mv	s3, a0
          	call	strlen
          	mv	s0, a0
          	mv	s1, a1
          	mv	a0, s2
          	call	strlen
          	add	a1, s1, a1
          	add	a2, s0, a0
          	sltu	a0, a2, s0
          	add	a1, a1, a0
          	addi	a0, a2, 1
          	sltu	a2, a0, a2
          	add	a1, a1, a2
          	call	malloc
          	mv	s0, a0
          	mv	a1, s3
          	call	strcpy
          	mv	a0, s0
          	mv	a1, s2
          	lw	s3, 12(sp)
          	lw	s2, 16(sp)
          	lw	s1, 20(sp)
          	lw	s0, 24(sp)
          	lw	ra, 28(sp)
          	addi	sp, sp, 32
          	tail	strcat
          .Lfunc_end8:
          	.size	_str_add, .Lfunc_end8-_str_add
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_eq                 # -- Begin function _str_eq
          	.p2align	2
          	.type	_str_eq,@function
          _str_eq:                                # @_str_eq
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	seqz	a0, a0
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end9:
          	.size	_str_eq, .Lfunc_end9-_str_eq
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_neq                # -- Begin function _str_neq
          	.p2align	2
          	.type	_str_neq,@function
          _str_neq:                               # @_str_neq
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	snez	a0, a0
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end10:
          	.size	_str_neq, .Lfunc_end10-_str_neq
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_slt                # -- Begin function _str_slt
          	.p2align	2
          	.type	_str_slt,@function
          _str_slt:                               # @_str_slt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	srli	a0, a0, 31
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end11:
          	.size	_str_slt, .Lfunc_end11-_str_slt
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_sle                # -- Begin function _str_sle
          	.p2align	2
          	.type	_str_sle,@function
          _str_sle:                               # @_str_sle
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	slti	a0, a0, 1
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end12:
          	.size	_str_sle, .Lfunc_end12-_str_sle
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_sgt                # -- Begin function _str_sgt
          	.p2align	2
          	.type	_str_sgt,@function
          _str_sgt:                               # @_str_sgt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	sgtz	a0, a0
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end13:
          	.size	_str_sgt, .Lfunc_end13-_str_sgt
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_sge                # -- Begin function _str_sge
          	.p2align	2
          	.type	_str_sge,@function
          _str_sge:                               # @_str_sge
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strcmp
          	not	a0, a0
          	srli	a0, a0, 31
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end14:
          	.size	_str_sge, .Lfunc_end14-_str_sge
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_length             # -- Begin function _str_length
          	.p2align	2
          	.type	_str_length,@function
          _str_length:                            # @_str_length
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	call	strlen
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end15:
          	.size	_str_length, .Lfunc_end15-_str_length
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_substring          # -- Begin function _str_substring
          	.p2align	2
          	.type	_str_substring,@function
          _str_substring:                         # @_str_substring
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	sw	s0, 8(sp)
          	sw	s1, 4(sp)
          	sw	s2, 0(sp)
          	.cfi_offset ra, -4
          	.cfi_offset s0, -8
          	.cfi_offset s1, -12
          	.cfi_offset s2, -16
          	mv	s0, a1
          	mv	s2, a0
          	sub	s1, a2, a1
          	addi	a0, s1, 1
          	srai	a1, a0, 31
          	call	malloc
          	add	a1, s2, s0
          	mv	a2, s1
          	lw	s2, 0(sp)
          	lw	s1, 4(sp)
          	lw	s0, 8(sp)
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	tail	memcpy
          .Lfunc_end16:
          	.size	_str_substring, .Lfunc_end16-_str_substring
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_parseInt           # -- Begin function _str_parseInt
          	.p2align	2
          	.type	_str_parseInt,@function
          _str_parseInt:                          # @_str_parseInt
          	.cfi_startproc
          # %bb.0:                                # %entry
          	addi	sp, sp, -16
          	.cfi_def_cfa_offset 16
          	sw	ra, 12(sp)
          	.cfi_offset ra, -4
          	lui	a1, %hi(.L.str.2)
          	addi	a1, a1, %lo(.L.str.2)
          	addi	a2, sp, 8
          	call	__isoc99_sscanf
          	lw	a0, 8(sp)
          	lw	ra, 12(sp)
          	addi	sp, sp, 16
          	ret
          .Lfunc_end17:
          	.size	_str_parseInt, .Lfunc_end17-_str_parseInt
          	.cfi_endproc
                                                  # -- End function
          	.globl	_str_ord                # -- Begin function _str_ord
          	.p2align	2
          	.type	_str_ord,@function
          _str_ord:                               # @_str_ord
          	.cfi_startproc
          # %bb.0:                                # %entry
          	add	a0, a0, a1
          	lb	a0, 0(a0)
          	ret
          .Lfunc_end18:
          	.size	_str_ord, .Lfunc_end18-_str_ord
          	.cfi_endproc
                                                  # -- End function
          	.type	.L.str,@object          # @.str
          	.section	.rodata.str1.1,"aMS",@progbits,1
          .L.str:
          	.asciz	"%s"
          	.size	.L.str, 3

          	.type	.L.str.2,@object        # @.str.2
          .L.str.2:
          	.asciz	"%d"
          	.size	.L.str.2, 3

          	.type	.L.str.3,@object        # @.str.3
          .L.str.3:
          	.asciz	"%d\\n"
          	.size	.L.str.3, 4

          	.ident	"clang version 10.0.0-4ubuntu1 "
          	.section	".note.GNU-stack","",@progbits
                    """;
  public BuiltinPrinter(String dst) throws IOException {
    FileOutputStream out = new FileOutputStream(dst);
    out.write(builtin_s_as_literal.getBytes());
  }
}