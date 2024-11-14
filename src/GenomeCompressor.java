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

import static java.lang.Long.toBinaryString;

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {
    public static final int NUM_COMBOS = 360;
    public static final int SEQUENCE_LENGTH  = 4;
    public static final int RADIX = 4;
    public static final int BIG_PRIME = 506683;
    public static final int BEG_SIZE = 1000;
    // Values holds values of each 4-character combo
    public static int[] values;
    // Keys holds values of each binary hash linked to each 4-character combo
    public static int[] keys;
    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */

    public static void compress() {
        // TODO: complete the compress() method
        String remaining = BinaryStdIn.readString();
        int binary = remaining.length();
        BinaryStdOut.write(binary);
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
        // Make key for each possible pattern/sequence of 4 characters and hash them to a different value (figure out how to access read in data)
        // For-loop to check every part of sequence:
            // read next 4 bits of data using BinaryStdIn.readChar()--use key to find the hashes and store them as the hashes
            // Add hash to sequence after converting hash to binary
        // Save binary hashes as the compression file
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // TODO: complete the expand() method
        int size = BinaryStdIn.readInt();
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
        // Use for-loop to go through each part of the compressed sequence
            // Expand each bit/hash into its original value using the maps to find original values
            // Add expanded value into sequence
        // Set total sequence as String back to its original place
        BinaryStdOut.close();
    }

    public static String hash(char toHash) {
        int hash = (toHash * RADIX) % BIG_PRIME;
        return Integer.toBinaryString(hash);
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