package Interfaces;

import Messages.PlainText;

/**
 * This class represents an Encryption of a given message using the AES*3 algorithm
 */
public class Encrypt {

    private PlainText plainText;
    private byte[] k1;
    private byte[] k2;
    private byte[] k3;

    public Encrypt(byte[] keys, byte[] message) {
        plainText = new PlainText(message);
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
     * Encrypts the message according to the AES*3 encryption algorithm
     * @return - a byte array representing the encrypted message
     */
    public byte[] encrypt() {
        byte[] cypher = new byte[plainText.getMessageLength()];
        int blockCount = 0;
        while (blockCount < cypher.length) {
            byte[][] currentBlock = plainText.getNextBlock();
            EncryptionUtils.shiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k1);
            EncryptionUtils.shiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k2);
            EncryptionUtils.shiftRows(currentBlock);
            EncryptionUtils.addRoundKey(currentBlock, k3);
            EncryptionUtils.addBlockToOutput(cypher, currentBlock, blockCount);
            blockCount++;
        }
        return cypher;
    }
}