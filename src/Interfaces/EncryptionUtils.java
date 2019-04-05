package Interfaces;

public class EncryptionUtils {

    /**
     * Operates the "Shift Rows" operation on a given block
     * @param block - a given block
     */
    public static void shiftRows(byte[][] block) {
        shiftLeftInRow(block[1]);
        shiftLeftInRow(block[2]);
        shiftLeftInRow(block[2]);
        shiftLeftInRow(block[3]);
        shiftLeftInRow(block[3]);
        shiftLeftInRow(block[3]);
    }

    /**
     * Performs one shift to the left in a given array
     * @param blockRow - a given array
     */
    private static void shiftLeftInRow(byte[] blockRow) {
        byte temp = blockRow[0];
        for(int i = 0; i < 3; i++)
        {
            blockRow[i] = blockRow[i + 1];
        }
        blockRow[3] = temp;
    }

    /**
     * Operates the reverse operation of "Shift Rows" on a given block
     * @param block - a given block
     */
    public static void unShiftRows(byte[][] block) {
        shiftRightInRow(block[1]);
        shiftRightInRow(block[2]);
        shiftRightInRow(block[2]);
        shiftRightInRow(block[3]);
        shiftRightInRow(block[3]);
        shiftRightInRow(block[3]);
    }

    /**
     * Performs one shift to the right in a given array
     * @param blockRow - a given array
     */
    private static void shiftRightInRow(byte[] blockRow) {
        byte temp = blockRow[3];
        for(int i = 3; i > 0; i--)
        {
            blockRow[i] = blockRow[i - 1];
        }
        blockRow[0] = temp;
    }

    /**
     * Operates the XOR operation with a given key on a given block
     * @param currentBlock - a given block
     * @param k - a given encryption key
     */
    public static void addRoundKey(byte[][] currentBlock, byte[] k) {
        int kCounter = 0;
        for (int i = 0; i < currentBlock.length; i++) {
            for (int j = 0; j < currentBlock[0].length; j++) {
                currentBlock[j][i] = (byte)(currentBlock[j][i] ^ k[kCounter]);
                kCounter++;
            }
        }
    }

    /**
     * Adds a given block to a given output byte array
     * @param output - a given output
     * @param currentBlock - a given block
     * @param blockCount - the given block's number
     */
    public static void addBlockToOutput(byte[] output, byte[][] currentBlock, int blockCount) {
        int outputCounter = blockCount * 16;
        for (int i = 0; i < currentBlock.length; i++) {
            for (int j = 0; j < currentBlock[0].length; j++) {
                output[outputCounter] = currentBlock[j][i];
                outputCounter++;
            }
        }
    }

    public static void generateKeys() {

    }
}
