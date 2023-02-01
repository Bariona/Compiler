	.text
	.globl	globInit
	.p2align	2
	.type	globInit,@function
globInit:
.globInit.entry_of_globInit:
	la t1, glob_ans
	li t0, 0
	sw t0, 0(t1)
	la t0, glob_fa
	li t1, 0
	sw t1, 0(t0)
	la t0, glob_rk
	li t1, 0
	sw t1, 0(t0)
	la t0, glob_e
	li t1, 0
	sw t1, 0(t0)
	j .globInit.exit_of_globInit
.globInit.exit_of_globInit:
	ret
	.size	globInit, .-globInit
			 # -- End function
	.globl	qsort
	.p2align	2
	.type	qsort,@function
qsort:
.qsort.entry_of_qsort:
	addi sp, sp, -16
	sw ra, 12(sp)
	mv ra, a2
	sw a0, 4(sp)
	sw ra, 0(sp)
	lw ra, 0(sp)
	blt a1, ra, .qsort.if.then
	j .qsort.if.else
.qsort.exit_of_qsort:
	lw ra, 12(sp)
	addi sp, sp, 16
	ret
.qsort.if.then:
	sw a1, 8(sp)
	lw ra, 0(sp)
	mv t1, ra
	lw t0, 4(sp)
	li ra, 8
	mul ra, a1, ra
	add ra, t0, ra
	lw ra, 0(ra)
	mv t0, ra
	j .qsort.while.cond
.qsort.if.else:
	j .qsort.if.end
.qsort.if.end:
	j .qsort.exit_of_qsort
.qsort.while.cond:
	lw t2, 8(sp)
	mv ra, t1
	blt t2, ra, .qsort.while.body
	j .qsort.while.end
.qsort.while.body:
	j .qsort.while.cond1
.qsort.while.end:
	lw t1, 4(sp)
	lw ra, 8(sp)
	li t2, 8
	mul ra, ra, t2
	add t1, t1, ra
	mv ra, t0
	sw ra, 0(t1)
	lw ra, 4(sp)
	lw t0, 8(sp)
	addi a2, t0, -1
	mv a0, ra
	call qsort
	lw a0, 4(sp)
	lw ra, 8(sp)
	addi a1, ra, 1
	lw a2, 0(sp)
	call qsort
	j .qsort.if.end
.qsort.while.cond1:
	lw t2, 8(sp)
	mv ra, t1
	blt t2, ra, .qsort.calc.True
	j .qsort.midBlock
.qsort.while.body1:
	mv ra, t1
	addi ra, ra, -1
	mv t1, ra
	j .qsort.while.cond1
.qsort.while.end1:
	lw t2, 8(sp)
	mv ra, t1
	blt t2, ra, .qsort.if.then1
	j .qsort.if.else1
.qsort.exitAnd:
	bne ra, zero, .qsort.while.body1
	j .qsort.while.end1
.qsort.calc.True:
	lw t2, 4(sp)
	li ra, 8
	mul ra, t1, ra
	add ra, t2, ra
	lw ra, 0(ra)
	addi ra, ra, 8
	lw t2, 0(ra)
	mv ra, t0
	addi ra, ra, 8
	lw ra, 0(ra)
	slt ra, t2, ra
	xori ra, ra, 1
	j .qsort.exitAnd
.qsort.if.then1:
	lw a0, 4(sp)
	lw ra, 8(sp)
	li t2, 8
	mul ra, ra, t2
	add a2, a0, ra
	lw t2, 4(sp)
	mv ra, t1
	li a0, 8
	mul ra, ra, a0
	add ra, t2, ra
	lw ra, 0(ra)
	sw ra, 0(a2)
	lw ra, 8(sp)
	addi ra, ra, 1
	sw ra, 8(sp)
	j .qsort.if.end1
.qsort.if.else1:
	j .qsort.if.end1
.qsort.if.end1:
	j .qsort.while.cond2
.qsort.while.cond2:
	lw t2, 8(sp)
	mv ra, t1
	blt t2, ra, .qsort.calc.True1
	j .qsort.midBlock1
.qsort.while.body2:
	lw ra, 8(sp)
	addi ra, ra, 1
	sw ra, 8(sp)
	j .qsort.while.cond2
.qsort.while.end2:
	lw ra, 8(sp)
	blt ra, t1, .qsort.if.then2
	j .qsort.if.else2
