/*
 * Robert Quan
 * CSCE 312 Parser and Tokenizer
 * Converts out .asm file into binary .hack output file for our asssembler
 */
//package assembler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rquan
 */
public class Tokenizer {

    //-----------Varibales used in Tokenizer class--------------------- 
    public static int lineNumber = 0;
    public static int globalCounter = 0;
    public static int removedLine = 0;
    public static int freeRam = 16;
    Map<String, String> lableTable = new HashMap<>();
    Map<String, String> varibaleTable = new HashMap<>();

    public String getTokenized(String line) {

        Dictionaries diction = new Dictionaries(); //Create new Dictionary class object
        BigDec2Bin bin = new BigDec2Bin();

        String delimeter = "(\\/+)(.*)";  //regex pattern to remove comments
        String delimeterSpace = "\\s+";
        String tokenized = line.replaceAll(delimeter, ""); //Remove comments
        tokenized = tokenized.replaceAll(delimeterSpace, "");

        String delimJump = ".*;[A-Z]*";
        String delimC = "[ADM]*=.*";
        String delimAddress = "(@\\d*)";
        String delimLoop = "\\(.*\\)";
        String delimAddnew = "(@.*)";

        StringBuilder binaryOut = new StringBuilder();

        if (!"".equals(tokenized)) {

            //-------------------------------Delimeter for @Digits-----------------------
            if (tokenized.matches(delimAddress)) {  //case for just @digit

                globalCounter++;   ///Line number
                String binary = BigDec2Bin.dec2bin(tokenized.substring(1));
                StringBuilder stringList = new StringBuilder();

                for (int i = 0; i < (16 - binary.length()); i++) {
                    stringList.append("0");
                }
                String out = stringList + binary;
                return out; //prints The binary value of any number

                //-------------------------------Delimeter for Equations------------------
            } else if (tokenized.matches(delimC)) {  //Finds any equations

                globalCounter++;   ///Line number
                binaryOut.append("111"); //C-instruction Recognized
                String[] tempStringArray;
                tempStringArray = tokenized.split("=");

                binaryOut.append(diction.comp.get(tempStringArray[1]));
                binaryOut.append(diction.dest.get(tempStringArray[0]));

                binaryOut.append("000"); // NO Jump

                return binaryOut.toString();

                //-------------------------------Delimeter for JUMPS---------------------
            } else if (tokenized.matches(delimJump)) {  //This condition is for the Jumps

                globalCounter++;   ///Line number
                binaryOut.append("111"); //C-instruction Recognized
                String charString = tokenized.charAt(0) + "";
                binaryOut.append(diction.comp.get(charString));
                binaryOut.append("000");
                binaryOut.append(diction.jump.get(tokenized.substring(1)));

                return binaryOut.toString();

                //-------------------------------Delimeter for Symbols-------------------
            } else {

                //-------------------------------Predefined Symbols table---------------- 
                if (diction.symbols.containsKey(tokenized.substring(1))) {
                    globalCounter++;   ///Line number
                    String symbol = diction.symbols.get(tokenized.substring(1));
                    String binary = BigDec2Bin.dec2bin(symbol);
                    StringBuilder stringList = new StringBuilder();
                    for (int i = 0; i < (16 - binary.length()); i++) {
                        stringList.append("0");
                    }
                    String out = stringList + binary;
                    return out;           
                } 

                //----------------------------- Symbols table from 1st run---------------  
                
                else if (lableTable.containsKey("(" + tokenized.substring(1) + ")")) {
                    globalCounter++;   ///Line number
                    String variable = tokenized.substring(1);
                    String symbolLoopLineNumber = lableTable.get("(" + variable + ")");
                    String binary = BigDec2Bin.dec2bin(symbolLoopLineNumber);
                    StringBuilder stringList = new StringBuilder();
                    for (int i = 0; i < (16 - binary.length()); i++) {
                        stringList.append("0");
                    }
                    String out = stringList + binary;
                    return out; //return the binary number of the symbols

                } 

                //-------------------------Here are the uncategorized variables----------
                
                else if (tokenized.matches(delimAddnew)) {
                    globalCounter++;   ///Line number
                    
                    if(!varibaleTable.containsKey(tokenized.substring(1))){
                        varibaleTable.put(tokenized.substring(1), Integer.toString(freeRam));   
                        freeRam++;
                    }
                    
                    String binary = BigDec2Bin.dec2bin(varibaleTable.get(tokenized.substring(1)));
                    StringBuilder stringList = new StringBuilder();

                    for (int i = 0; i < (16 - binary.length()); i++) {
                        stringList.append("0");
                    }
                    String out = stringList + binary;    
                    return out; //prints The binary value of any number
                }

                //----------------------There are all the Lables in the second pass------
                
                return "";
            }

        }
        return "";
    }

    public void lableTable(String asmfile) {
        //------------First pass to create the SymbolTable---------------------
        String line;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(asmfile)))) {

            while ((line = br.readLine()) != null) {
                String delimLoop = "\\(.*\\)";
                String delimeter = "(\\/+)(.*)";  //regex pattern to remove comments
                String delimeterSpace = "\\s+";
                String tokenized = line.replaceAll(delimeter, ""); //Remove comments
                tokenized = tokenized.replaceAll(delimeterSpace, "");
                if (!"".equals(tokenized)) {
                    lineNumber++;

                    //----------Put the Loops into our new Symbol Table ------------    
                    if (tokenized.matches(delimLoop)) {
                        removedLine++;
                        lableTable.put(tokenized, Integer.toString(lineNumber - removedLine));
                    }
                }
            }

        } catch (IOException e) {      //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
