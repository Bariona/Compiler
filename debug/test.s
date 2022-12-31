	.text
	.globl	globInit
	.p2align	2
	.type	globInit,@function
globInit:
.globInit.entry_of_globInit:
	addi sp, sp, -20
	sw s0, 16(sp)
	sw ra, 12(sp)
	lui a0, %hi(glob_a)
	sw a0, 8(sp)
	li a0, 5
	sw a0, 4(sp)
	j .globInit.exit_of_globInit
.globInit.exit_of_globInit:
	lw a0, 16(sp)
	mv s0, a0
	lw a0, 12(sp)
	mv ra, a0
	addi sp, sp, 20
	ret
	.size	globInit, .-globInit
			 # -- End function
	.globl	main
	.p2align	2
	.type	main,@function
main:
.main.entry_of_main:
	addi sp, sp, -28
	sw s0, 24(sp)
	sw ra, 20(sp)
	call globInit
	li a0, 0
	sw a0, 16(sp)
	lui a0, %hi(glob_a)
	sw a0, 12(sp)
	lw a0, 8(sp)
	mv a0, a0
	call printInt
	j .main.exit_of_main
.main.exit_of_main:
	lw a0, 4(sp)
	mv a0, a0
	lw a0, 24(sp)
	mv s0, a0
	lw a0, 20(sp)
	mv ra, a0
	addi sp, sp, 28
	ret
	.size	main, .-main
			 # -- End function
	.type	glob_a,@object
	.section	.rodata
	.globl	glob_a
glob_a:
	.word	0
	.size	glob_a, 4
