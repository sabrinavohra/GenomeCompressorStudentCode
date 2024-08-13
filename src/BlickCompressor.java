/******************************************************************************
 *  BlickCompressor
 *  An example compression algorithm created by Zach Blick
 *  for Adventures in Algorithms at Menlo School in Atherton, CA
 *
 *  Compilation:  javac BlickCompressor.java
 *  Execution:    java BlickCompressor - < input.txt > output.bin  (compress)
 *  Execution:    java BlickCompressor + < input.bin > output.txt   (expand)
 *  Dependencies: BinaryStdIn.java, BinaryStdOut.java
 *
 *  Compresses by converting a target String into a one-character escape code.
 *
 * % java DumpBinary 0 < BlickTest.txt
 *    992 bits
 *
 *  % java BlickCompressor - < BlickTest.txt | java DumpBinary 0
 *    888 bits
 ******************************************************************************/
public class BlickCompressor {

    public static final String TARGET = "Blickensderfer";
    public static final int LEN = TARGET.length();
    public static final char ESC = 0x07;                   // Utilizing an obsolete ASCII code

    /**
     * Reads a binary sequence from standard input; converts any instance of
     * the TARGET stirng into the ESC character. Keeps all other chars unchanged.
     */
    public static void compress() {

        // Read in the string and find the first instance of TARGET
        String s = BinaryStdIn.readString();
        String copy = s;
        int n = s.length();
        int target = copy.indexOf(TARGET);

        // Write out each character
        for (int i = 0; i < n; i++) {
            if (i == target) {
                BinaryStdOut.write(ESC);
                i += LEN - 1;
                copy = copy.substring(target + LEN);
                target = copy.indexOf(TARGET);
            }
            else {
                BinaryStdOut.write(s.charAt(i));
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; converts any instance of
     * the escape character into the TARGET string. Keeps all other chars unchanged.
     */
    public static void expand() {

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            if (c == ESC) {
                BinaryStdOut.write(TARGET);
            }
            else {
                BinaryStdOut.write(c);
            }
        }
        BinaryStdOut.close();
    }


    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
