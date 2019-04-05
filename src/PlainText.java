/**
 * This class represents a plain text
 */
public class PlainText extends Message {

    /**
     * Transforms a given byte array into a 2D array with 16 bytes in each cell
     * @param message - a given byte array
     */
    public PlainText(byte[] message) {
        super(message);
    }
}
