/*
 * Robert Quan
 * CSCE 312 Parser and Tokenizer
 * Converts out .asm file into binary .hack output file for our asssembler
 */
//package assembler;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rquan
 */
public class Dictionaries {

    Map<String, String> dest = new HashMap<>();
    Map<String, String> jump = new HashMap<>();
    Map<String, String> comp = new HashMap<>();
    Map<String, String> symbols = new HashMap<>();
    Map<String, String> variables = new HashMap<>();
  

    public Dictionaries() {

        dest.put("", "000");
        dest.put("A", "100");
        dest.put("M", "001");
        dest.put("D", "010");
        dest.put("MD", "011");
        dest.put("AM", "101");
        dest.put("AD", "110");
        dest.put("ADM", "111");

        jump.put("", "000");
        jump.put(";JGT", "001");
        jump.put(";JGE", "011");
        jump.put(";JEQ", "010");
        jump.put(";JLT", "100");
        jump.put(";JNE", "101");
        jump.put(";JLE", "110");
        jump.put(";JMP", "111");

        symbols.put("SP", "0");
        symbols.put("LCL", "1");
        symbols.put("ARG", "2");
        symbols.put("THIS", "3");
        symbols.put("THAT", "4");
        symbols.put("SCREEN", "16384");
        symbols.put("KBD", "24576");
        symbols.put("R0", "0");
        symbols.put("R1", "1");
        symbols.put("R2", "2");
        symbols.put("R3", "3");
        symbols.put("R4", "4");
        symbols.put("R5", "5");
        symbols.put("R6", "6");
        symbols.put("R7", "7");
        symbols.put("R8", "8");
        symbols.put("R9", "9");
        symbols.put("R10", "10");
        symbols.put("R11", "11");
        symbols.put("R12", "12");
        symbols.put("R13", "13");
        symbols.put("R14", "14");
        symbols.put("R15", "15");

        comp.put("0", "0101010");
        comp.put("1", "0111111");
        comp.put("-1", "0111010");
        comp.put("D", "0001100");
        comp.put("A", "0110000");
        comp.put("M", "1110000");
        comp.put("!D", "0001101");
        comp.put("!A", "0110001");
        comp.put("!M", "1110001");
        comp.put("-D", "0001111");
        comp.put("-A", "0110011");
        comp.put("-M", "1110011");
        comp.put("D+1", "0011111");
        comp.put("A+1", "0110111");
        comp.put("M+1", "1110111");
        comp.put("D-1", "0001110");
        comp.put("A-1", "0110010");
        comp.put("M-1", "1110010");
        comp.put("D+A", "0000010");
        comp.put("D+M", "1000010");
        comp.put("D-A", "0010011");
        comp.put("D-M", "1010011");
        comp.put("A-D", "0000111");
        comp.put("M-D", "1000111");
        comp.put("D&A", "0000000");
        comp.put("D&M", "1000000");
        comp.put("D|A", "0010101");
        comp.put("D|M", "1010101");

    }



}
