// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(INITIAL)
	@16384	//Start with initial screen pixel in memory
	D=A	//Save in D register our Screen Address
	@Counter
	M=D	//start screen initial location in a counter varibale

(KEYS)
	@24576	//Keyboard RAM Address
	D=M	//Save in D register
	@WHITESCREEN
	D;JEQ		//If we are not pressing keys, go to whitescreen
	@BLACKSCREEN
	D;JGT		//If we are pressing keys go to blacscreen loop
	@KEYS		//keep looping until we find a difference
	0;JMP

(WHITESCREEN)
	@Fill	//We will save M=0 in out varibale memory address
	M=0	//White pixels
	@TRIGGER	//Lets pain the whole screen
	0;JMP

(BLACKSCREEN)
	@Fill	//Save our will varibale memory adress
	M=-1	//Will paint 16 pixels black
	@TRIGGER	//Lets pain the whole screen
	0;JMP

(TRIGGER)
	@Fill	
	D=M	//Save our black/white memory command into D register
	@Counter	//Initially holds the screens initial pixel
	A=M	//A register now points to initial pixel
	M=D	//Change that initial pixel to our black or while
	@Counter
	D=M+1	//Move to next pixel after initial
	@24576	//keyboard RAM Address
	D=A-D	//This checks if A=D, if it is then we exit the trigger loop
	@Counter
	M=M+1	//Move pixel
	A=M	//Set A to next pixel and repeat Trigger
	@TRIGGER
	D;JGT		//if A=D=0, then exit, else repeat trigger

@INITIAL	//Instantly jump back to inital conditions loop if screen is black
0;JMP		//This program never ends
