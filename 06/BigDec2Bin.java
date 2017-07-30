/*
 * Robert Quan
 * CSCE 312 Parser and Tokenizer
 * Converts out .asm file into binary .hack output file for our asssembler
 * I received help on this class from stackoverflow:
 * https://tinyurl.com/yaxx4nce
 */

//package assembler;

/**
 *
 * @author rquan
 */
public class BigDec2Bin {

    public static int[] string2arrayReversed( String s ){
        char a[] = s.toCharArray();
        int  b[] = new int[ s.length() ];
        for( int i = 0; i < a.length; i++ ){
            b[a.length-1-i] = a[i] - 48;
        }
        return b;
    }

    // adds two binary numbers represented as strings
    public static String add( String s1, String s2 ){
        String result = "", stmp;
        int[] a1, a2;
        int ctmp, mark = 0;

        // a1 should be the longer one
        a1 = string2arrayReversed( ( s1.length() > s2.length() ? s1 : s2 ) );
        a2 = string2arrayReversed( ( s1.length() < s2.length() ? s1 : s2 ) );

        for( int i = 0; i < a1.length; i++ ){
            ctmp = a1[i] + ( i < a2.length ? a2[i] : 0 ) + mark;

            switch( ctmp ){
                default:
                case 0:
                    stmp = "0";
                    mark = 0;
                    break;
                case 1:
                    stmp = "1";
                    mark = 0;
                    break;
                case 2:
                    stmp = "0";
                    mark = 1;
                    break;
                case 3:
                    stmp = "1";
                    mark = 1;
                    break;
            }

            result = stmp + result;
        }

        if( mark > 0 ) { result = "1" + result; }

        return result;
    }

    public static String dec2bin( String s ){
        int k = Integer.parseInt(s);
        
        if (k < 20000) {
            return Integer.toBinaryString(k);
        } else {
            String result = "";

            for (int i = 0; i < s.length(); i++) {
                result = add(result + "0", result + "000");
                result = add(result, Integer.toBinaryString(s.charAt(i) - 48));
            }

            return result;
        }
    }

}
