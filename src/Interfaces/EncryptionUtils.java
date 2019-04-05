package Interfaces;

public class EncryptionUtils {

    /**
     * Operates the "Shift Rows" operation on a given block
     * @param block - a given block
     */
    public static void shiftRows(byte[][] block) {

    }

    /**
     * Operates the reverse operation of "Shift Rows" on a given block
     * @param block - a given block
     */
    public static void unShiftRows(byte[][] block) {

    }

    /**
     * Operates the XOR operation with a given key on a given block
     * @param currentBlock - a given block
     * @param k - a given encryption key
     */
    public static void addRoundKey(byte[][] currentBlock, byte[] k) {

    }

    /**
     * Adds a given block to a given output byte array
     * @param output - a given output
     * @param currentBlock - a given block
     * @param blockCount - the given block's number
     */
    public static void addBlockToOutput(byte[] output, byte[][] currentBlock, int blockCount) {

    }

    public static void generateKeys() {

    }
}
