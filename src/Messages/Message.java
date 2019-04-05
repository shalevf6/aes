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
     * Gets the next block of bytes of the message
     * @return - the next block of bytes of the message
     */
    public byte[] getNextBlock() {
        currentBlock++;
        return messageBlocks[currentBlock - 1];
    }

    /**
     * Resets the block count
     */
    public void resetBlockCount() {
        currentBlock = 0;
    }
}