// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// R0, R1, and R2 all pull their data form the test file.
// The idea behind multiplication is adding the same number N times, 
// Ex 2* 3 = 2+2+2 = 6, so we loop an addition 3 times


	@R2	//Ram[2] will be our summing variable
	M=0	//Set Memory to 0

(MULTIPLY)	
	@R1	//Ram[1] contains the number of times we will run the loop
	D=M	//Grab R1, load into D register

	@END	
	D;JEQ	//If D=0, we go to the End loop and finish the multiplication

	@R0	//Pull from Test file
	D=M	//D loads value from Memory[0]
	@R2
	M=D+M	//We add the value of RAM[0] to our summing variable
	@R1	//Our counter 
	M=M-1	//Subtract one indicating one addition has been done
	@MULTIPLY
	0;JMP	//Keep running Loop, jump back up to MULTIPLY

(END)
	@END
	0;JMP	//Infine loop indicating the end of our multiplication.

