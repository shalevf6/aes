/**
 * This class represents a cypher
 */
public class Cypher extends Message {

    /**
     * Transforms a given byte array into a 2D array with 16 bytes in each cell
     * @param message - a given byte array
     */
    public Cypher(byte[] message) {
        super(message);
    }
}