.qsort.exitAnd1:
	bne ra, zero, .qsort.while.body2
	j .qsort.while.end2
.qsort.calc.True1:
	lw a0, 4(sp)
	lw ra, 8(sp)
	li t2, 8
	mul ra, ra, t2
	add ra, a0, ra
	lw ra, 0(ra)
	addi ra, ra, 8
	lw t2, 0(ra)
	mv ra, t0
	addi ra, ra, 8
	lw ra, 0(ra)
	slt ra, t2, ra
	j .qsort.exitAnd1
.qsort.if.then2:
	lw a0, 4(sp)
	mv ra, t1
	li t2, 8
	mul ra, ra, t2
	add ra, a0, ra
	lw a2, 4(sp)
	lw t2, 8(sp)
	li a0, 8
	mul t2, t2, a0
	add t2, a2, t2
	lw t2, 0(t2)
	sw t2, 0(ra)
	addi ra, t1, -1
	mv t1, ra
	j .qsort.if.end2
.qsort.if.else2:
	j .qsort.if.end2
.qsort.if.end2:
	j .qsort.while.cond
.qsort.midBlock:
	li ra, 0
	j .qsort.exitAnd
.qsort.midBlock1:
	li ra, 0
	j .qsort.exitAnd1
	.size	qsort, .-qsort
			 # -- End function
	.globl	init
	.p2align	2
	.type	init,@function
init:
.init.entry_of_init:
	addi sp, sp, -16
	sw s2, 12(sp)
	sw s5, 4(sp)
	sw s8, 0(sp)
	sw ra, 8(sp)
	la ra, glob_n
	lw ra, 0(ra)
	addi s5, ra, 1
	li ra, 4
	mul ra, ra, s5
	addi a0, ra, 4
	call malloc
	sw s5, 0(a0)
	addi ra, a0, 4
	la t0, glob_fa
	sw ra, 0(t0)
	la ra, glob_n
	lw ra, 0(ra)
	addi s5, ra, 1
	li ra, 4
	mul ra, ra, s5
	addi a0, ra, 4
	call malloc
	sw s5, 0(a0)
	addi ra, a0, 4
	la t0, glob_rk
	sw ra, 0(t0)
	mv ra, s2
	li ra, 1
	mv s2, ra
	j .init.for.cond
.init.exit_of_init:
	lw s2, 12(sp)
	lw s5, 4(sp)
	lw s8, 0(sp)
	lw ra, 8(sp)
	addi sp, sp, 16
	ret
.init.for.cond:
	mv ra, s2
	la t0, glob_n
	lw t0, 0(t0)
	ble ra, t0, .init.for.body
	j .init.for.end
.init.for.body:
	la ra, glob_fa
	lw t1, 0(ra)
	mv ra, s2
	li t0, 4
	mul ra, ra, t0
	add t0, t1, ra
	mv ra, s2
	sw ra, 0(t0)
	la ra, glob_rk
	lw t1, 0(ra)
	mv ra, s2
	li t0, 4
	mul ra, ra, t0
	add ra, t1, ra
	li t0, 1
	sw t0, 0(ra)
	j .init.for.inc
.init.for.inc:
	mv ra, s2
	addi ra, ra, 1
	mv s2, ra
	j .init.for.cond
.init.for.end:
	j .init.exit_of_init
	.size	init, .-init
			 # -- End function
	.globl	find
	.p2align	2
	.type	find,@function
find:
.find.entry_of_find:
	addi sp, sp, -4
	sw ra, 0(sp)
	mv ra, a0
	la t0, glob_fa
	lw t1, 0(t0)
	li t0, 4
	mul t0, a0, t0
	add t0, t1, t0
	lw t0, 0(t0)
	beq ra, t0, .find.if.then3
	j .find.if.else3
.find.exit_of_find:
	lw ra, 0(sp)
	addi sp, sp, 4
	ret
.find.if.then3:
	j .find.exit_of_find
.find.if.else3:
	j .find.if.end3
.find.if.end3:
	la ra, glob_fa
	lw ra, 0(ra)
	li t0, 4
	mul t0, a0, t0
	add ra, ra, t0
	lw a0, 0(ra)
	call find
	la ra, glob_fa
	lw ra, 0(ra)
	li t0, 4
	mul t0, a0, t0
	add ra, ra, t0
	lw a0, 0(ra)
	j .find.exit_of_find
	.size	find, .-find
			 # -- End function
	.globl	union
	.p2align	2
	.type	union,@function
