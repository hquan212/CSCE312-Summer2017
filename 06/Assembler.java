/*
 * Robert Quan
 * CSCE 312 Parser and Tokenizer
 * Converts out .asm file into binary .hack output file for our asssembler
 */
//package assembler;

import java.util.Scanner;
import java.io.*;

public class Assembler {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Tokenizer toke = new Tokenizer();
        String asmfile;
        asmfile = args[0];       
        String line;   
        toke.lableTable(asmfile);
	String hack = asmfile.substring(0,(asmfile.length()-4));

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(asmfile)))) {

                FileWriter fw = new FileWriter(hack + ".hack");
                BufferedWriter fout = new BufferedWriter(fw);              
                    
                while ((line = br.readLine()) != null) {
                        String out = toke.getTokenized(line);
                   
                        if(!"".equals(out)){
                        fout.write(out); //Prints the binary code
                        fout.newLine(); //Create the newline
                    
                        }
                }
                fout.close();

        } catch (IOException e) {      //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

}
