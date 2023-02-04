	.text
	.globl	globInit
	.p2align	2
	.type	globInit,@function
globInit:
.globInit.entry_of_globInit:
	ret
	.size	globInit, .-globInit
			 # -- End function
	.globl	main
	.p2align	2
	.type	main,@function
main:
.main.entry_of_main:
	addi sp, sp, -4
	sw ra, 0(sp)
	call globInit
	li a0, 0
	li t0, 5
	addi t0, t0, 1
	mv ra, t0
	add ra, ra, t0
	add a0, ra, t0
	lw ra, 0(sp)
	addi sp, sp, 4
	ret
	.size	main, .-main
			 # -- End function