union:
.union.entry_of_union:
	addi sp, sp, -16
	sw s0, 12(sp)
	sw s3, 4(sp)
	sw s10, 0(sp)
	sw ra, 8(sp)
	mv s3, a0
	mv s0, a1
	mv ra, s3
	mv a0, s3
	call find
	mv s3, a0
	mv ra, s0
	mv a0, s0
	call find
	mv s0, a0
	mv ra, s3
	mv t0, s0
	beq ra, t0, .union.if.then4
	j .union.if.else4
.union.exit_of_union:
	lw s0, 12(sp)
	lw s3, 4(sp)
	lw s10, 0(sp)
	lw ra, 8(sp)
	addi sp, sp, 16
	ret
.union.if.then4:
	li a0, 0
	j .union.exit_of_union
.union.if.else4:
	j .union.if.end4
.union.if.end4:
	la ra, glob_rk
	lw t0, 0(ra)
	mv ra, s3
	li t1, 4
	mul ra, ra, t1
	add ra, t0, ra
	lw t0, 0(ra)
	la ra, glob_rk
	lw t2, 0(ra)
	mv ra, s0
	li t1, 4
	mul ra, ra, t1
	add ra, t2, ra
	lw ra, 0(ra)
	bgt t0, ra, .union.if.then5
	j .union.if.else5
.union.if.then5:
	la ra, glob_fa
	lw t1, 0(ra)
	mv ra, s0
	li t0, 4
	mul ra, ra, t0
	add t0, t1, ra
	mv ra, s3
	sw ra, 0(t0)
	la ra, glob_rk
	lw t0, 0(ra)
	mv ra, s3
	li t1, 4
	mul ra, ra, t1
	add t1, t0, ra
	la ra, glob_rk
	lw t2, 0(ra)
	mv ra, s3
	li t0, 4
	mul ra, ra, t0
	add ra, t2, ra
	lw t0, 0(ra)
	la ra, glob_rk
	lw t2, 0(ra)
	mv ra, s0
	li s0, 4
	mul ra, ra, s0
	add ra, t2, ra
	lw ra, 0(ra)
	add ra, t0, ra
	sw ra, 0(t1)
	j .union.if.end5
.union.if.else5:
	la ra, glob_fa
	lw t1, 0(ra)
	mv ra, s3
	li t0, 4
	mul ra, ra, t0
	add t0, t1, ra
	mv ra, s0
	sw ra, 0(t0)
	la ra, glob_rk
	lw t0, 0(ra)
	mv ra, s0
	li t1, 4
	mul ra, ra, t1
	add t1, t0, ra
	la ra, glob_rk
	lw t2, 0(ra)
	mv ra, s0
	li t0, 4
	mul ra, ra, t0
	add ra, t2, ra
	lw t2, 0(ra)
	la ra, glob_rk
	lw t0, 0(ra)
	mv ra, s3
	li s0, 4
	mul ra, ra, s0
	add ra, t0, ra
	lw ra, 0(ra)
	add ra, t2, ra
	sw ra, 0(t1)
	j .union.if.end5
.union.if.end5:
	li a0, 1
	j .union.exit_of_union
	.size	union, .-union
			 # -- End function
	.globl	main
	.p2align	2
	.type	main,@function
main:
.main.entry_of_main:
	addi sp, sp, -20
	sw s0, 16(sp)
	sw s6, 8(sp)
	sw s7, 4(sp)
	sw s8, 12(sp)
	sw ra, 0(sp)
	call globInit
	li a0, 0
	call getInt
	la ra, glob_n
	sw a0, 0(ra)
	call getInt
	la ra, glob_m
	sw a0, 0(ra)
	la ra, glob_m
	lw s0, 0(ra)
	li ra, 8
	mul ra, ra, s0
	addi a0, ra, 4
	call malloc
	sw s0, 0(a0)
	addi ra, a0, 4
	la t0, glob_e
	sw ra, 0(t0)
	mv ra, s7
	li ra, 0
	mv s7, ra
	j .main.for.cond1
.main.exit_of_main:
	lw s0, 16(sp)
	lw s6, 8(sp)
	lw s7, 4(sp)
	lw s8, 12(sp)
	lw ra, 0(sp)
	addi sp, sp, 20
	ret
