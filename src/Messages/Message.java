package Messages;

/**
 * This class represents a message
 */
public abstract class Message {

    private byte[][] messageBlocks;
    private short currentBlock;

    /**
     * Transforms a given byte array into a 2D array with 16 bytes in each cell
     * @param message - a given byte array
     */
    public Message(byte[] message) {
        messageBlocks = new byte[message.length / 16][16];
        int newBlockCounter = 0;
        int innerBlockCounter = 0;
        int arrayCounter = 0;
        while (arrayCounter < message.length) {
            while (innerBlockCounter < 16) {
                messageBlocks[newBlockCounter][innerBlockCounter] = message[arrayCounter];
                innerBlockCounter++;
                arrayCounter++;
            }
            innerBlockCounter = 0;
            newBlockCounter++;
        }
        currentBlock = 0;
    }

    /**
     * Gets the next block of bytes of the message in a 4X4 byte array
     * @return - the next block of bytes of the message in a 4X4 byte array
     */
    public byte[][] getNextBlock() {
        if (currentBlock < messageBlocks.length) {
            byte[][] block = new byte[4][4];
            byte[] arrBlock = messageBlocks[currentBlock];
            int blockCounter = 0;
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block[0].length; j++) {
                    block[j][i] = arrBlock[blockCounter];
                    blockCounter++;
                }
            }
            currentBlock++;
            return block;
        } else {
            return null;
        }
    }

    /**
     * Resets the block count
     */
    public void resetBlockCount() {
        currentBlock = 0;
    }

    /**
     * Gets the length of the message in bytes
     * @return - the length of the message in bytes
     */
    public int getMessageLength() {
        return messageBlocks.length * messageBlocks[0].length;
    }

    /**
     * method to get a specific block out of the message
     * @param num - the block number
     * @return block of the number or null if invalid number
     */
    public byte[] getBlock(int num){
        if (num <0 || num>=messageBlocks.length)
            return null;

        return messageBlocks[num];
    }
}
