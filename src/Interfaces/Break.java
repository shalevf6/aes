package Interfaces;

import Messages.Cypher;
import Messages.PlainText;

import java.util.Arrays;

public class Break {

    Cypher cypher;
    PlainText plainText;

    public Break(byte[] message, byte[] cypherMessage) {
        cypher = new Cypher(cypherMessage);
        plainText = new PlainText(message);

    }

    /**
     * method to retrieve keys from a cypher and a plain text
     * @return byte[] at the size of 48 containing 3 keys of size 16
     */
    public byte[] getKeys(){
        byte[] key1 = EncryptionUtils.generateKey();
        byte[] key2 = EncryptionUtils.generateKey();
        keysValidation(key1, key2);

        byte[][] messageBlock = plainText.getNextBlock();
        EncryptionUtils.shiftRows(messageBlock);
        EncryptionUtils.addRoundKey(messageBlock, key1);
        EncryptionUtils.shiftRows(messageBlock);
        EncryptionUtils.addRoundKey(messageBlock, key2);

        byte[] key3 = new byte[16];
        byte[][] cypherBlock = cypher.getNextBlock();
        for (int i = 0; i<16; i++){
            for (int j =0; j<4; j++){
                key3[i] = (byte) (messageBlock[j][i/16] ^ cypherBlock[j][i/16]);
            }
        }

        byte[] allKeys = new byte[48];
        System.arraycopy(key1, 0, allKeys, 0, 16);
        System.arraycopy(key2, 0, allKeys, 16, 16);
        System.arraycopy(key3, 0, allKeys, 32, 16);

        return allKeys;

    }

    /**
     * method that validates that 2 keys are not the same
     * @param key1 - first key
     * @param key2 - second key
     */
    private void keysValidation(byte[] key1, byte[] key2) {
        if (!Arrays.equals(key1, key2)){
            key1[0] = (byte) (key2[0]+1);
        }
    }

    public byte[] getKeys2(){
        byte[] key1 = EncryptionUtils.generateKey();
        byte[] key2 = EncryptionUtils.generateKey();
        keysValidation(key1, key2);
        byte[] key3 = cypher.getBlock(0);

        Encrypt encrypt = new Encrypt(plainText, key1, key2, key3);
        byte[] res = encrypt.encrypt();

        byte[] allKeys = new byte[48];
        System.arraycopy(key1, 0, allKeys, 0, 16);
        System.arraycopy(key2, 0, allKeys, 16, 16);
        System.arraycopy(res, 0, allKeys, 32, 16);

        return allKeys;

    }
}