.main.for.cond1:
	mv ra, s7
	la t0, glob_m
	lw t0, 0(t0)
	blt ra, t0, .main.for.body1
	j .main.for.end1
.main.for.body1:
	li a0, 12
	call malloc
	mv s6, a0
	mv ra, s6
	addi s0, ra, 0
	call getInt
	sw a0, 0(s0)
	mv ra, s6
	addi s0, ra, 4
	call getInt
	sw a0, 0(s0)
	mv ra, s6
	addi s0, ra, 8
	call getInt
	sw a0, 0(s0)
	la ra, glob_e
	lw t0, 0(ra)
	mv ra, s7
	li t1, 8
	mul ra, ra, t1
	add t0, t0, ra
	mv ra, s6
	sw ra, 0(t0)
	j .main.for.inc1
.main.for.inc1:
	mv ra, s7
	addi ra, ra, 1
	mv s7, ra
	j .main.for.cond1
.main.for.end1:
	la ra, glob_e
	lw a0, 0(ra)
	la ra, glob_m
	lw ra, 0(ra)
	addi a2, ra, -1
	li a1, 0
	call qsort
	call init
	mv ra, s7
	li ra, 0
	mv s7, ra
	li ra, 0
	mv s0, ra
	j .main.while.cond3
.main.while.cond3:
	mv ra, s7
	la t0, glob_n
	lw t0, 0(t0)
	addi t0, t0, -1
	blt ra, t0, .main.while.body3
	j .main.while.end3
.main.while.body3:
	mv ra, s0
	la t0, glob_m
	lw t0, 0(t0)
	bge ra, t0, .main.if.then6
	j .main.if.else6
.main.while.end3:
	la ra, glob_rk
	lw s0, 0(ra)
	li a0, 1
	call find
	li ra, 4
	mul ra, a0, ra
	add ra, s0, ra
	lw t0, 0(ra)
	la ra, glob_n
	lw ra, 0(ra)
	beq t0, ra, .main.if.then8
	j .main.if.else8
.main.if.then6:
	li ra, 0
	addi a0, ra, -1
	call printInt
	li a0, 0
	j .main.exit_of_main
.main.if.else6:
	j .main.if.end6
.main.if.end6:
	la ra, glob_e
	lw t1, 0(ra)
	mv ra, s0
	li t0, 8
	mul ra, ra, t0
	add ra, t1, ra
	lw ra, 0(ra)
	mv s6, ra
	mv ra, s0
	addi ra, ra, 1
	mv s0, ra
	mv ra, s6
	addi ra, ra, 0
	lw a0, 0(ra)
	mv ra, s6
	addi ra, ra, 4
	lw a1, 0(ra)
	call union
	bne a0, zero, .main.if.then7
	j .main.if.else7
.main.if.then7:
	mv ra, s7
	addi ra, ra, 1
	mv s7, ra
	la ra, glob_ans
	lw t0, 0(ra)
	mv ra, s6
	addi ra, ra, 8
	lw ra, 0(ra)
	add t0, t0, ra
	la ra, glob_ans
	sw t0, 0(ra)
	j .main.if.end7
.main.if.else7:
	j .main.if.end7
.main.if.end7:
	j .main.while.cond3
.main.if.then8:
	la ra, glob_ans
	lw a0, 0(ra)
	call printInt
	j .main.if.end8
.main.if.else8:
	li ra, 0
	addi a0, ra, -1
	call printInt
	j .main.if.end8
.main.if.end8:
	li a0, 0
	j .main.exit_of_main
	.size	main, .-main
			 # -- End function
	.type	glob_n,@object
	.section	.rodata
	.globl	glob_n
glob_n:
	.word	0
	.size	glob_n, 4
	.type	glob_m,@object
	.section	.rodata
	.globl	glob_m
glob_m:
	.word	0
	.size	glob_m, 4
	.type	glob_ans,@object
	.section	.rodata
	.globl	glob_ans
glob_ans:
	.word	0
	.size	glob_ans, 4
	.type	glob_fa,@object
	.section	.rodata
	.globl	glob_fa
glob_fa:
	.word	0
	.size	glob_fa, 4
	.type	glob_rk,@object
	.section	.rodata
	.globl	glob_rk
glob_rk:
	.word	0
	.size	glob_rk, 4
	.type	glob_e,@object
	.section	.rodata
	.globl	glob_e
glob_e:
	.word	0
	.size	glob_e, 4
