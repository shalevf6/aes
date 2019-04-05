package Interfaces;

import Messages.Cypher;

/**
 * This class represents a Decryption of a given message using the AES*3 algorithm
 */
public class Decrypt {

    private Cypher cypher;
    private byte[] k1;
    private byte[] k2;
    private byte[] k3;

    public Decrypt(byte[] keys, byte[] message) {
        cypher = new Cypher(message);
        k1 = new byte[16];
        k2 = new byte[16];
        k3 = new byte[16];
        for (int i = 0; i < keys.length; i++) {
            // getting the first key
            if (i < 16) {
                k1[i] = keys[i];
            }
            else {
                // getting the second key
                if (i < 32) {
                    k2[i - 16] = keys[i];
                }
                // getting the third key
                else {
                    k3[i - 32] = keys[i];
                }
            }
        }
    }

    /**
     * Decrypts the cypher according to the AES*3 decryption algorithm
     * @return - a byte array representing the decrypted message
     */
    public byte[] decrypt() {
        byte[] plainText = new byte[cypher.getMessageLength()];
        int blockCount = 0;
        while (blockCount < plainText.length) {
            byte[][] currentBlock = cypher.getNextBlock();
            EncryptionUtils.unShiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k1);
            EncryptionUtils.unShiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k2);
            EncryptionUtils.unShiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k3);
            EncryptionUtils.addBlockToOutput(plainText, currentBlock, blockCount);
            blockCount++;
        }
        return plainText;
    }
}