import Interfaces.Break;
import Interfaces.Decrypt;
import Interfaces.Encrypt;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0) {
            // Encryption wanted
            if (args[0].equals("-e")) {

            } else {
                // Decryption wanted
                if (args[0].equals("-d")) {

                } else {
                    // Breaking of an encryption wanted
                    if (args[0].equals("-b")) {

                    }
                    // If the input inserted wasn't in the right format
                    else {
                        System.out.println("Incorrect input" + Arrays.toString(args));
                    }
                }
            }
        }
        // If there was no input inserted
        else {
            System.out.println("Missing input");
        }
    }
}
