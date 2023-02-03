	.text
	.globl	globInit
	.p2align	2
	.type	globInit,@function
globInit:
.globInit.entry_of_globInit:
	addi sp, sp, -4
	sw ra, 0(sp)
	li ra, 4
	li t0, 4
	mul ra, ra, t0
	addi a0, ra, 4
	call malloc
	li ra, 4
	sw ra, 0(a0)
	addi ra, a0, 4
	la t0, glob_a
	sw ra, 0(t0)
	lw ra, 0(sp)
	addi sp, sp, 4
	ret
	.size	globInit, .-globInit
			 # -- End function
	.globl	main
	.p2align	2
	.type	main,@function
main:
.main.entry_of_main:
	addi sp, sp, -20
	sw s1, 16(sp)
	sw s7, 4(sp)
	sw s9, 8(sp)
	sw s10, 0(sp)
	sw ra, 12(sp)
	call globInit
	li a0, 0
	li t0, 8
	li ra, 4
	mul ra, t0, ra
	addi a0, ra, 4
	call malloc
	li ra, 4
	sw ra, 0(a0)
	addi ra, a0, 4
	mv s1, ra
	addi t0, s1, 0
	la ra, glob_a
	lw ra, 0(ra)
	sw ra, 0(t0)
	addi t0, s1, 8
	la ra, glob_a
	lw ra, 0(ra)
	sw ra, 0(t0)
	addi t0, s1, 16
	la ra, glob_a
	lw ra, 0(ra)
	sw ra, 0(t0)
	addi t0, s1, 24
	la ra, glob_a
	lw ra, 0(ra)
	sw ra, 0(t0)
	mv ra, s1
	addi ra, ra, -4
	lw a0, 0(ra)
	call toString
	call println
	li ra, 0
	mv s7, ra
	j .main.for.cond
.main.for.cond:
	addi ra, s1, 0
	lw ra, 0(ra)
	addi ra, ra, -4
	lw ra, 0(ra)
	blt s7, ra, .main.for.body
	li ra, 0
	mv s7, ra
	j .main.for.cond1
.main.for.body:
	addi ra, s1, 0
	lw t0, 0(ra)
	li ra, 4
	mul ra, s7, ra
	add s9, t0, ra
	call getInt
	sw a0, 0(s9)
	addi ra, s7, 1
	mv s7, ra
	j .main.for.cond
.main.for.cond1:
	addi ra, s1, 8
	lw ra, 0(ra)
	addi ra, ra, -4
	lw ra, 0(ra)
	blt s7, ra, .main.for.body1
	la ra, .str
	addi a0, ra, 0
	call println
	li ra, 0
	mv s7, ra
	j .main.for.cond2
.main.for.body1:
	addi ra, s1, 8
	lw t0, 0(ra)
	li ra, 4
	mul ra, s7, ra
	add ra, t0, ra
	lw a0, 0(ra)
	call toString
	call print
	addi ra, s7, 1
	mv s7, ra
	j .main.for.cond1
.main.for.cond2:
	addi ra, s1, 16
	lw ra, 0(ra)
	addi ra, ra, -4
	lw ra, 0(ra)
	blt s7, ra, .main.for.body2
	li ra, 0
	mv s7, ra
	j .main.for.cond3
.main.for.body2:
	addi ra, s1, 16
	lw t0, 0(ra)
	li ra, 4
	mul ra, s7, ra
	add t0, t0, ra
	li ra, 0
	sw ra, 0(t0)
	addi ra, s7, 1
	mv s7, ra
	j .main.for.cond2
.main.for.cond3:
	addi ra, s1, 24
	lw ra, 0(ra)
	addi ra, ra, -4
	lw ra, 0(ra)
	blt s7, ra, .main.for.body3
	li a0, 0
	lw s1, 16(sp)
	lw s7, 4(sp)
	lw s9, 8(sp)
	lw s10, 0(sp)
	lw ra, 12(sp)
	addi sp, sp, 20
	ret
.main.for.body3:
	addi ra, s1, 24
	lw t0, 0(ra)
	li ra, 4
	mul ra, s7, ra
	add ra, t0, ra
	lw a0, 0(ra)
	call toString
	call print
	addi ra, s7, 1
	mv s7, ra
	j .main.for.cond3
	.size	main, .-main
			 # -- End function
	.type	glob_a,@object
	.section	.rodata
	.globl	glob_a
glob_a:
	.word	0
	.size	glob_a, 4
	.type	.str,@object
	.section	.rodata
.str:
	.asciz	""
	.size	.str, 0
