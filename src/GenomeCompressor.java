/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/
/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {
    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        // Reads entire String
        String remaining = BinaryStdIn.readString();
        int codes = remaining.length();
        // Writes number of codes
        BinaryStdOut.write(codes);
        // Checks each character and converts it to binary
        for(int i = 0; i < remaining.length(); i++) {
            char c = remaining.charAt(i);
            if(c == 'A') {
                BinaryStdOut.write(0b00, 2);
            }
            else if(c == 'C') {
                BinaryStdOut.write(0b01,2);
            }
            else if(c == 'G') {
                BinaryStdOut.write(0b10, 2);
            }
            else {
                BinaryStdOut.write(0b11, 2);
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // Finds front-loaded value of how many codes to count
        int size = BinaryStdIn.readInt();
        // Converts and writes out each code as its corresponding character
        for(int i = 0; i < size; i++) {
            int code = BinaryStdIn.readInt(2);
            if(code == 0b00) {
                BinaryStdOut.write('A', 8);
            }
            else if(code == 0b01) {
                BinaryStdOut.write('C', 8);
            }
            else if(code == 0b10) {
                BinaryStdOut.write('G', 8);
            }
            else {
                BinaryStdOut.write('T', 8);
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
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