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
	li t0, 10
	li ra, 10
	beq t0, ra, .main.if.then
	li a0, 30
	j .main.if.end
.main.if.then:
	li a0, 20
	j .main.if.end
.main.if.end:
	lw ra, 0(sp)
	addi sp, sp, 4
	ret
	.size	main, .-main
			 # -- End function
